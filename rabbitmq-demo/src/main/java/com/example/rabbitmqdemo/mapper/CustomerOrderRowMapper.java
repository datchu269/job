package com.example.rabbitmqdemo.mapper;

import com.example.rabbitmqdemo.entity.Customer;
import com.example.rabbitmqdemo.entity.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomerOrderRowMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Map<Long, Customer> customerMap = new HashMap<>();

        long customerId = rs.getLong("customer_id");
        Customer customer = customerMap.get(customerId);
        if (customer == null) {
            customer = Customer.builder()
                    .id(customerId)
                    .name(rs.getString("customer_name"))
                    .email(rs.getString("customer_email"))
                    .orders(new ArrayList<>())
                    .build();
            customerMap.put(customerId, customer);
        }

        long orderId = rs.getLong("order_id");
        if (orderId > 0) {
            Order order = Order.builder()
                    .id(orderId)
                    .orderDate(rs.getDate("order_date"))
                    .build();
            customer.getOrders().add(order);
        }

        return customer;
    }
}

