package com.example.demo.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConnectionConfig {
    @Autowired
    private CacheProperties cacheProperties;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    @Qualifier("messagePackObjectMapper")
    private ObjectMapper messagePackObjectMapper;

    @Value("${spring.cache.redis.key-prefix}")
    private String PREFIX_KEY;

    @Bean
    @Primary
    @Qualifier("redisConnectionOne")
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(this.redisConnectionFactory);
        template.setEnableTransactionSupport(true);

        template.setKeySerializer(new StringKeyWithPrefixSerializer(PREFIX_KEY));

        MessagePackRedisSerializer<Object> msgPackSerializer = new MessagePackRedisSerializer<>(
                messagePackObjectMapper, Object.class
        );

        template.setHashKeySerializer(msgPackSerializer);
        template.setValueSerializer(msgPackSerializer);
        template.setHashValueSerializer(msgPackSerializer);
        return template;
    }
}