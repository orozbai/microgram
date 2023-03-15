package com.example.microgram.dao;

import com.example.microgram.entity.Publication;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PublicationDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Publication> getPublicationOfOtherUsers() {
        // my_user_id должен быть заменен на идентификатор текущего пользователя
        String sql = "select * from publication where user_id <> 'my_user_id'";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Publication.class));
    }
}
