package com.example.microgram.dao;

import com.example.microgram.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommentDao {
    private final JdbcTemplate jdbcTemplate;

    //выбрать все комментарии из определенной публикации
    public List<Comment> getCommentsFromPublication() {
        String sql = "select * from comments where publication_id = 1";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Comment.class));
    }
}
