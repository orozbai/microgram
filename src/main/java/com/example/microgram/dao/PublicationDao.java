package com.example.microgram.dao;

import com.example.microgram.entity.Publication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class PublicationDao extends BaseDao {

    public PublicationDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    public List<Publication> getPublicationOfOtherUsers(Long userId) {
        String sql = String.format("select * from publications where user_id <> '%s'", userId);
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Publication.class));
    }

    public List<Publication> selectPublicationsOtherUsers() {
        String sql = "select * from publications inner join subscriptions ON publications.user_id = subscriptions." +
                "whoIsSubscribedTo where subscriptions.whoIsSubscribedTo = 'my_user_id'";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Publication.class));
    }

    public List<Publication> checkPublicationFromLike(Long userId, Long publicationId) {
        String sql = String.format("select * from likes where user_id = '%s' and publication_id = '%s'", userId, publicationId);
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Publication.class));
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS publications(" +
                "id SERIAL PRIMARY KEY," +
                "imageLink TEXT," +
                "description TEXT," +
                "publicationTime TIMESTAMP NOT NULL DEFAULT '2000-00-00 10:10:10'," +
                "likes INTEGER NOT NULL DEFAULT '0'," +
                "user_id INTEGER NOT NULL DEFAULT '0'," +
                "comment_id INTEGER NOT NULL DEFAULT '0'," +
                "FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE," +
                "FOREIGN KEY (comment_id) REFERENCES comments(id) ON DELETE CASCADE);" +
                "INSERT INTO publications (id, imageLink, description, publicationTime, likes, user_id) \n" +
                "VALUES \n" +
                "(1, 'image link', 'some text', '2022-03-20 10:30:00', '0', '1'),\n" +
                "(2, 'image link', 'some text', '2022-04-20 10:40:00', '0', '2'),\n" +
                "(3, 'image link', 'some text', '2022-05-20 10:50:00', '0', '3');\n");
    }
}
