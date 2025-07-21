package com.pdt.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.redis.core.RedisHash;

import java.util.ArrayList;
import java.util.List;

@RedisHash("Basket")
@JsonIgnoreProperties(ignoreUnknown = true)
public record ShoppingCart(
        String username,
        List<ShoppingCartItem> items
) {
    public ShoppingCart(String username) {
        this(username, new ArrayList<>());
    }

    public double getTotalPrice() {
        return items.stream()
                .map(item -> item.getPrice() * item.getQuantity())
                .reduce(0D, Double::sum);
    }
}

