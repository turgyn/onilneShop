package com.example.task5.dao;

import com.example.task5.models.Product;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDAO {

    private final JdbcTemplate jdbcTemplate;

    public ProductDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> selectAll() {
        return jdbcTemplate.query(
                "select * from products",
                new BeanPropertyRowMapper<>(Product.class)
        );
    }

    public Product selectById(int id) {
        try {
            return jdbcTemplate.queryForObject(
                    "select * from products where id = ?",
                    new Object[]{id},
                    new BeanPropertyRowMapper<>(Product.class)
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void save(Product product) {
        jdbcTemplate.update(
                "insert into products(name, price, quantity) values(?, ?, ?)",
                product.getName(), product.getPrice(), product.getQuantity()
        );
    }

    public void update(Product product) {
        jdbcTemplate.update(
                "update products set name = ?, price = ?, quantity = ? where id = ?",
                product.getName(), product.getPrice(), product.getQuantity(), product.getId()
        );
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from products where id = ?", id);
    }
}
