package com.example.demo.property;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "application.redis.connection")
public class RedisConnectionProperty {

    private List<Detail> details;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor

    private static class Detail {
        private String host;
        private Integer port;
        private String password;

        private Integer maxConnectionPoolSize;
        private Integer connectionMinimumIdleSize;
    }

}