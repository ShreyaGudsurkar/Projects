package com.pong.view;

import com.pong.controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * WelcomeScreen provides the main menu for the Pong game.
 * It allows users to start the game by clicking a button.
 */
public class WelcomeScreen extends JPanel {
    private JFrame frame;

    /**
     * Constructor initializes the welcome screen layout and design.
     *
     * @param frame The JFrame to switch between screens
     */
    public WelcomeScreen(JFrame frame) {
        this.frame = frame;

        // Set preferred size to ensure the frame sizes correctly
        setPreferredSize(new Dimension(800, 600));
        setLayout(new GridBagLayout());
        setBackground(new Color(30, 30, 30)); // Match game background color

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20); // Add padding

        // Title label
        JLabel title = new JLabel("PONG");
        title.setFont(new Font("Verdana", Font.BOLD, 80));
        title.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(title, gbc);

        // Start button
        JButton startButton = new JButton("START GAME");
        startButton.setFont(new Font("Verdana", Font.PLAIN, 32));
        startButton.setFocusPainted(false);
        startButton.addActionListener(this::startGame);
        gbc.gridy = 1;
        add(startButton, gbc);
    }

    /**
     * Handles the transition to the game screen when the Start button is clicked.
     */
    private void startGame(ActionEvent e) {
        frame.getContentPane().removeAll(); // Clear the frame content
       // GameView view = new GameView();     // Create the game view
        //GameController controller = new GameController(view, frame); // Create the controller
        LoginScreen loginScreen = new LoginScreen(frame);
        //frame.add(view);                    // Add the game view to the frame
        frame.add(loginScreen);
       // frame.addKeyListener(controller);   // Add the controller as a key listener
        frame.revalidate();                 // Refresh the frame
        frame.pack();                       // Pack the frame to adjust to new content size
        frame.repaint();                    // Redraw the frame
        frame.requestFocus();               // Ensure the frame has focus for key events
    }
}