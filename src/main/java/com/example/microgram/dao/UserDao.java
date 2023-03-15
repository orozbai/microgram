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

    public List<User> CheckUserFromEmail() {
        String sql = "select * from users where email like 'user@example.com'";
        var user = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
        if (user != null) {
            return user;
        } else {
            // доработать логику возвращения если пользователя нету
            return null;
        }
    }
}
