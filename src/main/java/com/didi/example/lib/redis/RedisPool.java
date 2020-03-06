package com.didi.example.lib.redis;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import javax.annotation.PreDestroy;

/**
 *
 * @author Mark
 * @date 2018/4/20
 * Redis连接池：RedisPool
 */

@Slf4j
public class RedisPool {
    private JedisPool pool;

    public RedisPool(final GenericObjectPoolConfig poolConfig, final String host, final int port,
                     final int timeout) {
        pool = new JedisPool(poolConfig, host, port, timeout, null);
    }

    /**
     * 获取一个Jedis实例
     * @return
     */
    public  Jedis getJedis() {
        return pool.getResource();
    }

    public void close() {
        pool.close();
    }

    /**
     * 测试
     * @param args
     */
    public void test(String[] args) {
        Jedis jedis = pool.getResource();
        jedis.set("didi","didi");
        jedis.close();

        //临时调用，销毁连接池中的所有连接
        pool.destroy();
       log.info("test is end");

    }

}
