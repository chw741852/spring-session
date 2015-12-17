package com.hong.redis;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StopWatch;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.CountDownLatch;

/**
 * Created by caihongwei on 2015/12/16 16:57.
 * <p>
 * 测试在redis中使用LUA插件
 * 一个抢红包的策略
 */
public class TestEval {
    private static final int THREAD_COUNT = 20;
    private static final int HONGBAO_COUNT = 100_000;

    private static final String REDIS_PATH = "192.168.136.128";
    private static JedisPool jedisPool;

    private static final String HONGBAO_LIST = "list:hongbao";
    private static final String HONGBAO_CONSUMED_LIST = "list:hongbao:consumed";
    private static final String HONGBAO_CONSUMED_MAP = "map:hongbao:consumed";

    private static StopWatch stopWatch = new StopWatch();

    // 函数：尝试获得红包，如果成功，则返回json字符串，如果不成功，则返回空
    // 参数：红包队列名，已消费的队列名，去重的map名，用户ID
    // 返回值：nil或者json字符串，包含用户ID：userId，红包ID：id，红包金额：money
    private static final String LUA_SCRIPT = "if redis.call('hexists', KEYS[3], KEYS[4]) ~= 0 then return nil" +
            " else local hongbao = redis.call('rpop', KEYS[1])" +
            " if hongbao then" +
            " local x = cjson.decode(hongbao)" +
            " x['userId'] = KEYS[4]" +
            " local re = cjson.encode(x)" +
            " redis.call('hset', KEYS[3], KEYS[4], KEYS[4])" +
            " redis.call('lpush', KEYS[2], re)" +
            " return re" +
            " end end return nil";

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(THREAD_COUNT);
        jedisPool = new JedisPool(config, REDIS_PATH);
    }

    public static void main(String[] args) {
        try {
            generateTestData();
            testTryGetHongbao();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成测试数据
     */
    private static void generateTestData() throws InterruptedException {
        Jedis _jedis = jedisPool.getResource();
        _jedis.flushAll();
        returnJedis(_jedis);

        final CountDownLatch latch = new CountDownLatch(THREAD_COUNT);
        for (int i = 0; i < THREAD_COUNT; i++) {
            final int temp = i;
            Thread thread = new Thread() {
                @Override
                public void run() {
                    Jedis jedis = jedisPool.getResource();
                    int per = HONGBAO_COUNT / THREAD_COUNT;
                    for (int j = temp * per; j < (temp + 1) * per; j++) {
                        JSONObject json = new JSONObject();
                        json.put("id", j);
                        json.put("money", j);
                        jedis.lpush(HONGBAO_LIST, json.toJSONString());
                    }
                    returnJedis(jedis);
                    latch.countDown();
                }
            };
            thread.start();
        }

        latch.await();
    }

    /**
     * 测试获取红包
     */
    private static void testTryGetHongbao() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(THREAD_COUNT);
        System.err.println("start: " + System.currentTimeMillis() / 1000);
        stopWatch.start();
        for (int i = 0; i < THREAD_COUNT; i++) {
            final int temp = i;
            Thread thread = new Thread() {
                @Override
                public void run() {
                    Jedis jedis = jedisPool.getResource();
                    String sha = jedis.scriptLoad(LUA_SCRIPT);
                    int j = HONGBAO_COUNT / THREAD_COUNT * temp;
                    while (true) {
                        Object obj = jedis.eval(LUA_SCRIPT, 4, HONGBAO_LIST, HONGBAO_CONSUMED_LIST, HONGBAO_CONSUMED_MAP, "" + j);
                        j++;
                        if (obj != null) {
//                            System.out.println(obj);
                        } else {
                            // 已经取完了
                            if (jedis.llen(HONGBAO_LIST) == 0) break;
                        }
                    }
                    returnJedis(jedis);
                    latch.countDown();
                }
            };
            thread.start();
        }

        latch.await();
        stopWatch.stop();

        System.err.println("time: " + stopWatch.getTotalTimeSeconds());
        System.err.println("speed: " + HONGBAO_COUNT / stopWatch.getTotalTimeSeconds());
        System.err.println("end: " + System.currentTimeMillis() / 1000);
    }

    private static void returnJedis(Jedis jedis) {
        if (jedis != null) jedisPool.returnResourceObject(jedis);
    }
}
