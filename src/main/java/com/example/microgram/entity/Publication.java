package com.example.microgram.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Publication {
    private String imageLink;
    private String description;
    private LocalDateTime publicationTime;
    private Integer likes;
    private Integer user_id;
    private Integer comment_id;
}
