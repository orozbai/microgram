package com.example.microgram.service;

import com.example.microgram.dao.PublicationDao;
import com.example.microgram.dto.CommentDto;
import com.example.microgram.dto.PublicationDto;
import com.example.microgram.entity.Publication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicationService {
    @Autowired
    private PublicationDao publicationDao;

    public List<Publication> getPublicationOfOtherUsers(Long userId) {
        return publicationDao.getPublicationOfOtherUsers(userId);
    }

    public List<Publication> selectPublicationsSubscribedOtherUsers() {
        return publicationDao.selectPublicationsOtherUsers();
    }

    public PublicationDto addPublication(PublicationDto publicationDto) {
        var publication = Publication.builder()
                .imageLink(publicationDto.getImageLink())
                .description(publicationDto.getDescription())
                .publicationTime(publicationDto.getPublicationTime())
                .likes(publicationDto.getLikes())
                .user_id(publicationDto.getUser_id())
                .comment_id(publicationDto.getComment_id())
                .build();

        publicationDao.save(publication);
        return PublicationDto.from(publication);
    }

    public List<Publication> getPublicationById(Long userId) {
        return publicationDao.getPublicationById(userId);
    }

    public boolean deletePublicationById(Long id) {
        publicationDao.deleteById(id);
        return true;
    }
}
