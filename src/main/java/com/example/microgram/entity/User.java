package com.example.microgram.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String accountName;
    private String email;
    private String password;
    private Integer postsCount;
    private Integer subscriptionsCount;
    private Integer followersCount;
    private String name;
    private String surname;
    private Integer publicationId;
    private Integer subscriptionId;
}
