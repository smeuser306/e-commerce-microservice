package com.pdt.repositories.contracts;

import com.pdt.entities.ShoppingCart;

import java.util.Optional;

public interface BasketRepository{
    Optional<ShoppingCart> getBasket(String username);
    ShoppingCart updateShoppingCart(ShoppingCart basket);
    void deleteByUsername(String username);
}
