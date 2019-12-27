package com.example.order.api;

import com.example.order.model.OrderItems;
import com.example.order.model.Orders;
import com.example.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("orders")
@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(path = "/ord")
    public void addOrder(@RequestBody Orders order) {
        orderService.addOrder(order);
    }

    @GetMapping(path = "/ord")
    public List<Orders> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping(path = "/item")
    public List<OrderItems> getAllItems() {
        return orderService.getAllItems();
    }

    @PostMapping(path = "/item")
    public void addItem(@RequestBody OrderItems item) {
        orderService.addItem(item);
    }

    @GetMapping("/{id}/items")
    public ResponseEntity getOrderItems(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(orderService.getOrderItems(id));
    }

    @PutMapping("/{id}/status/{status}")
    public ResponseEntity setOrderStatus(@PathVariable("id") UUID id, @PathVariable("status") String status) {
        return ResponseEntity.ok(orderService.setOrderStatus(id, status));
    }

}