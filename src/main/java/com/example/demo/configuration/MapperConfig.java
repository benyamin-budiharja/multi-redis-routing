package com.example.demo.configuration;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.github.dozermapper.core.Mapper;
import org.msgpack.jackson.dataformat.MessagePackFactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@Configuration
public class MapperConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
        mapper.configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN, true);
        mapper.disable(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS);
        mapper.disable(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS);
        mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);

        mapper.registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());

        return mapper;
    }

    @Bean
    public Mapper mapper() {
        return DozerBeanMapperBuilder.buildDefault();
    }

    @Bean
    @Qualifier("messagePackObjectMapper")
    public ObjectMapper messagePackObjectMapper() {
        ObjectMapper mapper = new ObjectMapper(new MessagePackFactory());
        mapper.configOverride(BigInteger.class).setFormat(JsonFormat.Value.forShape(JsonFormat.Shape.STRING));
        mapper.configOverride(BigDecimal.class).setFormat(JsonFormat.Value.forShape(JsonFormat.Shape.STRING));
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

}