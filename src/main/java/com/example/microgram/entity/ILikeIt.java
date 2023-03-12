package com.example.microgram.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ILikeIt {
    private String whoMarkedThe;
    private String whatWasTheMark;
    private LocalDateTime stampDateTime;
}
