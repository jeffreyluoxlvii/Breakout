package breakout;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameManagerTest {

    @Test
    void testAddScore() {
        GameManager manager = new GameManager();
        manager.addScore(1);
        assertEquals("SCORE: 1", manager.getScore().getText());
    }

    @Test
    void testAddNegativeScore() {
        GameManager manager = new GameManager();
        manager.addScore(-1);
        assertEquals("SCORE: -1", manager.getScore().getText());
    }

    @Test
    void testAddLife() {
        GameManager manager = new GameManager();
        manager.addLife();
        assertEquals("LIVES: 4", manager.getLives().getText());
    }

    @Test
    void testLoseLife() {
        GameManager manager = new GameManager();
        manager.loseLife();
        assertEquals("LIVES: 2", manager.getLives().getText());
    }

    @Test
    void testCheckGameOverFalse() {
        GameManager manager = new GameManager();
        assertEquals(false, manager.checkGameOver());
    }

    @Test
    void testCheckGameOverTrue() {
        GameManager manager = new GameManager();
        manager.loseLife();
        manager.loseLife();
        manager.loseLife();
        assertEquals(true, manager.checkGameOver());
    }
}