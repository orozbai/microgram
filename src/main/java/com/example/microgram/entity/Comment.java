package com.example.microgram.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment {
    private String commentText;
    private LocalDateTime commentTime;
}
