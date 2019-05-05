package com.xuecheng.govern.gateway.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by Kai on 2018/11/5.
 */
@Configuration
public class RateLimiterRedisConfiguration {

    @Value("${spring.redis.rate-limiter.host}")
    protected String host;
    @Value("${spring.redis.rate-limiter.port}")
    protected int port;
    @Value("${spring.redis.pool.max-active}")
    protected int maxActive;
    @Value("${spring.redis.pool.max-idle}")
    protected int maxIdle;
    @Value("${spring.redis.pool.min-idle}")
    protected int minIdle;
    @Value("${spring.redis.timeout}")
    protected int timeout;

    @Bean(name = "rateLimiterRedisTemplate")
    public RateLimiterRedisTemplate rateLimiterRedisTemplate() {
        RateLimiterRedisTemplate redisTemplate = new RateLimiterRedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }

    private RedisConnectionFactory jedisConnectionFactory() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxActive);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        config.setMaxWaitMillis(timeout);
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(config);
        connectionFactory.setHostName(host);
        connectionFactory.setPort(port);
        connectionFactory.setTimeout(timeout);
        connectionFactory.afterPropertiesSet();
        return connectionFactory;
    }

}
