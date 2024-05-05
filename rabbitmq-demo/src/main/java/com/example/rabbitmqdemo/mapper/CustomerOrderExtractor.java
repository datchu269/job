package com.example.rabbitmqdemo.mapper;

import com.example.rabbitmqdemo.entity.Customer;
import com.example.rabbitmqdemo.entity.Order;
import com.example.rabbitmqdemo.entity.OrderItem;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerOrderExtractor implements ResultSetExtractor<List<Customer>> {

    @Override
    public List<Customer> extractData(ResultSet rs) throws SQLException {
        Map<Long, Customer> customerMap = new HashMap<>();

        while (rs.next()) {
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
                Order order = findOrCreateOrder(customer, orderId);
                if (hasOrderItemData(rs)) {
                    OrderItem orderItem = createOrderItem(rs);
                    order.getOrderItems().add(orderItem);
                }
            }
        }

        return new ArrayList<>(customerMap.values());
    }

    private Order findOrCreateOrder(Customer customer, long orderId) {
        for (Order order : customer.getOrders()) {
            if (order.getId() == orderId) {
                return order;
            }
        }
        Order newOrder = Order.builder()
                .id(orderId)
                .orderItems(new ArrayList<>())
                .build();
        customer.getOrders().add(newOrder);
        return newOrder;
    }

    private boolean hasOrderItemData(ResultSet rs) throws SQLException {
        return rs.getLong("order_item_id") > 0;
    }

    private OrderItem createOrderItem(ResultSet rs) throws SQLException {
        return OrderItem.builder()
                .id(rs.getLong("order_item_id"))
                .productName(rs.getString("product_name"))
                .quantity(rs.getInt("quantity"))
                .price(rs.getDouble("price"))
                .build();
    }
}
