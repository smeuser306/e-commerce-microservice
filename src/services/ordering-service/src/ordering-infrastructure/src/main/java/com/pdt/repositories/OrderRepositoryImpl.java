package com.pdt.repositories;

import com.pdt.annotations.ApplicationService;
import com.pdt.contracts.persistence.OrderRepository;
import com.pdt.data.OrderJpaRepository;
import com.pdt.entities.EntityId;
import com.pdt.entities.Order;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@ApplicationService
@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private final OrderJpaRepository orderJpaRepository;

    public OrderRepositoryImpl(OrderJpaRepository orderJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
    }

    @Override
    public List<Order> findAllByUsername(String username) {
        return orderJpaRepository.findAllByUsername(username);
    }

    @Override
    public Order save(Order order) {
        return orderJpaRepository.save(order);
    }

    @Override
    public void delete(Order orderToDelete) {
        orderJpaRepository.delete(orderToDelete);
    }

    @Override
    public Optional<Order> findById(EntityId id) {
        return orderJpaRepository.findById(id);
    }

}
