package com.example.microgram.service;

import com.example.microgram.dao.UserDao;
import com.example.microgram.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    private Connection connection;

    private Connection getNewConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=orozbay233";
        return DriverManager.getConnection(url);
    }

    private void init() throws SQLException {
        connection = getNewConnection();
    }

    private void executeUpdate(String query) throws SQLException {
        init();
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    private void createUsersTable() throws SQLException {
        String customerTableQuery = "create table users (" +
                "id serial primary key," +
                "accountName varchar(30)," +
                "email varchar(30)," +
                "password varchar(20)," +
                "postsCount integer," +
                "subscriptionsCount integer," +
                "followersCount integer," +
                "name varchar(20)," +
                "surname varchar(20))";
        String customerEntryQuery = "insert into publications(id, accountName, email, password, postsCount," +
                "subscriptionsCount, followersCount, name, surname)" +
                "values(1, 'ARK', 'example@example.com', 'password123', 4, 5, 3, 'orozbai', 'altynbekov')";
        executeUpdate(customerTableQuery);
        executeUpdate(customerEntryQuery);
    }

    public String shouldCreateTableUsers() {
        try {
            createUsersTable();
            connection.createStatement().execute("select * from users");
            return "All it's OK";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public List<User> getUsersFromName() {
        return userDao.getUsersFromName();
    }

    public List<User> getUsersFromAccountName() {
        return userDao.getUsersFromAccountName();
    }

    public List<User> getUsersFromEmail() {
        return userDao.getUsersFromEmail();
    }

    public String checkUserFromEmail(String email) {
        return userDao.checkUserFromEmail(email).getEmail();
    }

    public String checkUserFromAccountName(String accountName) {
        return userDao.checkUserFromAccountName(accountName).getAccountName();
    }
}
