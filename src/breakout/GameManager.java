package breakout;

import javafx.scene.text.Text;

import java.io.IOException;

// Class to keep track of scoring and lives and ball
public class GameManager {
    private int lives;
    private int score;
    private int level;
    private int gameHighScore;
    private Text myLevel;
    private Text myScore;
    private Text myLives;
    private Text highScore;
    private Text slowActive;
    private boolean canSlow;

    public static final double TEXT_DISTANCE_FROM_TOP = 0.05;
    public static final int STARTING_LIVES = 3;
    public static final String SLOW_READY = "AVAILABLE";
    public static final String SLOW_NOT_READY = "UNAVAILABLE";


    /**
     * GameManager constructor
     */
    public GameManager() {
        lives = STARTING_LIVES;
        score = 0;
        level = 0;
        gameHighScore = Scorer.getHighScore();
        myScore = new Text("SCORE: " + score);
        myScore.setX(20);
        myScore.setY(BreakoutGame.GAME_HEIGHT * TEXT_DISTANCE_FROM_TOP);
        myScore.setId("score");
        highScore = new Text("HIGH SCORE: " + gameHighScore);
        highScore.setX(20);
        highScore.setY(BreakoutGame.GAME_HEIGHT * TEXT_DISTANCE_FROM_TOP + 15);
        slowActive = new Text("SLOW: " + SLOW_NOT_READY);
        slowActive.setX(BreakoutGame.GAME_WIDTH / 2 - 30);
        slowActive.setY(BreakoutGame.GAME_HEIGHT * TEXT_DISTANCE_FROM_TOP + 15);
        slowActive.setId("slow");
        myLevel = new Text("LEVEL: " + level);
        myLevel.setX(BreakoutGame.GAME_WIDTH / 2 - 20);
        myLevel.setY(BreakoutGame.GAME_HEIGHT * TEXT_DISTANCE_FROM_TOP);
        myLives = new Text("LIVES: " + lives);
        myLives.setX(BreakoutGame.GAME_WIDTH - 80);
        myLives.setY(BreakoutGame.GAME_HEIGHT * TEXT_DISTANCE_FROM_TOP);
        myLives.setId("lives");
    }


    /**
     * restarts the game
     */
    public void restartGame() {
        lives = STARTING_LIVES;
        updateHighScore();
        score = 0;
        level = 0;
        updateLevel();
        updateLives();
        updateScore();
    }

    /**
     * Set the level of the game
     * @param level
     */
    public void setLevel(int level) {
        this.level = level;
        updateLevel();
    }

    /**
     * Add points to score
     * @param points -- uses parameter in case we want to implement that different blocks give different amount of score
     */
    public void addScore(int points) {
        score += points;
        updateScore();
    }

    /**
     * Add one life
     */
    public void addLife() {
        lives += 1;
        updateLives();
    }

    /**
     * Lose one life
     */
    public void loseLife() {
        lives -= 1;
        updateLives();
    }

    /**
     * @return whether the player lost all his lives
     */
    public boolean checkGameOver() {
        return (lives <= 0);
    }

    /**
     * update the lives display
     */
    private void updateLives() {
        myLives.setText("LIVES: " + lives);
    }

    /**
     * update the score display
     */
    private void updateScore() {
        myScore.setText("SCORE: " + score);
    }

    /**
     * update the high score display
     */
    public void updateHighScore() {
        gameHighScore = Math.max(gameHighScore, score);
        highScore.setText("HIGH SCORE: " + gameHighScore);
    }

    /**
     * @return text display of player's score
     */
    public Text getScore() {
        return myScore;
    }

    /**
     * @return text display of number of lives player has
     */
    public Text getLives() {
        return myLives;
    }

    /**
     * @return text display of the level
     */
    public Text getLevel() { return myLevel; }

    /**
     * @return text display of the high score
     */
    public Text getHighScore() { return highScore; }

    /**
     * @return level that player is playing on
     */
    public int getCurrentLevel() { return level; }

    /**
     * @return text display of whether player can use slow active
     */
    public Text getSlowActive() {
        return slowActive;
    }

    /**
     * update the text display of slow active
     */
    private void updateSlowActive() {
        if(canSlow) {
            slowActive.setText("SLOW: " + SLOW_READY);
        }
        else {
            slowActive.setText("SLOW: " + SLOW_NOT_READY);
        }
    }

    /**
     * update the level display
     */
    private void updateLevel() {
        myLevel.setText("LEVEL: " + level);
    }

    /**
     * go to next level
     */
    public void advanceLevel() {
        level++;
        updateLevel();
    }

    /**
     * toggle whether the player can use the slow active
     */
    public void toggleCanSlow() {
        canSlow = !canSlow;
        updateSlowActive();
    }

    /**
     * @return whether the player can use the slow active
     */
    public boolean getCanSlow() {
        return canSlow;
    }

    /**
     * @return current score of player
     */
    public int getCurrentScore() { return score; }

    /**
     * @return the high score
     */
    public int getGameHighScore() {
        return gameHighScore;
    }
}
