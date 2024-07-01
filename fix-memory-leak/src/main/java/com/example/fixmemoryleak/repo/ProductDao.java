package com.example.fixmemoryleak.repo;

import com.example.fixmemoryleak.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductDao {
    private final JdbcTemplate jdbcTemplate;

    @PersistenceContext
    private final EntityManager entityManager;

    public List<Long> saveAll(List<Product> products) {
        String query = "INSERT INTO product(name,price,description) values (?,?,?)";
        DataSource dataSource = jdbcTemplate.getDataSource();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
        ) {
            connection.setAutoCommit(false);
            for (Product product : products) {
                ps.setObject(1, product.getName());
                ps.setObject(2, product.getPrice());
                ps.setObject(3, product.getDescription());
                ps.addBatch();
                ps.clearParameters();
            }
            ps.executeBatch();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            List<Long> ids = new ArrayList<>();
            while (generatedKeys.next()) {
                ids.add(generatedKeys.getLong(1));
            }
            connection.commit();

            return ids;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public List<Long> saveOrUpdate(List<Product> products) throws SQLException {
        Session session = entityManager.unwrap(Session.class);
        Connection connection = session.doReturningWork(Connection::unwrap);

        String insertSql = "INSERT INTO product (name, price, description) VALUES (?, ?, ?)";
        String updateSql = "UPDATE product SET name = ?, price = ?, description = ? WHERE id = ?";

        List<Long> generatedIds = new ArrayList<>();
        try (PreparedStatement insertStmt = connection.prepareStatement(insertSql, PreparedStatement.RETURN_GENERATED_KEYS);
             PreparedStatement updateStmt = connection.prepareStatement(updateSql)) {
            int batchSize = 50;
            int count = 0;

            for (Product product : products) {
                if (product.getId() == null) {
                    insertStmt.setString(1, product.getName());
                    insertStmt.setDouble(2, product.getPrice());
                    insertStmt.setString(3, product.getDescription());
                    insertStmt.addBatch();
                } else {
                    updateStmt.setString(1, product.getName());
                    updateStmt.setDouble(2, product.getPrice());
                    updateStmt.setString(3, product.getDescription());
                    updateStmt.setLong(4, product.getId());
                    updateStmt.addBatch();
                    generatedIds.add(product.getId());
                }

                if (++count % batchSize == 0) {
                    insertStmt.executeBatch();
                    updateStmt.executeBatch();
                    connection.commit();

                    try (ResultSet generatedKeys = insertStmt.getGeneratedKeys()) {
                        while (generatedKeys.next()) {
                            generatedIds.add(generatedKeys.getLong(1));
                        }
                    }
                }
            }
            insertStmt.executeBatch();
            updateStmt.executeBatch();
            connection.commit();

            try (ResultSet generatedKeys = insertStmt.getGeneratedKeys()) {
                while (generatedKeys.next()) {
                    generatedIds.add(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }

        return generatedIds;
    }

}
