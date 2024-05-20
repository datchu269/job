package com.example.rabbitmqdemo.dao;

import com.example.rabbitmqdemo.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    //    public void insertOrder(Order order) {
//        String sql = "INSERT INTO orders (customer_id, order_date) VALUES (?, ?)";
//        jdbcTemplate.update(sql, order.getCustomerId(), order.getOrderDate());
//    }
    public Long insertOrder(Order order) {
        String sql = "INSERT INTO orders (customer_id, order_date) VALUES (?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(java.sql.Connection con) throws java.sql.SQLException {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, order.getCustomerId());
                ps.setDate(2, (Date) order.getOrderDate());
                return ps;
            }
        }, keyHolder);

        // Lấy id được tạo ra từ cơ sở dữ liệu
        Long customerId = keyHolder.getKey().longValue();
        return customerId;
    }

    public void saveAll(List<Order> orderList) {
        String sql = "INSERT INTO orders (customer_id, order_date) VALUES (:customerId, :orderDate)";

        List<Map<String, Object>> batchValues = new ArrayList<>(orderList.size());

        for (Order order : orderList) {
            Map<String, Object> params = new HashMap<>();
            params.put("customerId", order.getCustomerId());
            params.put("orderDate", new Date(order.getOrderDate().getTime()));
            batchValues.add(params);
        }

        namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
    }
}
