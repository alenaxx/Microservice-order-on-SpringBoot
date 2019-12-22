package com.example.order.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class OrderItems {
    private  UUID  itemId;
    private UUID orderId;
    private int amount;

    public UUID getItemId() {
        return  itemId;
    }
    public UUID getOrderId() {
        return  orderId;
    }
    public int getAmount() {
        return amount;
    }


    public OrderItems(){}

    public OrderItems(@JsonProperty(" itemId") UUID  itemId,
                      @JsonProperty("amount")  int amount) {
        this. itemId =  itemId;
        this.amount = amount;
    }

    public void setItemId(UUID itemId) {
        this.itemId=itemId;
    }
    public void setOrderId(UUID orderId) {
        this.orderId=orderId;
    }

    public void setAmount(int amount) {
        this.amount=amount;
    }


}
