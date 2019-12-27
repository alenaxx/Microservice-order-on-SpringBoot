package com.example.order.dao;

import com.example.order.model.OrderDto;
import com.example.order.model.OrderItems;
import com.example.order.model.OrderStatus;
import com.example.order.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository("postgres")
public class OrderDaoService implements OrderDao {
    private final JdbcTemplate jdbcTemplate;
   @Autowired
   public OrderDaoService(JdbcTemplate jdbcTemplate) {
       this.jdbcTemplate = jdbcTemplate;
   }

    @Override
    public int addOrder( UUID id,Orders order) {
        jdbcTemplate.update(
                "INSERT INTO orders ( id, orderStatus,totalCost) VALUES (?, ?, ?)",
                id,   order.getOrderStatus().ordinal(),order.getTotalCost()
        );
        return 0;
    }

    public List<Orders> getAllOrders() {
        final String sql ="SELECT id, orderStatus,totalCost FROM orders";
        return jdbcTemplate.query(sql,(resultSet ,i)-> {
            UUID id =UUID.fromString(resultSet.getString("id"));
            Integer orderStatus = resultSet.getInt("orderStatus");
            float totalCost = resultSet.getFloat("totalCost");
            return new Orders (id, orderStatus,totalCost);
        });
    }

    public List<OrderItems> getAllItems() {
        final String sql ="SELECT orderId, itemId,amount FROM orderItems";
        return jdbcTemplate.query(sql,(resultSet ,i)-> {
            UUID orderId =UUID.fromString(resultSet.getString("orderId"));
            UUID itemId =UUID.fromString(resultSet.getString("itemId"));
            int amount = resultSet.getInt("amount");
            return new OrderItems (orderId,itemId,amount);
        });
    }

    public void addItem(OrderItems item) {
        jdbcTemplate.update(
                "INSERT INTO orderItems(  itemId,orderId,amount) VALUES (?, ?, ?)",
                 item.getItemId(),item.getOrderId(), item.getAmount()
        );
    }


    @Override
    public OrderDto setOrderStatus(Orders order, String status) {
        switch (status) {
            case ("COLLECTING"):
                return putOrderStatus(order, OrderStatus.COLLECTING);
            case ("PAID"):
                return putOrderStatus(order, OrderStatus.PAID);
            case ("SHIPPING"):
                return putOrderStatus(order, OrderStatus.SHIPPING);
            case ("COMPLETED"):
                return putOrderStatus(order, OrderStatus.COMPLETED);
            case ("FAILED"):
                return putOrderStatus(order, OrderStatus.FAILED);
            case ("CANCELLED"):
                return putOrderStatus(order, OrderStatus.CANCELLED);
            default:

        }
        return null;
    }

    @Override
    public Orders getOrderById(UUID id) {
        String sql = "SELECT * FROM orders  WHERE id =?";
        Orders order = jdbcTemplate.queryForObject(sql, new Object[]{id},(resultSet, i) -> {
            UUID id1 = UUID.fromString(resultSet.getString("id"));
            Integer orderStatus = resultSet.getInt("orderStatus");
            float totalCost = resultSet.getFloat("totalCost");
            return new Orders (id1, orderStatus,totalCost);
        });
        return order;
    }

    private OrderDto putOrderStatus(Orders order, OrderStatus status)  {
         String sql = "UPDATE orders SET   orderStatus = ?  WHERE id =?";
        jdbcTemplate.update(sql,
                status.ordinal(),order.getId());
        order.setOrderStatus(status);
       // order.setOrderStatus(status);
        return new OrderDto(order, getOrderItems(order));
    }
    @Override
    public ArrayList<OrderItems> getOrderItems(Orders order){
        return selectItems(order.getId());
    }
    private ArrayList<OrderItems> selectItems(UUID id) {
        ArrayList<OrderItems> orderItems = new ArrayList<>();
        final String sql ="SELECT itemId, amount FROM orderItems WHERE orderId =?";
        List<Map<String, Object>> resultSet = jdbcTemplate
                .queryForList(sql, id);
        for (Map res : resultSet) {
            OrderItems orderItem = new OrderItems();
            orderItem.setItemId((UUID) (res.get("itemId")));
            orderItem.setAmount((int) (res.get("amount")));
            orderItem.setOrderId((UUID) (res.get("orderId")));
            orderItems.add(orderItem);
        }
        return orderItems;

    }
}
