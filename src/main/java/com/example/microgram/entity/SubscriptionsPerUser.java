package com.example.microgram.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SubscriptionsPerUser {
    private String whoIsSigning;
    private String whoIsSubscribedTo;
    private LocalDateTime eventDate;
}
