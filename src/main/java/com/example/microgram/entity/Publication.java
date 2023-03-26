package com.example.microgram.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Publication {
    private String imageLink;
    private String description;
    private LocalDateTime publicationTime;
    private Integer likes;
}
