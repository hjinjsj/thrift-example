package com.didi.example.lib.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;


/**
 * @author huangjin
 */
@Slf4j
public class RedisPoolUtil {

    @Autowired
    private static RedisPool redisPool;

    /**
     * 设置key的有效期
     * @param key
     * @param seconds
     * @return
     */
    public static Long expire(String key, int seconds){
        Long result = null;
        try (Jedis jedis = redisPool.getJedis()){
            /**
             * 从自定义RedisPool连接池获取一个实例
             */
            result = jedis.expire(key,seconds);
        } catch (Exception e) {
            log.error("expire key:{} error",key ,e);
        }
        return result;
    }

    /**
     * 设置key时间并且设置过期时间
     * @param key
     * @param value
     * @param seconds
     * @return
     */
    public static String setEx(String key, String value, int seconds){
        String result = null;
        try (Jedis jedis = redisPool.getJedis()){
            result = jedis.setex(key, seconds, value);
        } catch (Exception e) {
            log.error("expire key:{} value:{}, error: {}", key , value, e);
        }
        return result;
    }

    /**
     * 设置一个key
     * @param key
     * @param value
     * @return
     */
    public static String set(String key, String value){
        String result = null;
        try (Jedis jedis = redisPool.getJedis()){
            result = jedis.set(key,value);
        } catch (Exception e) {
            log.error("expire key:{} value:{} error", key, value, e);
        }
        return result;
    }

    //获取一个key
    public static String get(String key){
        String result = null;
        try (Jedis jedis = redisPool.getJedis()){
            result = jedis.get(key);
        } catch (Exception e) {
            log.error("expire key:{} value:{} error",key ,e);
        }
        return result;
    }

    /**
     * 删除一个key
     * @param key
     * @return
     */
    public static Long del(String key){
        Long result = null;
        try (Jedis jedis = redisPool.getJedis()){
            result = jedis.del(key);
        } catch (Exception e) {
            log.error("expire key:{} value:{} error",key ,e);
        }
        return result;
    }

    /**
     * 调用测试
     * @param args
     */
    public static void test(String[] args) {
        String result =  RedisPoolUtil.set("hjin","25");
        result = RedisPoolUtil.get("hjin");
        result = RedisPoolUtil.setEx("didi","23",20);
        Long resultL = RedisPoolUtil.expire("didi",20);
        resultL = RedisPoolUtil.del("didi");
        resultL = RedisPoolUtil.del("hjin");
        log.info("补充：使用redis缓存数据时候，最好加上过期时间，因为redis缓存需要占用内存空间");
    }

}
