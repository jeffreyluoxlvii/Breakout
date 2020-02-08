package breakout;

import javafx.scene.text.Text;

// Class to keep track of scoring and lives
public class GameManager {
    private int lives;
    private int score;
    private Text myScore;
    private Text myLives;

    public static final double TEXT_DISTANCE_FROM_BOTTOM = 0.03;

    public GameManager() {
        // Game starts with 3 lives by default
        lives = 3;
        score = 0;
        myScore = new Text("SCORE: " + score);
        myScore.setX(0);
        myScore.setY(BreakoutGame.SIZE * (1 - TEXT_DISTANCE_FROM_BOTTOM));
        myScore.setId("score");
        myLives = new Text("LIVES: " + lives);
        myLives.setX(BreakoutGame.SIZE - 100);
        myLives.setY(BreakoutGame.SIZE * (1 - TEXT_DISTANCE_FROM_BOTTOM));
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
}
