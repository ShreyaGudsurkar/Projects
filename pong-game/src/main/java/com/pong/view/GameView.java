package com.pong.view;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.pong.model.GameModel;
import com.pong.model.User;

/**
 * GameView is responsible for rendering the game elements: paddles, ball, and scores.
 * It uses a coordinate system where (0,0) is at the top-left corner.
 */
public class GameView extends JPanel {
    // Background color of the game panel
    private Color backgroundColor = new Color(30, 30, 30); // Dark gray
    private User playerOne;
    private User playerTwo;
    // Reference to the game model for accessing game state
    private GameModel model;

    public GameView(User playerOne, User playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        // Set preferred size for consistent window dimensions
        setPreferredSize(new Dimension(800, 600));
        setBackground(backgroundColor);
    }

    /**
     * Updates the game model reference and triggers a repaint to reflect the new game state.
     *
     * @param model The GameModel containing the current game state
     */
    public void updateGameState(GameModel model) {
        this.model = model;
        repaint();  // Calls paintComponent to redraw the game elements

        // *** Added: Check if the game has ended ***
        if (model.isGameOver()) {
            showWinnerMessageAndStatistics(); // Show message and statistics when the game ends
        }
    }

    /**
     * *** Added: Displays a winner/loser message and then transitions to the ScoreStatistics panel. ***
     */
    private void showWinnerMessageAndStatistics() {
        int playerScore = model.player1Score;
        int opponentScore = model.player2Score;

        // Determine the winner
        String message = playerScore > opponentScore
                ? "Congratulations! You are the Winner!"
                : "Better luck next time!";

        // Show a dialog with the winner/loser message
        JOptionPane.showMessageDialog(
                this,
                message,
                "Game Over",
                playerScore > opponentScore ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.WARNING_MESSAGE
        );

        // Transition to the ScoreStatistics panel with user IDs
        showScoreStatistics(playerScore, opponentScore, playerOne, playerTwo);
    }

    /**
     * *** Added: Displays the ScoreStatistics panel. ***
     *
     * @param playerScore    The player's score
     * @param opponentScore  The opponent's score
     */
    private void showScoreStatistics(int playerScore, int opponentScore, User playerOne, User playerTwo) {
        removeAll();
        ScoreStatistics statsPanel = new ScoreStatistics(playerScore, opponentScore, playerOne, playerTwo);
        add(statsPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (model == null) return;

        // Enable anti-aliasing for smoother graphics
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Enable transparency
        g2d.setComposite(AlphaComposite.SrcOver);

        // Draw paddles
        g2d.setColor(Color.WHITE);
        // Player 1 paddle
        g2d.fillRoundRect(
                model.paddle1X, model.paddle1Y,
                model.paddleWidth, model.paddleHeight,
                10, 10
        );
        // Player 2 paddle
        g2d.fillRoundRect(
                model.paddle2X, model.paddle2Y,
                model.paddleWidth, model.paddleHeight,
                10, 10
        );

        // Draw ball with optional subtle deformation effect
        int ballWidth = model.ballSize;
        int ballHeight = model.ballSize;

        if (model.deformationFrames > 0) {
            // Calculate deformation based on ball speed
            double deformationFactor = 0.3;
            double speedMagnitude = Math.sqrt(model.ballXSpeed * model.ballXSpeed + model.ballYSpeed * model.ballYSpeed);
            double speedRatio = speedMagnitude / 10.0; // Normalize speed

            // Deform the ball slightly in the direction of movement
            if (Math.abs(model.ballXSpeed) > Math.abs(model.ballYSpeed)) {
                // Horizontal deformation
                ballWidth = (int) (model.ballSize * (1 + speedRatio * deformationFactor));
                ballHeight = (int) (model.ballSize * (1 - speedRatio * deformationFactor));
            } else {
                // Vertical deformation
                ballWidth = (int) (model.ballSize * (1 - speedRatio * deformationFactor));
                ballHeight = (int) (model.ballSize * (1 + speedRatio * deformationFactor));
            }

            // Ensure minimum size
            ballWidth = Math.max(ballWidth, model.ballSize);
            ballHeight = Math.max(ballHeight, model.ballSize);
        }

        // Center the ball's drawing position if it has changed size
        int drawBallX = model.ballX - (ballWidth - model.ballSize) / 2;
        int drawBallY = model.ballY - (ballHeight - model.ballSize) / 2;

        // Draw the ball
        g2d.fillOval(drawBallX, drawBallY, ballWidth, ballHeight);

        // Set font for scores
        g2d.setColor(Color.WHITE);
        Font scoreFont = new Font("Monospaced", Font.BOLD, 48);
        g2d.setFont(scoreFont);

        // Draw scores centered at the top
        String scoreText = model.player1Score + "       " + model.player2Score;
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(scoreText);
        int x = (model.width - textWidth) / 2;
        int y = fm.getAscent() + 30; // Position slightly below the top edge
        g2d.drawString(scoreText, x, y);

        // Draw center line
        // Use a semi-transparent gray color
        g2d.setColor(new Color(255, 255, 255, 100)); // RGBA, alpha set to 100 for transparency
        // Create a dashed stroke
        float[] dashPattern = {10, 10};
        g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashPattern, 0));
        g2d.drawLine(model.width / 2, 0, model.width / 2, model.height);
    }
}
