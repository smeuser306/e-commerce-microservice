package com.pdt.controllers;

import com.pdt.OrderingApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = OrderingApplication.class)
public class OrderControllerTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testOrderControllerBeanExists() {
        // Test that the OrderController bean is properly instantiated
        OrderController orderController = applicationContext.getBean(OrderController.class);
        assertNotNull(orderController, "OrderController bean should be instantiated");
        
        System.out.println("[DEBUG_LOG] OrderController bean successfully instantiated");
    }
}