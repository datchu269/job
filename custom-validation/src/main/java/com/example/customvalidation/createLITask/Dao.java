//package com.example.customvalidation.createLITask;
//
//import com.example.customvalidation.entity.HI1Message;
//import com.example.customvalidation.entity.HI1Object;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.PreparedStatementCreator;
//import org.springframework.jdbc.support.GeneratedKeyHolder;
//import org.springframework.stereotype.Component;
//
//import java.sql.*;
//import java.util.Objects;
//
//@Component
//public class Dao {
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    GeneratedKeyHolder holder = new GeneratedKeyHolder();
//
//    public Long saveWarrantTransaction(HI1Message message) {
//        String query = "INSERT INTO warrant_transaction(trans_id, send_time, error_code, lea_id" +
//                ", receiver_id)" +
//                "VALUE (?, ?, ?, ?, ?)";
//        jdbcTemplate.update(
//                con -> {
//                    PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//                    statement.setString(1, );
//                    statement.setDate(2, );
//                    statement.setInt(3, );
//                    statement.setLong(4, );
//                    statement.setLong(5, );
//                    return statement;
//                }, holder);
//        return Objects.requireNonNull(holder.getKey()).longValue();
//    }
//
//    public Long saveObject(HI1Object object) {
//        String query = "INSERT INTO object(object_type, format_owner, version, generation, object_identify, " +
//                "country_code, owner_identifier, start_time, end_time, last_changed, created_at, warrant_transaction_id) " +
//                "VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//        jdbcTemplate.update(
//                con -> {
//                    PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//                    statement.setInt(1, );
//                    statement.setString(2, );
//                    statement.setString(3, );
//                    statement.setInt(4, );
//                    statement.setString(5, );
//                    statement.setString(6, );
//                    statement.setString(7, );
//                    statement.setDate(9, (Date) course.getCreateat());
//                    statement.setDate(8, (Date) course.getCreateat());
//                    statement.setDate(10, (Date) course.getCreateat());
//                    statement.setDate(11, );
//                    statement.setLong(12,);
//                    return statement;
//                }, holder);
//        return Objects.requireNonNull(holder.getKey()).longValue();
//    }
//
//    public Long saveLITaskObject(LITaskObject object) {
//        String query = "INSERT INTO li_task_object(status, desired_status, task_delivery_type, csp_id, " +
//                "content_detail, created_at, object_id)" +
//                " values (?, ?, ?, ?, ?, ?, ?)";
//        jdbcTemplate.update(
//                con -> {
//                    PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//                    statement.setInt(1, );
//                    statement.setInt(2, );
//                    statement.setInt(3, );
//                    statement.setString(4, );
//                    statement.setString(5, );
//                    statement.setDate(6, );
//                    statement.setLong(7, );
//                    return statement;
//                }, holder);
//        return Objects.requireNonNull(holder.getKey()).longValue();
//    }
//
//    public Long saveDeliveryInfo(DeliVeryInFo object) {
//        String query = "INSERT INTO delivery_info(liid, profile, provision_type, iri_id, cc_id, " +
//                "last_changed, created_at, object_id)" +
//                " values (?, ?, ?, ?, ?, ?, ?)";
//        jdbcTemplate.update(
//                con -> {
//                    PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//                    statement.setString(1, );
//                    statement.setInt(2, );
//                    statement.setInt(3, );
//                    statement.setLong(4, );
//                    statement.setLong(5, );
//                    statement.setDate(6, );
//                    statement.setDate(7, );
//                    statement.setLong(8, );
//                    return statement;
//                }, holder);
//        return Objects.requireNonNull(holder.getKey()).longValue();
//    }
//
//}
