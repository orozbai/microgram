package com.example.microgram.database;

import com.example.microgram.dao.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitDatabase {
    @Bean
    CommandLineRunner init(UserDao userDao, SubscriptionDao subscriptionDao, CommentDao commentDao,
                           PublicationDao publicationDao, LikeItDao likeItDao) {
        return (args) -> {
            userDao.createTable();
            subscriptionDao.createTable();
            publicationDao.createTable();
            commentDao.createTable();
            likeItDao.createTable();
        };
    }
}
