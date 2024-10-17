package com.example.demo.configuration;


import org.springframework.data.redis.serializer.StringRedisSerializer;

public class StringKeyWithPrefixSerializer extends StringRedisSerializer {

    private final String prefix;

    public StringKeyWithPrefixSerializer(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public byte[] serialize(String value) {
        return super.serialize(prefixWithSeparator().concat(value));
    }

    @Override
    public String deserialize(byte[] bytes) {
        String deserialize = super.deserialize(bytes);

        if (deserialize != null && deserialize.startsWith(prefix)) {
            int prefixLength = prefixWithSeparator().length();
            return deserialize.substring(prefixLength);
        }

        return deserialize;
    }

    private String prefixWithSeparator() {
        return prefix.concat(CacheConstant.SEPARATOR);
    }

}