package com.example.demo.service;

import com.example.demo.constant.CacheDataType;

public interface CacheService {
    Object getByKey(CacheDataType type, String key);

    Object getHashByMemberId(String key, Long memberId);

    Object getHashMemberSize(String key);

    Object putIfAbsentHashByMemberId(String key, Long memberId, Object value);

    Object updateHashByMemberId(String key, Long memberId, Object value);

    Object deleteHashByMemberId(String key, Long memberId);

    Object deleteByKey(String key);

    Object incrementByKey(String key, Long increment);

    Object decrementByKey(String key, Long decrement);

    Object countKeyPattern(String key);

    Object getKeyPattern(String key);

    void flushDb();
}