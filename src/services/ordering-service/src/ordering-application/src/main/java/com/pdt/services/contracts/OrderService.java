package com.pdt.services.contracts;

import com.pdt.entities.EntityId;
import com.pdt.models.CheckoutOrder;
import com.pdt.models.DeleteOrder;
import com.pdt.models.OrdersVm;
import com.pdt.models.UpdateOrder;

import java.util.List;

public interface OrderService {
    List<OrdersVm> getOrders(String username);
    EntityId checkout(CheckoutOrder checkoutOrder);
    void deleteOrder(DeleteOrder deleteOrder) throws Exception;
    void updateOrder(UpdateOrder updateOrder);
}
