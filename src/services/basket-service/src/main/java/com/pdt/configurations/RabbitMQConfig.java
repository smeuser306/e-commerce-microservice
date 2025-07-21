package com.pdt.configurations;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String BASKET_EXCHANGE = "basket.exchange";
    public static final String BASKET_UPDATED_QUEUE = "basket.updated.queue";
    public static final String BASKET_UPDATED_ROUTING_KEY = "basket.updated";

    @Bean
    public TopicExchange basketExchange(){
        return new TopicExchange(BASKET_EXCHANGE);
    }

    @Bean
    public Queue basketUpdatedQueue(){
        return new Queue(BASKET_UPDATED_QUEUE);
    }

    @Bean
    public Binding basketUpdatedBinding(){
        return BindingBuilder
                .bind(basketUpdatedQueue())
                .to(basketExchange())
                .with(BASKET_UPDATED_ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
