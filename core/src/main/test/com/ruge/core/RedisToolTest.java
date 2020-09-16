package com.ruge.core;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import static org.junit.Assert.*;

public class RedisToolTest {

public static Jedis  jedis = null;


    @Before
    public void getJedis() {
        RedisTool redisTool = new RedisTool("172.20.24.130",6379,"651tkoOuMeQzxLZfFUAv");
        jedis = redisTool.getJedis();
    }
    @Test
    public void get() {
        System.out.println(jedis.get("name"));
    }

    @Test
    public void set() {
        System.out.println(jedis.set("name", "张三"));
    }

    @Test
    public void del() {
    }

    @Test
    public void append() {
        System.out.println(jedis.append("name", "是个学生"));
    }

    @Test
    public void exists() {
        System.out.println(jedis.exists("name"));
        System.out.println(jedis.exists("name2"));
    }

    @Test
    public void setnx() {
        System.out.println(jedis.setnx("name","我是name1"));
        System.out.println(jedis.setnx("name2","我是name2"));
    }

    @Test
    public void setex() {
        System.out.println(jedis.setex("name",10,"我是name1"));
        System.out.println(jedis.setex("name2",20,"我是name2"));
    }

    @Test
    public void setrange() {
    }

    @Test
    public void mget() {
    }

    @Test
    public void mset() {
    }

    @Test
    public void msetnx() {
    }

    @Test
    public void getSet() {
    }

    @Test
    public void getrange() {
    }

    @Test
    public void incr() {
    }

    @Test
    public void incrBy() {
    }

    @Test
    public void decr() {
    }

    @Test
    public void decrBy() {
    }

    @Test
    public void strLen() {
    }

    @Test
    public void hsetnx() {
    }

    @Test
    public void hset() {
    }

    @Test
    public void hmset() {
    }

    @Test
    public void hget() {
    }

    @Test
    public void expire() {
    }

    @Test
    public void hmget() {
    }

    @Test
    public void hincrby() {
    }

    @Test
    public void hexists() {
    }

    @Test
    public void hlen() {
    }

    @Test
    public void hdel() {
    }

    @Test
    public void hkeys() {
    }

    @Test
    public void hvals() {
    }

    @Test
    public void hgetall() {
    }

    @Test
    public void lpush() {
    }

    @Test
    public void rpush() {
    }

    @Test
    public void lset() {
    }

    @Test
    public void lrem() {
    }

    @Test
    public void ltrim() {
    }

    @Test
    public void lpop() {
    }

    @Test
    public void rpop() {
    }

    @Test
    public void rpoplpush() {
    }

    @Test
    public void lindex() {
    }

    @Test
    public void llen() {
    }

    @Test
    public void lrange() {
    }

    @Test
    public void sadd() {
    }

    @Test
    public void srem() {
    }

    @Test
    public void spop() {
    }

    @Test
    public void sdiff() {
    }

    @Test
    public void sdiffstore() {
    }

    @Test
    public void sinter() {
    }

    @Test
    public void sinterstore() {
    }

    @Test
    public void sunion() {
    }

    @Test
    public void sunionstore() {
    }

    @Test
    public void smove() {
    }

    @Test
    public void scard() {
    }

    @Test
    public void sismember() {
    }

    @Test
    public void srandmember() {
    }

    @Test
    public void smembers() {
    }

    @Test
    public void zadd() {
    }

    @Test
    public void zrem() {
    }

    @Test
    public void zincrby() {
    }

    @Test
    public void zrank() {
    }

    @Test
    public void zrevrank() {
    }

    @Test
    public void zrevrange() {
    }

    @Test
    public void zrangebyscore() {
    }

    @Test
    public void zrangeByScore() {
    }

    @Test
    public void zcount() {
    }

    @Test
    public void zcard() {
    }

    @Test
    public void zscore() {
    }

    @Test
    public void zremrangeByRank() {
    }

    @Test
    public void zremrangeByScore() {
    }

    @Test
    public void keys() {
    }

    @Test
    public void ttl() {
        System.out.println(jedis.ttl("name"));
    }
}