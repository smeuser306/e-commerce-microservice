package com.pdt.eventConsumer;

import com.pdt.contracts.persistence.OrderRepository;
import com.pdt.entities.EntityId;
import com.pdt.entities.Order;
import com.pdt.events.BasketCheckoutEvent;
import com.pdt.mapping.OrderingMapper;
import com.pdt.mappings.OrderMapper;
import com.pdt.models.CheckoutOrder;
import com.pdt.services.contracts.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class BasketCheckoutConsumer {
    private final OrderingMapper orderingMapper;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final OrderService orderService;

    public BasketCheckoutConsumer(OrderingMapper orderingMapper,
                                  OrderMapper orderMapper,
                                  OrderService orderService) {
        this.orderingMapper = orderingMapper;
        this.orderService = orderService;
    }

    @RabbitListener(
            queues = "#{@environment.getProperty('basketCheckoutQueue.name')}",
            containerFactory = "rabbitListenerContainerFactory"
    )
    public void handleBasketCheckoutEvent(BasketCheckoutEvent event) {
        try {
            CheckoutOrder checkoutOrder = orderingMapper.toCheckoutOrder(event);
            EntityId orderId = orderService.checkout(checkoutOrder);
            logger.info("Order with order id {} has been created successfully", orderId);
        } catch (Exception e) {
            logger.info("Failed to create order");
        }
    }
}
