package com.example.microgram.service;

import com.example.microgram.dao.PublicationDao;
import com.example.microgram.entity.Publication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicationService {
    @Autowired
    private PublicationDao publicationDao;

    public List<Publication> getPublicationOfOtherUsers() {
        return publicationDao.getPublicationOfOtherUsers();
    }
}
