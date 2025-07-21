package com.pdt.contracts.persistence;

import com.pdt.entities.EntityId;
import com.pdt.entities.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    List<Order> findAllByUsername(String username);

    Order save(Order order);

    void delete(Order orderToDelete);

    Optional<Order> findById(EntityId id);
}
