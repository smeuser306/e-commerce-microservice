package com.pdt.controllers;

import com.pdt.entities.BasketCheckout;
import com.pdt.entities.ShoppingCart;
import com.pdt.events.BasketCheckoutEvent;
import com.pdt.mapper.BasketMapper;
import com.pdt.repositories.contracts.BasketRepository;
import com.pdt.services.DiscountGrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.pdt.configurations.RabbitMQConfig.BASKET_EXCHANGE;
import static com.pdt.configurations.RabbitMQConfig.BASKET_UPDATED_ROUTING_KEY;

@RestController
@RequestMapping("api/v1/basket")
public class BasketController {
    private final BasketRepository basketRepository;
    private final DiscountGrpcService discountGrpcService;
    private final RabbitTemplate rabbitTemplate;
    private final BasketMapper basketMapper;
    private final Logger logger;

    public BasketController(BasketRepository basketRepository, DiscountGrpcService discountGrpcService, RabbitTemplate rabbitTemplate, BasketMapper basketMapper) {
        this.basketRepository = basketRepository;
        this.discountGrpcService = discountGrpcService;
        this.rabbitTemplate = rabbitTemplate;
        this.basketMapper = basketMapper;
        this.logger = LoggerFactory.getLogger(getClass());
    }

    @GetMapping("{username}")
    @ResponseBody
    public ShoppingCart getBasket(@PathVariable String username) {
        var basketOption = basketRepository.getBasket(username);
        return basketOption.orElse(new ShoppingCart(username));
    }

    @PostMapping
    @ResponseBody
    public ShoppingCart updateBasket(@RequestBody ShoppingCart basket) {
        for (var product : basket.items()) {
            var coupon = discountGrpcService.getDiscount(product.getProductName());
            product.setPrice(product.getPrice() - coupon.getAmount());
            logger.info("coupon: {}", coupon);
        }
        return basketRepository.updateShoppingCart(basket);
    }

    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(@RequestBody BasketCheckout basketCheckout) {
        ShoppingCart basket = basketRepository.getBasket(basketCheckout.username()).orElse(null);
        if (basket == null) {
            return ResponseEntity.badRequest().build();
        }

        BasketCheckoutEvent eventMessage = basketMapper.toBasketCheckoutEvent(basketCheckout);
        eventMessage.setTotalPrice(basket.getTotalPrice());
        rabbitTemplate.convertAndSend(BASKET_EXCHANGE, BASKET_UPDATED_ROUTING_KEY, eventMessage);

        return ResponseEntity.accepted().build();
    }
}
