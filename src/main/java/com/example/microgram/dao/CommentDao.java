package com.example.microgram.dao;

import com.example.microgram.dto.CommentDto;
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

    public List<Comment> getCommentsFromPublication() {
        String sql = "select * from comments";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Comment.class));
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS comments(" +
                "id SERIAL PRIMARY KEY," +
                "commentText TEXT NOT NULL DEFAULT 'text'," +
                "commentTime TIMESTAMP NOT NULL DEFAULT '2000-01-01 10:10:10'," +
                "publication_id INTEGER," +
                "FOREIGN KEY (publication_id) REFERENCES publications(id) ON DELETE CASCADE);" +
                "INSERT INTO comments (id, commentText, commentTime, publication_id) \n" +
                "VALUES \n" +
                "(100, 'some text', '2022-03-20 10:30:00', '100'),\n" +
                "(200, 'some text', '2022-04-20 10:40:00', '200'),\n" +
                "(300, 'some text', '2022-05-20 10:50:00', '300');\n");
    }

    public CommentDto addComment(Long id, CommentDto commentDto) {
        String sql = "INSERT INTO comments (commentText, commentTime, publication_id) VALUES (?, ?, ?)";
        Object[] params = new Object[]{commentDto.getCommentText(), commentDto.getCommentTime(), id};
        jdbcTemplate.update(sql, params);
        return commentDto;
    }
}
