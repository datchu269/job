package com.example.rabbitmqdemo.dao;

import com.example.rabbitmqdemo.entity.hi1.ObjectTable;
import com.example.rabbitmqdemo.mapper.ObjectRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ObjectTableDAO {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<ObjectTable> getAllObjectsWithDetails(String objectIdentify) {
        String sql = "SELECT o.id AS object_id, o.object_type, o.object_identify, " +
                "lt.id AS li_task_object_id, lt.status, " +
                "t.id AS target_id, t.identifier_type, " +
                "di.id AS delivery_info_id, di.liid, " +
                "da.id AS delivery_address_id, da.port " +
                "FROM objects o " +
                "LEFT JOIN li_task_object lt ON o.id = lt.object_id " +
                "LEFT JOIN target t ON o.id = t.object_id " +
                "LEFT JOIN delivery_info di ON o.id = di.object_id " +
                "LEFT JOIN delivery_address da ON di.id = da.delivery_info_id " +
                "WHERE o.object_identify = :objectIdentify";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("objectIdentify", objectIdentify);

        return jdbcTemplate.query(sql, parameters, new ObjectRowMapper());
    }

    // Add other methods as needed for CRUD operations, etc.
}
