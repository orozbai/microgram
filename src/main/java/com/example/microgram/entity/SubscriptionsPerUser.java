package com.example.microgram.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SubscriptionsPerUser {
    private User whoIsSigning;
    private User whoIsSubscribedTo;
    private LocalDateTime eventDate;
}
