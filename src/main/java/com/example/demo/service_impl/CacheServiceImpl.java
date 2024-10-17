package com.example.demo.service_impl;

import com.example.demo.configuration.CacheConstant;
import com.example.demo.constant.CacheDataType;
import com.example.demo.service.CacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CacheServiceImpl implements CacheService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final RedisConnectionFactory redisConnectionFactory;

    @Override
    public Object getByKey(CacheDataType type, String key) {
        switch (type) {
            case SET:
                return this.redisTemplate.boundSetOps(key).members();
            case HASH:
                return this.redisTemplate.boundHashOps(key).entries();
            case SORTED_SET:
                return this.redisTemplate.boundZSetOps(key).range(0, -1);
            case LIST:
                return this.redisTemplate.boundListOps(key).range(0, Long.MAX_VALUE);
            case VALUE:
                return this.redisTemplate.boundValueOps(key).get();
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override
    public Object getHashByMemberId(String key, Long memberId) {
        return this.redisTemplate.boundHashOps(key).get(memberId);
    }

    @Override
    public Object getHashMemberSize(String key) {
        return this.redisTemplate.boundHashOps(key).size();
    }

    @Override
    public Object putIfAbsentHashByMemberId(String key, Long memberId, Object value) {
        return this.redisTemplate.boundHashOps(key).putIfAbsent(memberId, value);
    }

    @Override
    public Object updateHashByMemberId(String key, Long memberId, Object value) {
        Object object = this.redisTemplate.boundHashOps(key).get(memberId);
        if (Objects.isNull(object)) {
            throw new IllegalArgumentException("value must not be null!");
        }
        this.redisTemplate.boundHashOps(key).put(memberId, value);
        return true;
    }

    @Override
    public Object deleteHashByMemberId(String key, Long memberId) {
        return this.redisTemplate.boundHashOps(key).delete(memberId);
    }

    @Override
    public Object deleteByKey(String key) {
        return this.redisTemplate.delete(key);
    }

    @Override
    public Object incrementByKey(String key, Long increment) {
        return this.redisTemplate.boundValueOps(key).increment(increment);
    }

    @Override
    public Object decrementByKey(String key, Long decrement) {
        return this.redisTemplate.boundValueOps(key).decrement(decrement);
    }

    @Override
    public Object countKeyPattern(String key) {
        return Optional
                .ofNullable(this.redisTemplate.keys(key))
                .map(Set::size)
                .orElse(0);
    }

    @Override
    public Object getKeyPattern(String key) {
        return this.redisTemplate.keys(key);
    }

    @Override
    public void flushDb() {
        Set<String> idSequencerKeys = Optional.ofNullable(
                        this.redisTemplate.keys(CacheConstant.keyByPattern("id-sequencer")))
                .orElse(new HashSet<>());

        Map<String, Long> lastIdSequencerMap = idSequencerKeys
                .stream()
                .collect(Collectors.toMap(key -> key,
                        key -> Optional.ofNullable(this.redisTemplate.opsForValue().increment(key))
                                .orElse(0L)));

        try (RedisConnection redisConnection = this.redisConnectionFactory.getConnection()) {
            redisConnection.flushDb();
        }

        lastIdSequencerMap
                .forEach((key, lastId) -> this.redisTemplate.opsForValue().increment(key, lastId - 1));
    }
}