package com.example.microgram.dao;

import com.example.microgram.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDao extends BaseDao {

    public UserDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    public List<User> getUsersFromName() {
        String sql = "select * from users where name like '%Boris%'";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public List<User> getUsersFromAccountName(String name) {
        String sql = String.format("select * from users where accountName like %s", name);
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public List<User> getUsersFromEmail(String email) {
        String sql = String.format("select * from users where email like '%s'", email);
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public User checkUserFromEmail(String email) {
        String sql = String.format("select * from users where email like '%s'", email);
        var user = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
        for (User value : user) {
            if (value.getAccountName().contains(email)) {
                return value;
            }
        }
        return null;
    }

    public User checkUserFromAccountName(String accountName) {
        String sql = String.format("select * from users where accountName like '%s'", accountName);
        var user = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
        for (User value : user) {
            if (value.getAccountName().contains(accountName)) {
                return value;
            }
        }
        return null;
    }

    public boolean login(String usernameOrEmail, String password) {
        if (usernameOrEmail.contains("@")) {
            User user = getUsersFromEmail(usernameOrEmail).get(0);
            return user.getEmail().contains(usernameOrEmail) || user.getPassword().contains(password);
        } else {
            User user = getUsersFromAccountName(usernameOrEmail).get(0);
            return user.getAccountName().contains(usernameOrEmail) || user.getPassword().contains(password);
        }
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS users (" +
                "id SERIAL PRIMARY KEY," +
                "accountName VARCHAR(40) NOT NULL DEFAULT 'account name'," +
                "email VARCHAR(40) NOT NULL DEFAULT 'admin@gmail.com'," +
                "password VARCHAR(40) NOT NULL DEFAULT 'password'," +
                "postsCount INTEGER NOT NULL DEFAULT '0'," +
                "subscriptionsCount INTEGER NOT NULL DEFAULT '0'," +
                "followersCount INTEGER NOT NULL DEFAULT '0'," +
                "name VARCHAR(40) NOT NULL DEFAULT 'name'," +
                "surname VARCHAR(40) NOT NULL DEFAULT 'surname' );" +
                "INSERT INTO users (id, accountName, email, password, postsCount, subscriptionsCount," +
                "followersCount, name, surname) \n" +
                "VALUES \n" +
                "(1, 'qwer', 'john@gmail.com', '123', '0', '0', '0', 'oroz', 'altyn'),\n" +
                "(2, 'qwer', 'emeli@gmail.com', '123', '0', '0', '0', 'aman', 'zoevich'),\n" +
                "(3, 'qwer', 'one@gmail.com', '123', '0', '0', '0', 'rus', 'rusovich');\n");
    }
}
