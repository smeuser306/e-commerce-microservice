package com.pdt.repositories;

import com.pdt.entities.Coupon;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class DiscountRepositoryImpl implements DiscountRepository {
    private final JdbcTemplate jdbcTemplate;

    public DiscountRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Coupon getDiscount(String productName) {
        String sql = "SELECT * FROM Coupon WHERE product_name = ?";
        return jdbcTemplate.query(sql, new CouponExtractor(), productName);
    }

    @Override
    public boolean createDiscount(Coupon coupon) {
        String sql = """
                INSERT INTO Coupon (product_name, description, amount) VALUES (?, ?, ?)
                """;
        int affectedRows = jdbcTemplate.update(sql, coupon.getProductName(), coupon.getDescription(), coupon.getAmount());
        return affectedRows > 0;
    }

    @Override
    public boolean updateDiscount(Coupon coupon) {
        return false;
    }

    @Override
    public boolean deleteDiscount(String productName) {
        return false;
    }

    private class CouponExtractor implements ResultSetExtractor<Coupon> {

        @Override
        public Coupon extractData(ResultSet rs) throws SQLException, DataAccessException {
            return mapCoupon(rs);
        }
    }

    private Coupon mapCoupon(ResultSet rs) throws SQLException {
        Coupon coupon = null;

        while (rs.next()) {
            if (coupon == null) {
                coupon = new Coupon();
                coupon.setId(rs.getInt("id"));
                coupon.setProductName(rs.getString("product_name"));
                coupon.setDescription(rs.getString("description"));
                coupon.setAmount(rs.getInt("amount"));
            }
        }
        if (coupon == null) {
            throw new EmptyResultDataAccessException(1);
        }
        return coupon;
    }
}
