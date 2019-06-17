package com.zk.WeChatRobot.utils;

import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;

public class CacheUtils {

    private static final EhCacheCacheManager cacheCacheManager;

    static {
        cacheCacheManager = ApplicationContextUtils.applicationContext.getBean(EhCacheCacheManager.class);
    }

    public static <T> T getCacheFromAssignCache(String cacheName,String cacheKey,Class<T> clazz){
        Cache cache=cacheCacheManager.getCache(cacheName);
        T cacheValue = cache.get(cacheKey, clazz);
        return cacheValue;
    }

    public static void setCacheToAssignCache(String cacheName,Object cacheKey,Object cacheValue){
        Cache cache=cacheCacheManager.getCache(cacheName);
        cache.put(cacheKey,cacheValue);
    }
}
