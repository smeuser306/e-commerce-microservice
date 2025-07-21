package com.pdt.data;

import com.pdt.annotations.ApplicationService;
import com.pdt.entities.EntityId;
import com.pdt.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderJpaRepository extends JpaRepository<Order, EntityId> {
    List<Order> findAllByUsername(String username);
}
