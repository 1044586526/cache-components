package com.aspire.nsmp.cache.config;

import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class CacheConfiguration {

    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
      EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
      cacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
      cacheManagerFactoryBean.setShared(true);
      return cacheManagerFactoryBean;
    }

    @Bean
    public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean bean){
      return new EhCacheCacheManager(bean.getObject());
    }

}
