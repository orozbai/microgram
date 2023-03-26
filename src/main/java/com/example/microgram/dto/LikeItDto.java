package com.example.microgram.dto;

import com.example.microgram.entity.Publication;
import com.example.microgram.entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LikeItDto {
    private Integer user_id;
    private Integer publication_id;
    private User whoMarkedThe;
    private Publication whatWasTheMark;
    private LocalDateTime likeTime;
}
