package com.aspofirst.deliveryapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeliveryRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Delivery> getAllOrder() {
        return jdbcTemplate.query("SELECT * FROM order",
                BeanPropertyRowMapper.newInstance(Delivery.class));
    }

    public Delivery getOrderById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM order WHERE " +
                "id = ?", BeanPropertyRowMapper.newInstance(Delivery.class), id);
    }

    public int save(List<Delivery> orders) {
        orders.forEach(order -> jdbcTemplate
                .update("INSERT INTO order(name, rating) VALUES(?, ?)",
                        order.getStatus(),
                        order.getDate(),
                        order.getFrom(),
                        order.getZipCodeFrom(),
                        order.getTo(),
                        order.getZipCodeTo()
                ));

        return 1;
    }

    public int update(Delivery order) {
        return jdbcTemplate.update("UPDATE order SET name=?, rating=? WHERE id=?",
                order.getStatus(),
                order.getDate(),
                order.getFrom(),
                order.getZipCodeFrom(),
                order.getTo(),
                order.getZipCodeTo(),
                order.getId()
        );
    }

    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM order WHERE id=?", id);
    }
}
