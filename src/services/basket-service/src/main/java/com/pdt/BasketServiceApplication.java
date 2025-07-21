package com.pdt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@RefreshScope
public class BasketServiceApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(BasketServiceApplication.class, args);
    }
}
