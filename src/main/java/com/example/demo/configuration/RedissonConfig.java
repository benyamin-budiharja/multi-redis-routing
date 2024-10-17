package com.example.demo.configuration;

import io.micrometer.common.util.StringUtils;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Slf4j
@Configuration
public class RedissonConfig {

    @Autowired
    private RedisProperties redisProperties;

    @Autowired
    private RedissonProperties redissonProperties;

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() {
        Config config = new Config();
        SingleServerConfig server = config.useSingleServer();

        server.setAddress("redis://" +
                this.redisProperties.getHost() + ":" +
                this.redisProperties.getPort());

        server.setConnectionPoolSize(this.redissonProperties.getMaxConnectionPoolSize());
        server.setConnectionMinimumIdleSize(this.redissonProperties.getConnectionMinimumIdleSize());

        if (StringUtils.isNotEmpty(this.redisProperties.getPassword())) {
            server.setPassword(this.redisProperties.getPassword());
        }

        return Redisson.create(config);
    }

    @Bean
    @DependsOn("redissonClient")
    public RedissonConnectionFactory redissonConnectionFactory() {
        return new RedissonConnectionFactory(this.redissonClient());
    }

    @PreDestroy
    public void destroy() {
        log.info("RedissonConfig is being destroyed");
    }

}