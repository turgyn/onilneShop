package com.example.task5.dao;

import com.example.task5.models.Account;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountDAO {

    private final JdbcTemplate jdbcTemplate;

    public AccountDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Account> selectAll() {
        return jdbcTemplate.query("select * from accounts", new BeanPropertyRowMapper<>(Account.class));
    }

    public Account selectByUsername(String username) {
        try {
            return jdbcTemplate.queryForObject(
                    "select * from accounts where username = ?",
                    new Object[]{username},
                    new BeanPropertyRowMapper<>(Account.class)
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void save(Account account) {
        jdbcTemplate.update(
                "insert into accounts values(?, ?, ?)",
                account.getUsername(), account.getPassword(), account.getBalance()
        );
    }

    public void update(Account account) {
        jdbcTemplate.update(
                "update accounts set password = ?, balance = ? where username = ?",
                account.getPassword(), account.getBalance(), account.getUsername()
        );
    }

    public void updatePassword(String username, String password) {
        jdbcTemplate.update("update accounts set password = ?  where username = ?", password, username);
    }

    public void updateBalance(String username, int balance) {
        jdbcTemplate.update("update accounts set balance = ?  where username = ?", balance, username);
    }

    public void delete(String username) {
        jdbcTemplate.update("delete from accounts where username = ?", username);
    }
}