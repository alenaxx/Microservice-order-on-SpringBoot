package com.example.order.dao;

import com.example.order.model.*;

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

    void addItem(OrderItems items);

    OrderDto setOrderStatus(Orders order, String status);

    Orders getOrderById(UUID id);

    ArrayList<OrderItems> getOrderItems(Orders order);
}
