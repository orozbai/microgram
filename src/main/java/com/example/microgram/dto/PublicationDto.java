package com.example.microgram.dto;

import com.example.microgram.entity.Publication;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class PublicationDto {
    private MultipartFile imageLink;
    private String description;
    private LocalDateTime publicationTime;
    private Integer likes;
    private Integer user_id;

    public static PublicationDto from(Publication publication) {
        return builder()
                .imageLink(publication.getImageLink())
                .description(publication.getDescription())
                .publicationTime(publication.getPublicationTime())
                .likes(publication.getLikes())
                .user_id(publication.getUser_id())
                .build();
    }
}
