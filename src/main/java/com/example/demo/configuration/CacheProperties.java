package com.example.demo.configuration;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "application.cache")
public class CacheProperties {

    private Duration defaultLockWaitTime;
    private List<CacheNames> cacheNames = new ArrayList<>();

    @Data
    public static class CacheNames {
        private String key;
        private Duration expiryTtl;
        private Duration lockWaitTime;
    }

}