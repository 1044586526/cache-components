package com.aspire.nsmp.cache.task;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.aspire.nsmp.cache.bo.ComponentFileNameInfoBo;
import com.aspire.nsmp.cache.bo.ComponentGavInfoBo;
import com.aspire.nsmp.cache.bo.ComponentInfoBo;
import com.aspire.nsmp.cache.constants.ComponentConstants;
import com.aspire.nsmp.cache.exception.BizException;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ljh
 * @version 1.0
 * @date 2023/9/22 15:38
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class ComponentTask {

    private final EhCacheCacheManager ehCacheCacheManager;

//    @Resource(name = "cacheThreadPoolExecutor")
//    private final ThreadPoolExecutor threadPoolExecutor;

    @Value("${csv.path}")
    public String csvPath;

    @Value("${over.num}")
    public Integer overNum;

    public Boolean isExecute = true;
    public List<ComponentInfoBo> infoBos;



    /**
     * 初始化加载
     */
    @PostConstruct
    public void init() {
        //数据写入
        cacheHandler();
    }


    /**
     * 缓存数据写入
     */
    @Scheduled(cron = "0 0 0 1 * *")
    public void cacheHandler(){
        log.info("-------缓存数据写入开始-------");
        Instant start = Instant.now();
        int initNum = 0;
        while (isExecute){
            int num = ++initNum;
            log.info("第[{}]次循环写入缓存开始...",num);
            //获取组件数据
            infoBos = getComponentInfoBo();
            if (CollectionUtils.isEmpty(infoBos)){
                return;
            }
            //获取缓存对象
            CacheManager manager = ehCacheCacheManager.getCacheManager();
            //缓存数据写入
            Stream.of(ComponentConstants.CacheType.values())
                    .forEach(ct ->{
                        List<Element> elements = threadHandler(ct.getCode());
                        if (CollectionUtils.isEmpty(elements)){
                            throw new BizException("[{}]数据为空!",ct.getCode());
                        }
                        Cache cache = manager.getCache(ct.getCode());
                        cache.putAll(elements);
                    });
            //清除废弃数据
            infoBos = null;
            log.info("第[{}]次循环写入缓存结束...",num);
        }
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        long seconds = duration.getSeconds();
        log.info("缓存数据写入结束,总耗时:[{}]",seconds);
    }


    /**
     * 读取csv文件并缓存结果
     * @return
     */
    private void readComponentInfoBo() {
        // 仅在结果未缓存时读取CSV文件
        if (infoBos == null) {
            // 创建数据列表
            List<ComponentInfoBo> componentInfoBos = new ArrayList<>();

            // 创建事件监听器
            AnalysisEventListener<ComponentInfoBo> listener = new AnalysisEventListener<ComponentInfoBo>() {
                @Override
                public void invoke(ComponentInfoBo infoBo, AnalysisContext analysisContext) {
                    // 将每行数据添加到列表中
                    componentInfoBos.add(infoBo);
                }
                @Override
                public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                    // 数据解析完成后的操作，这里可以进行后续处理
                }
            };

            // 读取CSV文件并将数据转换为ComponentInfoBo对象
            List<String> allFilePaths = getAllFilePaths(csvPath);
            if (CollectionUtils.isEmpty(allFilePaths)){
                log.warn("无文件，不做处理！");
                isExecute = false;
                return;
            }
            Instant start = Instant.now();
            //此处采用并行流会导致OOM <parallelStream>
            AtomicInteger num = new AtomicInteger();
            //文件分批处理，保证大对象不会OOM,当前测试数据，目前一个文件大约200M
            allFilePaths.forEach(fp ->{
                log.info("开始处理第[{}]个文件:[{}]", num.incrementAndGet(),fp);
                //超出该文件个数先不处理，等待前面文件执行完毕
                if (num.get() > overNum){
                    log.info("对象已满足，不在处理，直接跳过！");
                    isExecute = true;
                    return;
                }
                EasyExcel.read(fp, ComponentInfoBo.class, listener).sheet().doRead();
                //读完文件清除
                File file = new File(fp);
                if (file.exists()){
                    file.delete();
                }
                log.info("处理完成---");
            });
            Instant end = Instant.now();
            Duration duration = Duration.between(start, end);
            long seconds = duration.getSeconds();
            log.info("文件处理总耗时:[{}]s",seconds);
            // 缓存方法的结果
            infoBos = componentInfoBos;
        }
    }

    /**
     * 获取全部文件
     * @param csvPath
     * @return
     */
    public static List<String> getAllFilePaths(String csvPath)  {
        try (Stream<Path> paths = Files.walk(Paths.get(csvPath))) {
            return paths
                    .filter(Files::isRegularFile) // 过滤出普通文件
                    .map(Path::toString) // 转换为字符串路径
                    .collect(Collectors.toList()); // 收集为列表
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 获取ComponentInfoBo列表
     * @return
     */
    public List<ComponentInfoBo> getComponentInfoBo() {
        readComponentInfoBo(); // 调用读取方法，确保结果被缓存
        return infoBos;
    }


    /**
     * 多线程处理
     * @param name
     * @return
     */
    private synchronized List<Element> threadHandler(String name) {
        List<List<ComponentInfoBo>> partitions = Lists.partition(infoBos, 100000);

        try {
            List<Element> elements = partitions.parallelStream()
                    .flatMap(partition -> partition.stream()
                            .filter(componentInfo -> {
                                if (ComponentConstants.CacheConstants.MD5.equals(name)) {
                                    return !StringUtils.isEmpty(componentInfo.getAssetMd5()) && !"N/A".equals(componentInfo.getAssetMd5());
                                }
                                return true;
                            })
                            .map(componentInfo -> {
                                if (ComponentConstants.CacheConstants.MD5.equals(name)) {
                                    return new Element(componentInfo.getAssetMd5(), componentInfo);
                                }
                                switch (name) {
                                    case ComponentConstants.CacheConstants.GAV:
                                        return new Element(new ComponentGavInfoBo(componentInfo.getCompId(), componentInfo.getGroup(), componentInfo.getName(), componentInfo.getVersion()), componentInfo);
                                    case ComponentConstants.CacheConstants.FILE_NAME:
                                        return new Element(new ComponentFileNameInfoBo(componentInfo.getCompId(), componentInfo.getName() + "-" + componentInfo.getVersion()), componentInfo);
                                    default:
                                        return null;
                                }
                            })
                    )
                    .collect(Collectors.toList());
            return elements;
        } catch (Exception e) {
            log.info("多线程处理异常,异常信息:[{}]", e);
        }

        return null;
    }

}
