package com.aspire.nsmp.cache.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ljh
 * @version 1.0
 * @date 2023/9/25 17:17
 */
@Configuration
@Slf4j
public class ThreadPoolConfig {

    @Value("${threadPool.keepAliveTime}")
    private long keepAliveTime;

    @Value("${threadPool.corePoolSize}")
    private Integer corePoolSize;

    @Value("${threadPool.maxPoolSize}")
    private Integer maxPoolSize;

    @Value("${threadPool.maxQueue}")
    private Integer maxQueue;

    @Bean("cacheThreadPoolExecutor")
    public ThreadPoolExecutor initThreadPoolExecutor(){
        log.info("初始化线程池：corePoolSize：{}, maxPoolSize：{}, keepAliveTime：{} ,maxQueue：{}"
                ,this.corePoolSize
                ,this.maxPoolSize
                ,this.keepAliveTime
                ,this.maxQueue);
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(this.corePoolSize,
                this.maxPoolSize,
                this.keepAliveTime,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(this.maxQueue)
        );
        return threadPool;
    }
}
