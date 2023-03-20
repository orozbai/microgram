package com.example.microgram.dao;

import com.example.microgram.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    public List<User> getUsersFromName() {
        String sql = "select * from users where name like '%Boris%'";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public List<User> getUsersFromAccountName() {
        String sql = "select * from users where accountName like '%ARK%'";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public List<User> getUsersFromEmail() {
        String sql = "select * from users where email like '%Boris@example.com%'";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public User checkUserFromEmail(String email) {
        String sql = String.format("select * from users where email like %s", email);
        var user = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
        for (User value : user) {
            if (value.getAccountName().contains(email)) {
                return value;
            }
        }
        return null;
    }

    public User checkUserFromAccountName(String accountName) {
        String sql = String.format("select * from users where accountName like %s", accountName);
        var user = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
        for (User value : user) {
            if (value.getAccountName().contains(accountName)) {
                return value;
            }
        }
        return null;
    }
}
