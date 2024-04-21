package com.example.rabbitmqdemo.dao;

import com.example.rabbitmqdemo.entity.Customer;
import com.example.rabbitmqdemo.mapper.CustomerOrderExtractor;
import com.example.rabbitmqdemo.mapper.CustomerOrderRowMapper;
import com.example.rabbitmqdemo.mapper.CustomerWithOrdersAndItemsRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class CustomerDAO {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Long insertCustomer(Customer customer) {
        String sql = "INSERT INTO customers (name, email) VALUES (:name, :email)";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", customer.getName());
        parameters.put("email", customer.getEmail());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(parameters), keyHolder);

        // Lấy id được tạo ra từ cơ sở dữ liệu
        Long customerId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        return customerId;
    }

    public List<Customer> getCustomersWithOrdersAndOrderItems() {
        String sql = "SELECT " +
                "c.id AS customer_id, c.name AS customer_name, c.email AS customer_email, " +
                "o.id AS order_id, o.customer_id AS order_customer_id, o.order_date, " +
                "oi.id AS order_item_id, oi.order_id AS order_item_order_id, oi.product_name AS order_item_product_name, " +
                "oi.quantity AS order_item_quantity, oi.price AS order_item_price " +
                "FROM customers c " +
                "LEFT JOIN orders o ON c.id = o.customer_id " +
                "LEFT JOIN order_items oi ON o.id = oi.order_id";

        return namedParameterJdbcTemplate.query(sql, new CustomerWithOrdersAndItemsRowMapper());
    }

    public List<Customer> getAllCustomersWithOrdersAndOrderItems() {
        String sql = "SELECT c.id as customer_id, c.name as customer_name, c.email as customer_email, " +
                "o.id as order_id, o.order_date, " +
                "oi.id as order_item_id, oi.product_name, oi.quantity, oi.price " +
                "FROM customers c " +
                "LEFT JOIN orders o ON c.id = o.customer_id " +
                "LEFT JOIN order_items oi ON o.id = oi.order_id";

        return namedParameterJdbcTemplate.query(sql, new CustomerOrderExtractor());
    }

}
