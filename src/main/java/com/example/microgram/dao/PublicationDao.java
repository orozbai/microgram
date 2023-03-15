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

    public List<Publication> selectPublicationsForYourFeedBasedOnSubscriptionsToOtherUsers() {
        String sql = "select * from publications join subscriptions ON publication.user_id = subscriptions." +
                "followee_id where subscriptions.follower_id = 'my_user_id'";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Publication.class));
    }

    public List<Publication> checkPublicationFromLike() {
        String sql = "select * from likes where user_id = 'my_user_id' and publication_id = 'publication_id'";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Publication.class));
    }
}
