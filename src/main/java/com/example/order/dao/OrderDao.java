package com.example.order.dao;

import com.example.order.model.OrderDto;
import com.example.order.model.OrderItems;
import com.example.order.model.Orders;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface OrderDao {

    int addOrder(UUID id, Orders order);

    default int addOrder(Orders order) {
        UUID id = UUID.randomUUID();
        return addOrder(id, order);
    }

    List<Orders> getAllOrders();
    List<OrderItems> getAllItems();

    int addItem(UUID  itemId, OrderItems items);

    default int addItem(OrderItems items) {
        UUID itemId = UUID.randomUUID();
        return addItem( itemId, items);
    }
        OrderDto setOrderStatus(Orders order, String status);
    Orders getOrderById(UUID id);
     ArrayList<OrderItems> getOrderItems(Orders order);
}
