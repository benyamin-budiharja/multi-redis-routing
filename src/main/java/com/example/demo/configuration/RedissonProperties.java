package com.example.demo.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "application.redisson")
public class RedissonProperties {

    private Integer maxConnectionPoolSize = 64;
    private Integer connectionMinimumIdleSize = 24;

}