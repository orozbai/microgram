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

    public List<User> getUserFromAccountName(String name) {
        return userDao.getUsersFromAccountName(name);
    }

    public List<User> getUserFromEmail(String email) {
        return userDao.getUsersFromEmail(email);
    }

    public String checkUserFromEmail(String email) {
        return userDao.checkUserFromEmail(email).getEmail();
    }

    public String checkUserFromAccountName(String accountName) {
        return userDao.checkUserFromAccountName(accountName).getAccountName();
    }

    public boolean login(String usernameOrEmail, String password) {
        return userDao.login(usernameOrEmail, password);
    }
}
