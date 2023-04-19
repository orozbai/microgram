package com.example.microgram.service;

import com.example.microgram.dao.CommentDao;
import com.example.microgram.dto.CommentDto;
import com.example.microgram.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentDao commentDao;
    public List<CommentDto> showCommentsFromPublication() {
        return commentDao.getCommentsFromPublication();
    }

    public void addComment(Long userId, Long postId, String text) {
        commentDao.addComment(userId, postId, text);
    }
}
