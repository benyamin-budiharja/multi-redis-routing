package com.example.demo.configuration;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class MessagePackRedisSerializer<T> implements RedisSerializer<T> {

    private final ObjectMapper objectMapper;
    private final Class<T> clazz;

    public MessagePackRedisSerializer(ObjectMapper objectMapper, Class<T> clazz) {
        this.objectMapper = objectMapper;
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        try {
            return objectMapper.writeValueAsBytes(t);
        } catch (Exception e) {
            throw new SerializationException("Could not serialize: " + e.getMessage(), e);
        }
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null) {
            return null;
        }
        try {
            return objectMapper.readValue(bytes, clazz);
        } catch (Exception e) {
            throw new SerializationException("Could not deserialize: " + e.getMessage(), e);
        }
    }
}