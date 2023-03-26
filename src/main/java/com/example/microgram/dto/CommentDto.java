package com.example.microgram.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {
    private String commentText;
    private LocalDateTime commentTime;
}
