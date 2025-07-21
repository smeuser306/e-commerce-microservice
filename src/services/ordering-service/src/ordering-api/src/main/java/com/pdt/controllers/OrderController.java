package com.pdt.controllers;

import com.pdt.models.CheckoutOrder;
import com.pdt.models.DeleteOrder;
import com.pdt.models.OrdersVm;
import com.pdt.models.UpdateOrder;
import com.pdt.services.contracts.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("{username}")
    public ResponseEntity<List<OrdersVm>> getOrders(@PathVariable("username") String username){
        return ResponseEntity.ok(orderService.getOrders(username));
    }

    @PostMapping("checkout")
    @Async
    public ResponseEntity<UUID>  checkoutOrder(@RequestBody CheckoutOrder checkoutOrder){
        return ResponseEntity.ok(orderService.checkout(checkoutOrder).getId());
    }

    @PostMapping("update")
    @Async
    public ResponseEntity<?> updateOrder(@RequestBody UpdateOrder updateOrder){
        orderService.updateOrder(updateOrder);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable UUID id){
        try {
            orderService.deleteOrder(new DeleteOrder(id));
        } catch (Exception e) {

        }
        return ResponseEntity.noContent().build();
    }

}
