package com.pong.database;

import com.pong.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserManager {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    public void createUser(User user) {

        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }

        if (user.getEmail() == null || !validateEmail(user.getEmail())) {
            throw new IllegalArgumentException("Invalid email format.");
        }

        String hashedPassword = hashPassword(user.getPassword());

        String sql = "INSERT INTO users (username, password, email, firstName, lastName) VALUES (?, ?, ?, ?, ?)";


        try (Connection conn = DatabaseUtils.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false);

            stmt.setString(1, user.getUsername());
            stmt.setString(2, hashedPassword);
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getFirstName());
            stmt.setString(5, user.getLastName());


            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {

                conn.commit();
                System.out.println("User created successfully.");
            } else {

                conn.rollback();
                System.out.println("Failed to create user.");
            }


        } catch (SQLException e) {
            System.err.println("Error during user creation: " + e.getMessage());
            throw new RuntimeException("Database error occurred during user creation", e);
        }

    }

private String hashPassword(String password) {

    return password;
}
    private boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    public User authenticateUser(String username, String password) {

        String sql = "SELECT user_id, username, password FROM users WHERE username = ?";

        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {


            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {

                    String storedPasswordHash = rs.getString("password");

                    String providedPasswordHash = hashPassword(password);


                    if (providedPasswordHash.equals(storedPasswordHash)) {

                        return new User.Builder()
                                .setId(rs.getInt("user_id"))
                                .setUsername(rs.getString("username"))
                                .setPassword(storedPasswordHash)
                                .build();
                    } else {
                        throw new IllegalArgumentException("Invalid password.");
                    }
                } else {
                    throw new IllegalArgumentException("User not found.");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error during authentication: " + e.getMessage(), e);
        }
    }

    public User getUser(int userId) {

        String sql = "SELECT user_id, username,email, firstName, lastName FROM users WHERE user_id = ?";


        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {


            stmt.setInt(1, userId);


            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    User user = new User.Builder()
                            .setId(rs.getInt("user_id"))
                            .setUsername(rs.getString("username"))
                            .setEmail(rs.getString("email"))
                            .setFirstName(rs.getString("firstName"))
                            .setLastName(rs.getString("lastName"))
                            .build();
                    return user;
                } else {
                    System.out.println("No user found with ID: " + userId);
                    return null;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving user with ID " + userId + ": " + e.getMessage());
            throw new RuntimeException("Database error occurred while fetching the user", e);
        }
    }
}