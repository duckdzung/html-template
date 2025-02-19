package com.example.dao;

import com.example.models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public User getUserById(String userId) throws SQLException {
        String query = "SELECT * FROM users WHERE userId = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet.getString("userId"), resultSet.getString("password"));
            }
        }
        return null;
    }

    public boolean addUser(User user) throws SQLException {
        String query = "INSERT INTO users (userId, password) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getUserId());
            statement.setString(2, user.getPassword());
            return statement.executeUpdate() > 0;
        }
    }

    public boolean updateUser(User user) throws SQLException {
        String query = "UPDATE users SET password = ? WHERE userId = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getPassword());
            statement.setString(2, user.getUserId());
            return statement.executeUpdate() > 0;
        }
    }

    public boolean deleteUser(String userId) throws SQLException {
        String query = "DELETE FROM users WHERE userId = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userId);
            return statement.executeUpdate() > 0;
        }
    }
}