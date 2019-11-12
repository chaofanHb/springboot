package cn.hb.genneral.controller;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class EhCacheTestService {
    @Cacheable(value="cacheTest",key="#param")
    public String getTimestamp(String param) {
        Long timestamp = System.currentTimeMillis();
        System.out.println("新生成的timestamp："+timestamp);
        return timestamp.toString();
    }
}
