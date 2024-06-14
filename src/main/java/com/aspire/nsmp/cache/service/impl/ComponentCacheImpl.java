package com.aspire.nsmp.cache.service.impl;

import com.aspire.nsmp.cache.bo.ComponentInfoBo;
import com.aspire.nsmp.cache.constants.ComponentConstants;
import com.aspire.nsmp.cache.dto.ComponentReqDto;
import com.aspire.nsmp.cache.dto.ResultDTO;
import com.aspire.nsmp.cache.service.ComponentCacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.search.Attribute;
import net.sf.ehcache.search.Direction;
import net.sf.ehcache.search.Query;
import net.sf.ehcache.search.Results;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ljh
 * @version 1.0
 * @date 2023/9/21 14:02
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ComponentCacheImpl implements ComponentCacheService {

    private final EhCacheCacheManager ehCacheCacheManager;

    @Override
    public ResultDTO md5Search(ComponentReqDto reqDto) {

        //获取缓存对象(MD5)
        CacheManager manager = ehCacheCacheManager.getCacheManager();
        Cache cache = manager.getCache(ComponentConstants.CacheConstants.MD5);

        //获取key对应属性值
        Attribute<String> md5 = cache.getSearchAttribute(ComponentConstants.CacheConstants.MD5);

        // 定义分页参数
        calPageData(reqDto);

        //缓存数据查询
        Query query = cache.createQuery().includeKeys().includeValues();
        if (!StringUtils.isEmpty(reqDto.getMd5())){
            query.addCriteria(md5.eq(reqDto.getMd5())).addOrderBy(md5, Direction.DESCENDING);
        }
        Results execute = query.execute();

        //为空返回
        if (execute.size() == 0){
            return ResultDTO.fail("无数据");
        }

        //分页
        List<ComponentInfoBo> components = execute
                .range(reqDto.getStartIndex(), reqDto.getEndIndex())
                .stream()
                .map(m -> (ComponentInfoBo)m.getValue())
                .collect(Collectors.toList());

        //响应参数
        return response(reqDto,execute.size(),components);
    }

    /**
     * 计算分页参数
     * @param reqDto
     */
    private void calPageData(ComponentReqDto reqDto){
        // 定义分页参数
        reqDto.setPageSize(null == reqDto.getPageSize() ? 10 : reqDto.getPageSize());
        reqDto.setPageIndex(null == reqDto.getPageIndex() ? 1 : reqDto.getPageIndex());
        int pageSize = reqDto.getPageSize();
        int pageNumber = reqDto.getPageIndex();

        // 计算起始索引和结束索引
        int startIndex = (pageNumber - 1) * pageSize;
        int endIndex = startIndex + pageSize - 1;

        reqDto.setStartIndex(startIndex);
        reqDto.setEndIndex(endIndex);
    }

    /**
     * 获取响应结果
     * @param reqDto
     * @param total
     * @return
     */
    private ResultDTO response(ComponentReqDto reqDto,Integer total,List data){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setPageIndex(reqDto.getPageIndex());
        resultDTO.setPageSize(reqDto.getPageSize());
        //计算总翻页数
        int count = (int) Math.ceil((double) total / reqDto.getPageSize());
        resultDTO.setPageCount(count);
        resultDTO.setTotal(total);
        resultDTO.setData(data);
        return resultDTO;
    }

    @Override
    public ResultDTO gavSearch(ComponentReqDto reqDto) {
        //获取缓存对象(gav)
        CacheManager manager = ehCacheCacheManager.getCacheManager();
        Cache cache = manager.getCache(ComponentConstants.CacheConstants.GAV);

        //获取key对应属性值
        Attribute<String> name = cache.getSearchAttribute("name");
        Attribute<String> group = cache.getSearchAttribute("group");
        Attribute<String> version = cache.getSearchAttribute("version");
        Attribute<String> compId = cache.getSearchAttribute("compId");

        // 定义分页参数
        calPageData(reqDto);

        //缓存数据查询
        Query query = cache.createQuery().includeKeys().includeValues();
        //组件名称
        if (!StringUtils.isEmpty(reqDto.getName())){
            query.addCriteria(name.ilike("*"+reqDto.getName()+"*"));
        }
        //组件ID
        if (!StringUtils.isEmpty(reqDto.getGroupId())){
            query.addCriteria(group.ilike("*"+reqDto.getGroupId()+"*"));
        }
        //组件版本
        if (!StringUtils.isEmpty(reqDto.getVersion())){
            query.addCriteria(version.ilike("*"+reqDto.getVersion()+"*"));
        }
        //排序-处理分页问题
        Results execute = query.addOrderBy(compId,Direction.DESCENDING).execute();

        //为空返回
        if (execute.size() == 0){
            return ResultDTO.fail("无数据");
        }

        //分页
        List<ComponentInfoBo> components = execute
                .range(reqDto.getStartIndex(), reqDto.getEndIndex())
                .stream()
                .map(m -> (ComponentInfoBo)m.getValue())
                .collect(Collectors.toList());

        //响应参数
        return response(reqDto,execute.size(),components);
    }

    @Override
    public ResultDTO fileNameSearch(ComponentReqDto reqDto) {
        //获取缓存对象(fileName)
        CacheManager manager = ehCacheCacheManager.getCacheManager();
        Cache cache = manager.getCache(ComponentConstants.CacheConstants.FILE_NAME);

        //获取key对应属性值
        Attribute<String> fileName = cache.getSearchAttribute("fileName");
        Attribute<String> compId = cache.getSearchAttribute("compId");

        // 定义分页参数
        calPageData(reqDto);

        //缓存数据查询
        Query query = cache.createQuery().includeKeys().includeValues();
        if (!StringUtils.isEmpty(reqDto.getFileName())){
            query.addCriteria(fileName.eq(reqDto.getFileName())).addOrderBy(compId, Direction.DESCENDING);
        }
        Results execute = query.execute();

        //为空返回
        if (execute.size() == 0){
            return ResultDTO.fail("无数据");
        }

        //分页
        List<ComponentInfoBo> components = execute
                .range(reqDto.getStartIndex(), reqDto.getEndIndex())
                .stream()
                .map(m -> (ComponentInfoBo)m.getValue())
                .collect(Collectors.toList());

        //响应参数
        return response(reqDto,execute.size(),components);
    }

}
