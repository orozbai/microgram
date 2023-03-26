package com.example.microgram.service;

import com.example.microgram.dao.CommentDao;
import com.example.microgram.dto.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentDao commentDao;

    public void commentsFromPublication() {
        //показывает комментарии к публикации метод должен принимать айди публикации
    }

    public CommentDto addComment(Long id, CommentDto commentDto) {
        return commentDao.addComment(id, commentDto);
    }
}
