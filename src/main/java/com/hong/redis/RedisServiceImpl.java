package com.hong.redis;

import org.springframework.stereotype.Service;

/**
 * Created by Cai on 2015/6/11 16:14.
 */
@Service("redisService")
public class RedisServiceImpl implements RedisService {
    public long del(String... keys) {
        return 0;
    }

    public void set(byte[] key, byte[] value, long liveTime) {

    }

    public void set(String key, String value, long liveTime) {

    }

    public void set(String key, String value) {

    }

    public void set(byte[] key, byte[] value) {

    }

    public String get(String key) {
        return null;
    }

    public void setKeys(String pattern) {

    }

    public boolean exists(String key) {
        return false;
    }

    public String flushDB() {
        return null;
    }

    public long dbSize() {
        return 0;
    }

    public String ping() {
        return null;
    }
}
