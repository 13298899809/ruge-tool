package com.ruge.core;

import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author tengfei
 * @version 1.0
 * @date 2018/7/13 下午4:15
 */
public class RedisUtil {
    private JedisPool pool = null;











//    /**
//     * 通过key在list指定的位置之前或者之后 添加字符串元素
//     *
//     * @param key
//     * @param where LIST_POSITION枚举类型
//     * @param pivot list里面的value
//     * @param value 添加的value
//     * @return
//     */
//    public Long linsert(String key, BinaryClient.LIST_POSITION where,
//                        String pivot, String value) {
//        Jedis jedis = getJedis();
//        return jedis.linsert(key, where, pivot, value);
//    }



    /**
     * 通过key判断值得类型
     *
     * @param key
     * @return
     */
    public String type(String key) {
        Jedis jedis = getJedis();
        return jedis.type(key);
    }


    private void close(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    private Jedis getJedis() {
        return pool.getResource();
    }

    public static RedisUtil getRedisUtil() {
        return new RedisUtil();
    }

}