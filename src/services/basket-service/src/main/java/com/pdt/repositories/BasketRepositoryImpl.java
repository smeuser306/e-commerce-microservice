package com.pdt.repositories;

import com.pdt.entities.ShoppingCart;
import com.pdt.repositories.contracts.BasketRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BasketRepositoryImpl implements BasketRepository {
    private final RedisTemplate<String, Object> redisTemplate;

    public BasketRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Optional<ShoppingCart> getBasket(String username) {
        var basket = (ShoppingCart) redisTemplate.opsForValue().get(username);
        return Optional.ofNullable(basket);
    }

    @Override
    public ShoppingCart updateShoppingCart(ShoppingCart basket) {
        redisTemplate.opsForValue().set(basket.username(), basket);

        return getBasket(basket.username()).orElse(null);
    }

    @Override
    public void deleteByUsername(String username) {
        redisTemplate.delete(username);
    }
}
