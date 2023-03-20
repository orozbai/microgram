package com.example.microgram.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ILikeIt {
    private User whoMarkedThe;
    private Publication whatWasTheMark;
    private LocalDateTime stampDateTime;
}
