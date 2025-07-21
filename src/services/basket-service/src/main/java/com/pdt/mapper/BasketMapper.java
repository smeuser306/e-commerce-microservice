package com.pdt.mapper;

import com.pdt.entities.BasketCheckout;
import com.pdt.events.BasketCheckoutEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BasketMapper {
    BasketCheckoutEvent toBasketCheckoutEvent(BasketCheckout basketCheckout);
}
