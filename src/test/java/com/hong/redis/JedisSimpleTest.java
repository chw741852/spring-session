package com.hong.redis;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Created by Cai on 2015/6/10 17:05.
 */
@Ignore
public class JedisSimpleTest {
    private Jedis jedis;

    @Before
    public void before() {
        jedis = new Jedis("127.0.0.1");
        jedis.auth("jredis");
    }

    @Test
    public void testSet() {
        jedis.set("blog", "java redis");
    }

    @Test
    public void testGet() {
        System.out.println(jedis.get("blog"));
        Assert.assertEquals("java redis", jedis.get("blog"));
    }

    @Test
    public void testRenameKey() {
        jedis.rename("blog", "new_blog");
    }

    @Test
    public void testDel() {
        jedis.del("blog");
    }

    @Test
    public void testKeys() {
        System.out.println(jedis.keys("*"));
    }
}
