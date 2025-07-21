package com.pdt.mapping;

import com.pdt.events.BasketCheckoutEvent;
import com.pdt.models.CheckoutOrder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderingMapper {
    CheckoutOrder toCheckoutOrder(BasketCheckoutEvent basketCheckoutEvent);
}
