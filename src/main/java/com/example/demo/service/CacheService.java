package com.example.demo.service;

public interface CacheService {
    Object get(String key);

    void put(String key, String  value);

    void flushAll();
}