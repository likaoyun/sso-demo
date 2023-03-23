package com.supos.eco.util;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.date.DateUnit;

/**
 * @author caonuoqi@supos.com
 * @date 2022/7/26
 */
public class CacheUtils {
    static TimedCache<String, String> cache = CacheUtil.newTimedCache(1600 * DateUnit.SECOND.getMillis());

    public static String get(String name) {
        return cache.get(name,false);
    }

    public static void set(String name, String value) {
        cache.put(name,value);
        cache.schedulePrune(1000);
    }
    public static  void delete(String name){
        cache.remove(name);
    }
}
