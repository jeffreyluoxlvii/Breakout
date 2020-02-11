package breakout;


import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;


public class LevelCreatorTest {
    @Test
    public void testBrickSetup() {
        List<Brick> bricks = LevelCreator.setupBricksForLevel("testOne", 80, 80);
        assertEquals(10, bricks.get(0).getWidth());
        assertEquals(10, bricks.get(1).getX());
        assertEquals(80 / 2.0 * LevelCreator.BRICKS_RATIO_TO_SCREEN ,bricks.get(1).getY());
    }

    @Test
    public void testBrickSetup2() {
        List<Brick> bricks = LevelCreator.setupBricksForLevel("testTwo", 40, 100);
        assertEquals(10, bricks.get(1).getWidth());
        assertEquals(100 * LevelCreator.BRICKS_RATIO_TO_SCREEN / 5, bricks.get(2).getY());
        assertEquals(20, bricks.get(2).getX());
    }

    // Checks that different types of bricks can be read
    @Test
    public void testBrickSetup3() {
        List<Brick> bricks = LevelCreator.setupBricksForLevel("testThree", 40, 100);
        assertEquals(1, bricks.get(0).getDurability());
        assertEquals(2, bricks.get(1).getDurability());
        assertEquals(3, bricks.get(2).getDurability());
    }
}
