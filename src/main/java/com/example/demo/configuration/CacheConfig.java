package com.example.demo.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.boot.actuate.health.HealthIndicator;

import java.time.Duration;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.demo.configuration.CacheProperties.CacheNames;


@Configuration
@EnableCaching
@EnableTransactionManagement
@DependsOn("redissonClient")
public class CacheConfig {

    private static final String PONG = "PONG";

    @Autowired
    private RedisProperties redisProperties;

    @Value("${spring.cache.redis.time-to-live}")
    private Duration DEFAULT_TTL;

    @Value("${spring.cache.redis.key-prefix}")
    private String PREFIX_KEY;

    @Autowired
    private CacheProperties cacheProperties;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    @Qualifier("messagePackObjectMapper")
    private ObjectMapper messagePackObjectMapper;

    @Bean
    public RedisCacheManager cacheManager() {
        final Map<String, RedisCacheConfiguration> cacheConfigurations =
                cacheProperties.getCacheNames()
                        .stream()
                        .collect(Collectors.toMap(CacheNames::getKey,
                                cacheNames -> redisCacheConfiguration(cacheNames.getExpiryTtl())));

        return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory)
                .cacheDefaults(redisCacheConfiguration(DEFAULT_TTL))
                .withInitialCacheConfigurations(cacheConfigurations)
                .build();
    }

    private RedisCacheConfiguration redisCacheConfiguration(Duration duration) {
        final CacheKeyPrefix cacheKeyPrefix = cacheName -> PREFIX_KEY + CacheConstant.SEPARATOR
                + cacheName + CacheConstant.SEPARATOR;

        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(duration)
                .computePrefixWith(cacheKeyPrefix);
    }

    @Bean
    public HealthIndicator redisHealthIndicator() {
        return () -> {
            final long start = System.currentTimeMillis();
            RedisConnection connection = null;
            try {
                connection = RedisConnectionUtils.getConnection(this.redisConnectionFactory);
                final String response = connection.ping();
                final Health.Builder builder = PONG.equals(response) ? Health.up() : Health.down();
                return builder.withDetail("time", System.currentTimeMillis() - start)
                        .withDetail("response", response).build();
            } catch (final Exception e) {
                return Health.down().withDetail("time", System.currentTimeMillis() - start)
                        .withDetail("error", e.getMessage()).build();
            } finally {
                RedisConnectionUtils.releaseConnection(connection, this.redisConnectionFactory);
            }
        };
    }

}