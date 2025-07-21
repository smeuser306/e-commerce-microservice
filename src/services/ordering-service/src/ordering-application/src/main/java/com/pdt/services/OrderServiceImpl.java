package com.pdt.services;

import com.pdt.contracts.infrastructure.EmailService;
import com.pdt.contracts.persistence.OrderRepository;
import com.pdt.entities.EntityId;
import com.pdt.entities.Order;
import com.pdt.mappings.OrderMapper;
import com.pdt.models.*;
import com.pdt.services.contracts.OrderService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final EmailService emailService;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository, EmailService emailService, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.emailService = emailService;
        this.orderMapper = orderMapper;
    }

    @Override
    public List<OrdersVm> getOrders(String username) {
        List<Order> orders = orderRepository.findAllByUsername(username);
        List<OrdersVm> ordersVms = new ArrayList<>();
        for(Order order : orders){
            ordersVms.add(orderMapper.toOrdersVm(order));
        }
        return ordersVms;
    }
    @Override
    public EntityId checkout(CheckoutOrder checkoutOrder) {
        Order order = orderMapper.toOrder(checkoutOrder);
        Order savedOrder = orderRepository.save(order);
        sendMail(savedOrder);
        return savedOrder.getId();
    }

    @Override
    public void deleteOrder(DeleteOrder deleteOrder) throws Exception {
        Order orderToDelete = orderRepository.findById(new EntityId(deleteOrder.id())).orElseThrow(() -> new EntityNotFoundException("Order not found"));
        orderRepository.delete(orderToDelete);
    }

    @Override
    public void updateOrder(UpdateOrder updateOrder) {
        Order orderToUpdate = orderRepository
                .findById(new EntityId(updateOrder.id()))
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
        Order order = orderMapper.toOrder(updateOrder);
        orderRepository.save(order);
    }

    private void sendMail(Order order) {
        try{
            Email email = new Email();
            email.setTo(order.getEmailAddress());
            email.setBody("Your order has been placed successfully!");
            email.setSubject("Order Placed Successfully");
            emailService.sendEmail(email);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
