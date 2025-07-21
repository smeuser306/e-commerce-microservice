package com.pdt.controllers;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
    private final RedisTemplate<String, Object> redisTemplate;

    public HealthCheckController(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/redis-health")
    public String checkRedisConnection(){
       try{
           redisTemplate.opsForValue().set("health", "OK");
           String result = (String) redisTemplate.opsForValue().get("health");
           return  "Redis connection successful: " + result;
       }catch (Exception e){
           return "Redis connection failed: " + e.getMessage();
       }
    }
}
