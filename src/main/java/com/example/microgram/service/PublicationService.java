package com.example.microgram.service;

import com.example.microgram.dao.PublicationDao;
import com.example.microgram.entity.Publication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Service
public class PublicationService {
    @Autowired
    private PublicationDao publicationDao;

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

    private void createPublicationTable() throws SQLException {
        String customerTableQuery = "create table publications (" +
                "id serial primary key," +
                "imageLink varchar(400)," +
                "description varchar(400)," +
                "publicationDateTime TIMESTAMP," +
                "likes integer)";
        String customerEntryQuery = "insert into publications(id, imageLink, description, publicationDateTime, likes)" +
                "values(1, 'https://www.example.com/png.1', 'some text..', '2022-05-01 12:30:00', 4)";
        executeUpdate(customerTableQuery);
        executeUpdate(customerEntryQuery);
    }

    public String shouldCreateTablePublication() {
        try {
            createPublicationTable();
            connection.createStatement().execute("select * from publication");
            return "All it's OK";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public List<Publication> getPublicationOfOtherUsers() {
        return publicationDao.getPublicationOfOtherUsers();
    }

    public List<Publication> selectPublicationsForYourFeedBasedOnSubscriptionsToOtherUsers() {
        return publicationDao.selectPublicationsForYourFeedBasedOnSubscriptionsToOtherUsers();
    }
}
