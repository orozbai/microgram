package com.example.microgram.service;

import com.example.microgram.dao.UserDao;
import com.example.microgram.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public List<User> getUsersFromName() {
        return userDao.getUsersFromName();
    }

    public List<User> getUsersFromAccountName() {
        return userDao.getUsersFromAccountName();
    }

    public List<User> getUsersFromEmail() {
        return userDao.getUsersFromEmail();
    }

    public List<User> CheckUserFromEmail() {
        return userDao.CheckUserFromEmail();
    }
}
