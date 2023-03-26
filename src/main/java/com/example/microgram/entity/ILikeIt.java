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
public class ILikeIt {
    private User whoMarkedThe;
    private Publication whatWasTheMark;
    private LocalDateTime likeTime;
}
