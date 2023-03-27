package com.example.microgram.service;

import com.example.microgram.dao.LikeItDao;
import com.example.microgram.dao.PublicationDao;
import com.example.microgram.dto.PublicationDto;
import com.example.microgram.entity.Publication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicationService {
    @Autowired
    private PublicationDao publicationDao;
    @Autowired
    private LikeItDao likeItDao;

    public List<Publication> getPublicationOfOtherUsers(Long userId) {
        return publicationDao.getPublicationOfOtherUsers(userId);
    }

    public List<Publication> selectAllPublications() {
        return publicationDao.selectAllPublications();
    }

    public PublicationDto addPublication(PublicationDto publicationDto) {
        var publication = Publication.builder()
                .imageLink(publicationDto.getImageLink())
                .description(publicationDto.getDescription())
                .publicationTime(publicationDto.getPublicationTime())
                .likes(publicationDto.getLikes())
                .user_id(publicationDto.getUser_id())
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

    public boolean likePublication(Long id, Long postId) {
        return likeItDao.likePublication(id, postId);
    }

    public boolean deleteComment(Long commentId) {
        publicationDao.deleteCommentById(commentId);
        return true;
    }
}
