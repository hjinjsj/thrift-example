package com.didi.example.lib.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author huangjin
 */
@Configuration
@ConfigurationProperties(prefix = "redis")
@Data
public class RedisProperties {
    private JedisPoolConfig config;

    /**
     * redis服务端的ip
     */
    private String host = "127.0.0.1";
    /**
     * redis提供的接口
     */
    private Integer port = 3679;

    private Integer timeout = 1000 * 2;
}
