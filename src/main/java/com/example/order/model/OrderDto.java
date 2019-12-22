package com.example.order.model;

import java.util.ArrayList;

public class OrderDto extends Orders{

    public ArrayList<OrderItems> getItems() {
        return items;
    }

    private final ArrayList<OrderItems> items;

    public OrderDto(Orders order, ArrayList<OrderItems> items){
        super(order.getId(), order.getOrderStatus().ordinal(), order.totalCost);
        this.items = items;
    }
}
