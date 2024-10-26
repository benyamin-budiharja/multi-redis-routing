package com.example.demo.controller;

import com.example.demo.constant.ApiConstant;
import com.example.demo.dto.BaseResponse;
import com.example.demo.service.CacheService;
import com.example.demo.utils.ResponseUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class CacheController {

    private final CacheService internalCacheService;
    private final ObjectMapper objectMapper;

    @GetMapping(ApiConstant.V1_INTERNAL_CACHE)
    public BaseResponse<Object> get(@RequestParam("key") String key) {
        return ResponseUtils.ok(internalCacheService.get(key));
    }

    @PostMapping(ApiConstant.V1_INTERNAL_CACHE)
    public BaseResponse<Boolean> get(@RequestParam("key") String key, @RequestBody Object value) {
        internalCacheService.put(key, toString(value));
        return ResponseUtils.ok(true);
    }

    private String toString(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            return "";
        }

    }

    @DeleteMapping(ApiConstant.V1_INTERNAL_CACHE)
    public BaseResponse<Boolean> delete(@RequestParam("key") String key) {
        internalCacheService.flushAll();
        return ResponseUtils.ok(true);
    }


}