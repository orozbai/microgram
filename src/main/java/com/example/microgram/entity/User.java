package com.example.microgram.entity;

import lombok.Data;

@Data
public class User {
    private String accountName;
    private String email;
    private String password;
    private Integer postsCount;
    private Integer subscriptionsCount;
    private Integer followersCount;
    private String name;
    private String surname;
}
