package com.pdt.services.configs;

import com.pdt.basket.grpc.DiscountProtoServiceGrpc;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@TestConfiguration
public class TestConfig {

    @Bean
    @Primary
    @Profile("test")
    public DiscountProtoServiceGrpc.DiscountProtoServiceBlockingStub discountProtoStub() {
        return Mockito.mock(DiscountProtoServiceGrpc.DiscountProtoServiceBlockingStub.class);
    }
}
