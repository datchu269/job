package com.example.rabbitmqdemo.service;

import com.example.rabbitmqdemo.dao.CustomerDAO;
import com.example.rabbitmqdemo.dao.OrderDAO;
import com.example.rabbitmqdemo.dao.OrderItemDAO;
import com.example.rabbitmqdemo.entity.Customer;
import com.example.rabbitmqdemo.entity.Order;
import com.example.rabbitmqdemo.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;

@Service
public class ServiceService {
    @Autowired
    CustomerDAO customerDAO;
    @Autowired
    OrderDAO orderDAO;
    @Autowired
    OrderItemDAO orderItemDAO;

    @Transactional
    public Boolean insert() {
        try {
            Customer customer = Customer.builder()
                    .name("New Customer")
                    .email("customer@example.com")
                    .build();

            // Thêm khách hàng mới vào cơ sở dữ liệu
            Long customerId = customerDAO.insertCustomer(customer);

            // Kiểm tra xem customerId có được trả về không
            if (customerId == null) {
                throw new RuntimeException("Failed to insert customer");
            }

            // Tạo một đơn hàng mới và gán customerId của khách hàng
            Order order = Order.builder()
                    .customerId(customerId)
                    .orderDate(new Date())
                    .build();

            // Thêm đơn hàng mới vào cơ sở dữ liệu
            Long orderId = orderDAO.insertOrder(order);

            // Kiểm tra xem orderId có được trả về không
            if (orderId == null) {
                throw new RuntimeException("Failed to insert order");
            }

            // Tạo một mục đơn hàng mới và gán orderId của đơn hàng
            OrderItem orderItem = OrderItem.builder()
                    .orderId(orderId)
                    .productName("Product A")
                    .quantity(1)
                    .price(10.0)
                    .build();

            // Thêm mục đơn hàng mới vào cơ sở dữ liệu
            orderItemDAO.insertOrderItem(orderItem);


            // Trả về true nếu thêm thành công
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            // Nếu có lỗi xảy ra, rollback transaction và trả về false
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }
}
