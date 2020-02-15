package breakout;

import javafx.scene.text.Text;

// Class to keep track of scoring and lives
public class GameManager {
    private int lives;
    private int score;
    private int level;
    public static final int HIGH_SCORE = 10000;
    private Text myLevel;
    private Text myScore;
    private Text myLives;
    private Text highScore;

    public static final double TEXT_DISTANCE_FROM_TOP = 0.05;
    public static final int STARTING_LIVES = 10;

    public GameManager(String levelName) {
        lives = STARTING_LIVES;
        score = 0;
        level = 0;
        myScore = new Text("SCORE: " + score);
        myScore.setX(20);
        myScore.setY(BreakoutGame.GAME_HEIGHT * TEXT_DISTANCE_FROM_TOP);
        myScore.setId("score");
        highScore = new Text("HIGH SCORE: " + HIGH_SCORE);
        highScore.setX(20);
        highScore.setY(BreakoutGame.GAME_HEIGHT * TEXT_DISTANCE_FROM_TOP + 15);
        myLevel = new Text("LEVEL: " + level);
        myLevel.setX(BreakoutGame.GAME_WIDTH / 2 - 20);
        myLevel.setY(BreakoutGame.GAME_HEIGHT * TEXT_DISTANCE_FROM_TOP);
        myLives = new Text("LIVES: " + lives);
        myLives.setX(BreakoutGame.GAME_WIDTH - 80);
        myLives.setY(BreakoutGame.GAME_HEIGHT * TEXT_DISTANCE_FROM_TOP);
        myLives.setId("lives");
    }

    public void addScore(int points) {
        score += points;
        updateScore();
    }

    public void addLife() {
        lives += 1;
        updateLives();
    }
    
    public void loseLife() {
        lives -= 1;
        updateLives();
    }

    public boolean checkGameOver() {
        return (lives <= 0);
    }

    private void updateLives() {
        myLives.setText("LIVES: " + lives);
    }

    private void updateScore() {
        myScore.setText("SCORE: " + score);
    }

    public Text getScore() {
        return myScore;
    }

    public Text getLives() {
        return myLives;
    }

    public Text getLevel() { return myLevel; }

    public Text getHighScore() { return highScore; }

    public int getCurrentLevel() { return level; }

    private void updateLevel() {
        myLevel.setText("LEVEL: " + level);
    }

    public void advanceLevel() {
        level++;
        updateLevel();
    }

    public int getCurrentScore() { return score; }
}
