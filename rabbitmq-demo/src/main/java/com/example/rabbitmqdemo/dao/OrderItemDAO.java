package com.example.rabbitmqdemo.dao;

import com.example.rabbitmqdemo.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderItemDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertOrderItem(OrderItem orderItem) {
        String sql = "INSERT INTO order_items (order_id, product_name, quantity, price) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, orderItem.getOrderId(), orderItem.getProductName(), orderItem.getQuantity(), orderItem.getPrice());
    }
}
