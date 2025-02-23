package com.pong.model;

import java.util.ArrayList;
import java.util.List;

/**
 * GameModel represents the state of the game, including positions, speeds, and scores.
 * It holds all the data required to render the game and perform physics calculations.
 */
public class GameModel {
    // Game area dimensions
    public final int width;       // Width of the game panel
    public final int height;      // Height of the game panel

    // Paddle dimensions
    public final int paddleWidth; // Width of the paddles
    public final int paddleHeight; // Height of the paddles

    // Ball dimensions
    public final int ballSize;    // Diameter of the ball (since it's a circle)

    // Player 1 (left paddle) state
    public int paddle1X;          // X position of Player 1's paddle (constant)
    public int paddle1Y;          // Y position of Player 1's paddle (changes with movement)
    public int paddle1Speed;      // Vertical speed of Player 1's paddle (used in physics)
    public int player1Score;      // Score of Player 1

    // Player 2 (right paddle) state
    public int paddle2X;          // X position of Player 2's paddle (constant)
    public int paddle2Y;          // Y position of Player 2's paddle (changes with movement)
    public int paddle2Speed;      // Vertical speed of Player 2's paddle (used in physics)
    public int player2Score;      // Score of Player 2

    // Ball state
    public int ballX;             // X position of the ball
    public int ballY;             // Y position of the ball
    public double ballXSpeed;     // Horizontal speed of the ball
    public double ballYSpeed;     // Vertical speed of the ball
    public int deformationFrames; // Frames remaining for ball deformation effect

    // *** Added: Max score to determine when the game ends ***
    private final int maxScore; // The score needed to win the game

    // Add a field for score history
    private final List<String> scoreHistory = new ArrayList<>();


    /**
     * Constructor initializes the game model with default values.
     *
     * @param width        Width of the game panel
     * @param height       Height of the game panel
     * @param paddleWidth  Width of the paddles
     * @param paddleHeight Height of the paddles
     * @param ballSize     Size (diameter) of the ball
     * @param maxScore     The score required to win the game
     */
    public GameModel(int width, int height, int paddleWidth, int paddleHeight, int ballSize, int maxScore) {
        // Initialize game area dimensions
        this.width = width;
        this.height = height;

        // Initialize paddle and ball dimensions
        this.paddleWidth = paddleWidth;
        this.paddleHeight = paddleHeight;
        this.ballSize = ballSize;

        // Set the maximum score
        this.maxScore = maxScore;

        // Set initial positions and scores
        resetGame();
    }

    public int getMaxScore() {
        return maxScore;
    }

    /**
     * *** Added: Checks if the game is over based on player scores. ***
     *
     * @return true if either player has reached the max score, false otherwise.
     */
    public boolean isGameOver() {
        return player1Score >= maxScore || player2Score >= maxScore;
    }

    // Method to add a new game result to the history
    public void addScoreToHistory(String scoreRecord) {
        scoreHistory.add(scoreRecord);
    }

    /**
     * Resets the game state to initial values.
     * Positions paddles and ball to start positions and resets scores.
     */
    public void resetGame() {
        // Initialize paddle positions
        // Paddles are positioned at fixed x coordinates
        paddle1X = 20; // Left paddle (Player 1)
        paddle2X = width - paddleWidth - 20; // Right paddle (Player 2)

        // Center paddles vertically
        paddle1Y = height / 2 - paddleHeight / 2;
        paddle2Y = height / 2 - paddleHeight / 2;

        // Reset paddle speeds (used in physics calculations)
        paddle1Speed = 0;
        paddle2Speed = 0;

        // Reset player scores
        player1Score = 0;
        player2Score = 0;

        // Initialize ball position to center of the game area
        ballX = width / 2 - ballSize / 2;
        ballY = height / 2 - ballSize / 2;

        // Reset ball speeds to zero (will be set when the game starts)
        ballXSpeed = 0;
        ballYSpeed = 0;

        // Reset deformation frames for the ball (no deformation at start)
        deformationFrames = 0;
    }
}
