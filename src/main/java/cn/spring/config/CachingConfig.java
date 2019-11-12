package cn.spring.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import net.sf.ehcache.CacheManager;

@Configuration  
@EnableCaching//启用缓存  
public class CachingConfig {  
      
    /** 
     * @param cacheManager 是 net.sf.ehcache.CacheManager的一个实例 
     * 配置EhCacheCacheManager缓存管理器 
     */  
    @Bean  
    public EhCacheCacheManager cacheManager(CacheManager cacheManager) {  
        return new EhCacheCacheManager(cacheManager);  
    }  
      
    @Bean  
    public EhCacheManagerFactoryBean ehcache() {  
          
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();  
        ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("/ehcache.xml"));  
        return ehCacheManagerFactoryBean;  
          
    }  
}  