**Pong Game**

A Java-based two-player Pong game that recreates the classic table tennis experience with modern features, including customizable settings, user profiles, and score tracking.

**Table of Contents**

1\. [Introduction](#introduction)

2\. [Features](#features)

3\. [How Does the Game Work](#_top)

4\. [Game States and Their Functions](#_top)

5\. [How the States Transition](#_top)

6\. [Setup Instructions](#setup-instructions)

7\. [Usage](#usage)

8\. [Technical Details](#technical-details)

**Introduction:** The Pong Game is a simple yet engaging two-player game where players control paddles to hit a bouncing ball. This project highlights foundational game development concepts and serves as a stepping stone towards exploring advanced mechanics.

**Features:**

- **Two-Player Local Multiplayer:** Play against a friend using keyboard controls.
- **Customizable Settings:** User can select paddle and ball colors, set score limit, and enable sound.
- **User Authentication:** User can create an account and will be authenticated before the game starts.
- **Score Statistics:** User can view their score history.
- **Physics Engine:** Ball movement and paddle collision physics, including a deformation effect on the ball to enhance realism.
- **Sound Effects:** Optional audio for paddle hit.

**How Does the Game Work?**

The game transitions through various game states to manage the flow of gameplay and user interaction. Below are the states in the game and their respective functions:

**Game States and Their Functions**

| Game State | Main Function |
| --- | --- |
| START | Displays the game intro screen. |
| Sign Up | Players create accounts by providing a username, email, and password. |
| Login | Players log in using their username and password. |
| SETTINGS | Allows customization, including:<br><br>\-Ball colors<br><br>\-Paddle colors<br><br>\-Score limits  <br>\-Sound settings |
| GAMEPLAY | Main game state where:  <br>players control paddles, and the ball moves with physics.<br><br>\- Tracks and displays the score.<br><br>\- Ends when a player reaches the score limit. |
| PAUSE | Provides pause functionality with options to:<br><br>\- Resume gameplay.<br><br>\- Restart the game. |
| GAME_OVER | Displays the final scores and announces the winner.<br><br>\-Shows the history of 2 players and the final score.<br><br>\- Options to exit the game or return to the main menu. |

**How the States Transition**

1\. **From START to MAIN_MENU**: After the intro screen, the player is presented with the main menu.

2\. **From MAIN_MENU to GAMEPLAY**: Selecting a mode begins the game in the chosen mode.

3\. **During GAMEPLAY**: Players can pause the game, adjust settings, or quit.

4\. **From GAMEPLAY to GAME_OVER**: The game ends when a player reaches the predefined score limit.

5\. **From GAME_OVER**: Players can choose to restart the game or return to the main menu.

**Setup Instructions**

**Prerequisites:**

• Java Development Kit (JDK) 17

• MySQL database

**Steps**:

1. Clone the repository:

- git clone <https://github.com/your-repo/PongGame.git>
- cd PongGame

1. Set up the database:

- Go to the configuration folder and run the run_sql_script.sh script.
- Enter your MySQL root password when prompted.
- Run the sql file configuration > mysql-setup.sql to setup the pongdb database

1. Build the project
2. Run the application

**Usage**

1. **Launch the Game**: Run the main file to start the game.
2. **Login/Signup:**

- When you start a game, you’ll be taken to the Login Screen.
- Enter your credentials if you’re an existing user or sign up to create a new account. (you can use the following user credentials inserted using the sql file: uname: , pwd:)
- The signup screen will ask for a username, email, and password.

1. **Settings Screen:**
    - Customize the ball and paddle colors and set the score limit.
    - You can also enable/disable sound.
    - Once done, click Start Game to enter the Game Screen.
2. **Gameplay:**

- Player 1 Controls: W (up) and S (down)
- Player 2 Controls: ↑ (up) and ↓ (down)
- The game ends when the score limit is reached. A game over dialog will appear, and upon clicking OK, the screen will show your score statistics.

1. **Score History:**

- After the game, you can view the score history for each player, including their current score, at the top of the Score History Screen.

**Technical Details:**

- **IDE:** IntelliJ IDEA
- **Build Tool:** Gradle
- **Database:** MySQL
- **Language Used:** Java
- **Frameworks:** Swing for GUI development.