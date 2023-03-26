package com.example.microgram.service;

import com.example.microgram.dao.CommentDao;
import com.example.microgram.dto.CommentDto;
import com.example.microgram.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentDao commentDao;
    public List<Comment> showCommentsFromPublication() {
        return commentDao.getCommentsFromPublication();
    }

    public CommentDto addComment(Long id, CommentDto commentDto) {
        return commentDao.addComment(id, commentDto);
    }
}
