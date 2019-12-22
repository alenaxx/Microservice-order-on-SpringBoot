package com.example.order.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Orders {

    private  UUID id;
    private OrderStatus orderStatus;
     float totalCost;

    public UUID getId() {
        return id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public Orders(@JsonProperty("id") UUID id,
                  @JsonProperty("totalCost") float totalCost) {
        this.id = id;
        this.orderStatus = OrderStatus.COLLECTING;
        this.totalCost = totalCost;
    }
    public Orders(UUID id,
                  Integer orderStatus,
                  float totalCost) {
        this.id = id;
        this.totalCost = totalCost;
        this.orderStatus = OrderStatus.values()[orderStatus];
    }

    public void setOrderStatus(OrderStatus status) {
        this.orderStatus=status;
    }


}