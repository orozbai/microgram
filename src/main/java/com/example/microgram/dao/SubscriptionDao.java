package com.example.microgram.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionDao extends BaseDao {
    public SubscriptionDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS subscriptions(" +
                "id SERIAL PRIMARY KEY," +
                "whoIsSigning INTEGER NOT NULL DEFAULT '0'," +
                "whoIsSubscribedTo INTEGER NOT NULL DEFAULT '0'," +
                "FOREIGN KEY (whoIsSubscribedTo) REFERENCES users(id) ON DELETE CASCADE," +
                "FOREIGN KEY (whoIsSigning) REFERENCES users(id) ON DELETE CASCADE );" +
                "INSERT INTO subscriptions (id, whoIsSigning, whoIsSubscribedTo) \n" +
                "VALUES \n" +
                "(100, '100', '300'),\n" +
                "(200, '200', '200'),\n" +
                "(300, '300', '100');\n");
    }
}
