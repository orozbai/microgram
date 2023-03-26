package com.example.microgram.dao;

import com.example.microgram.entity.Comment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentDao extends BaseDao {

    public CommentDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    public List<Comment> getCommentsFromPublication(Long id) {
        String sql = "select * from comments where publication_id = '" + id + "'";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Comment.class));
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS comments(" +
                "id SERIAL PRIMARY KEY," +
                "commentText TEXT NOT NULL DEFAULT 'text'," +
                "commentTime TIMESTAMP NOT NULL DEFAULT '2000-00-00 10:10:10' );" +
                "INSERT INTO comments (id, commentText, commentTime) \n" +
                "VALUES \n" +
                "(1, 'some text', '2022-03-20 10:30:00'),\n" +
                "(2, 'some text', '2022-04-20 10:40:00'),\n" +
                "(3, 'some text', '2022-05-20 10:50:00');\n");
    }
}
