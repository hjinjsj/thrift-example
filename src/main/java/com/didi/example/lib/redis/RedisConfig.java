package com.didi.example.lib.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author huangjin
 */
@Configuration
@Slf4j
public class RedisConfig {
    @Autowired
    private RedisProperties props;

    @Bean(name = "redisPool", destroyMethod = "close")
    public RedisPool getRedisPool(){
        log.info("config: {}, host: {}, port: {}, timeout: {}", props.getConfig().toString(), props.getHost(), props.getPort(), props.getTimeout());
        RedisPool redisPool = new RedisPool(props.getConfig(), props.getHost(), props.getPort(), props.getTimeout());
        return redisPool;
    }
}
