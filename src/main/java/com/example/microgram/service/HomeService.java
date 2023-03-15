package com.example.microgram.service;

import com.example.microgram.dao.CustomerDao;
import com.example.microgram.entity.Customer;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Service
public class HomeService {
    private Connection connection;
    @Autowired
    private CustomerDao customerDao;

    private Connection getNewConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=orozbay233";
        return DriverManager.getConnection(url);
    }

    public String getDataSourceConnection() {
        DataSource dataSource = getDataSource();
        try (Connection conn = dataSource.getConnection()) {
            if (conn.isValid(1)) {
                return "All it's OK";
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    private DataSource getDataSource() {
        HikariConfig config = new HikariConfig();
        config.setUsername("postgres");
        config.setPassword("orozbay233");
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres?user=postgres&password=orozbay233");
        return new HikariDataSource(config);
    }

    public String connect() {
        try {
            init();
            return "Connection to the database was successful";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    private void init() throws SQLException {
        connection = getNewConnection();
    }

    private int executeUpdate(String query) throws SQLException {
        init();
        Statement statement = connection.createStatement();
        return statement.executeUpdate(query);
    }

    private void createCustomerTable() throws SQLException {
        String customerTableQuery = "create table customers (" +
                "id integer primary key," +
                "name varchar(40)," +
                "age integer)";
        String customerEntryQuery = "insert into customers(id, name, age)" +
                "values(73, 'Brain', 33)";
        executeUpdate(customerTableQuery);
        executeUpdate(customerEntryQuery);
    }

    public String shouldCreatTable() {
        try {
            createCustomerTable();
            connection.createStatement().execute("select * from customers");
            return "All it's OK";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String shouldSelectData() {
        try {
            String query = "select * from customers where name =?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, "Brain");

            if (ps.execute()) {
                ResultSet resultSet = ps.getResultSet();
                resultSet.next();
                int age = resultSet.getInt("age");
                return String.format("Age %s", age);
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public List<Customer> getCustomers() {
        return customerDao.getCustomers();
    }
}
