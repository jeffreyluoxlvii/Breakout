package breakout;

// Class to keep track of scoring and lives
public class GameManager {
    private int lives;
    private int score;

    public GameManager() {
        // Game starts with 3 lives by default
        lives = 3;
        score = 0;
    }

    public void addScore(int points) {
        score += points;
    }

    public void addLife() {
        lives += 1;
    }
    
    public void loseLife() {
        lives -= 1;
    }

    public boolean isGameOver() {
        return (lives <= 0);
    }

}
