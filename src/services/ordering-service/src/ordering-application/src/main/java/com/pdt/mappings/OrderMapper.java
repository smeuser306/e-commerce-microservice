package com.pdt.mappings;

import com.pdt.entities.Order;
import com.pdt.models.CheckoutOrder;
import com.pdt.models.OrdersVm;
import com.pdt.models.UpdateOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toOrder(CheckoutOrder checkoutOrder);
    CheckoutOrder toCheckoutOrder(Order order);
    Order toOrder(OrdersVm ordersVm);
    OrdersVm toOrdersVm(Order order);
    @Mapping(target = "id", ignore = true)
    Order toOrder(UpdateOrder updateOrder);
    @Mapping(target = "id", ignore = true)
    UpdateOrder toUpdateOrder(Order order);
}
