package com.example.rabbitmqdemo.mapper;

import com.example.rabbitmqdemo.entity.Customer;
import com.example.rabbitmqdemo.entity.Order;
import com.example.rabbitmqdemo.entity.OrderItem;
import lombok.SneakyThrows;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class CustomerWithOrdersAndItemsRowMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        CustomerResultSetExtractor extractor = new CustomerResultSetExtractor();
        return extractor.extractData(rs);
    }
}

class CustomerResultSetExtractor implements ResultSetExtractor<Customer> {

    @SneakyThrows
    public Customer extractData(ResultSet rs) throws SQLException, DataAccessException {
        Customer customer = null;
        while (rs.next()) {
            if (customer == null) {
                customer = Customer.builder()
                        .id(rs.getLong("customer_id"))
                        .name(rs.getString("customer_name"))
                        .email(rs.getString("customer_email"))
                        .orders(new ArrayList<>())
                        .build();
            }
            Order order = Order.builder()
                    .id(rs.getLong("order_id"))
                    .customerId(rs.getLong("customer_id"))
                    .orderDate(rs.getDate("order_date"))
                    .orderItems(new ArrayList<>())
                    .build();
            OrderItem orderItem = OrderItem.builder()
                    .id(rs.getLong("order_item_id"))
                    .orderId(rs.getLong("order_item_order_id"))
                    .productName(rs.getString("order_item_product_name"))
                    .quantity(rs.getInt("order_item_quantity"))
                    .price(rs.getDouble("order_item_price"))
                    .build();
            order.addOrderItem(orderItem);
            customer.addOrder(order);
        }
        if (customer == null) {
            throw new Exception("User not found");
        }
        return customer;
    }
}

