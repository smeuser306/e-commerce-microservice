package com.pdt.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/order-health")
    public String checkRedisConnection(){
       try{
           return  "Order connection successful: ";
       }catch (Exception e){
           return "Order connection failed: " + e.getMessage();
       }
    }
}
