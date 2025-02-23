package com.pong;


import com.pong.database.ScoreManager;
import com.pong.database.UserManager;
import com.pong.model.Score;
import com.pong.model.User;
import com.pong.view.WelcomeScreen;

import java.sql.Timestamp;
import javax.swing.*;


public class Main {
    public static void main(String[] args) {

//        try (Connection connection = DatabaseUtils.getConnection()) {
//
//            Statement statement = connection.createStatement();
//
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
//
//
//            while (resultSet.next()) {
//                System.out.println("Name: " + resultSet.getString("userName"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        UserManager userManager = new UserManager();
        ScoreManager scoreManager = new ScoreManager();

        User newUser = new User.Builder()
                .setUsername("ryan_g")
                .setPassword("securepassword123")
                .setEmail("ryan.g@example.com")
                .setFirstName("ryan")
                .setLastName("G")
                .build();

        try {
//            userManager.createUser(newUser);
            //User user=userManager.getUser(3);
//            User user=userManager.authenticateUser("ryan_g", "securepassword123");
//            System.out.println(user.toString());

//            Score score = new Score.Builder()
//                    .setUserId(user.getId())
//                    .setScore(10)
//                    .setScorePoints(100)
//                    .setDatePlayed(new Timestamp(System.currentTimeMillis()))
//                    .setOpponentScore(8)
//                    .build();

            // Add the score to the database
//            scoreManager.addScore(score);

            // Retrieve and display the user's scores
//            System.out.println("\nScores for user ID " + user.getId() + ":");
//            for (Score s : scoreManager.getUserScores(user.getId())) {
//                System.out.println(s);
//            }


        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }


//        for (User user : userManager.getUser()) {
//            System.out.println("User: " + user.getUsername());
//        }

        // -------------
        // Render UI
        // -------------
        JFrame frame = new JFrame("Pong Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add the welcome screen to the frame
        frame.add(new WelcomeScreen(frame));

        // Pack the frame to respect preferred sizes of its components
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); // Center the window on the screen
        frame.setVisible(true);
    }
}