package breakout;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class LevelCreatorTest {

    @Test
    public void testBrickSetup() {
        LevelCreator levelCreator = new LevelCreator(1, BreakoutGame.TEST_PATH);
        List<Brick> bricks = levelCreator.setupBricks();
        assertEquals(400.0 / 8, bricks.get(0).getWidth());
        assertEquals(400.0 / 8, bricks.get(1).getX());
        assertEquals(400.0 / 2.0 * LevelCreator.BRICKS_HEIGHT_RATIO_TO_SCREEN + LevelCreator.BRICKS_STARTING_HEIGHT
                ,bricks.get(1).getY());
    }

    @Test
    public void testBrickSetup2() {
        LevelCreator levelCreator = new LevelCreator(2, BreakoutGame.TEST_PATH);
        List<Brick> bricks = levelCreator.setupBricks();
        assertEquals(400.0 / 4, bricks.get(1).getWidth());
        assertEquals(400.0 * LevelCreator.BRICKS_HEIGHT_RATIO_TO_SCREEN / 5 + LevelCreator.BRICKS_STARTING_HEIGHT,
                bricks.get(2).getY());
        assertEquals(400.0 / 4 * 2, bricks.get(2).getX());
    }

    // Checks that different types of bricks can be read
    @Test
    public void testBrickSetup3() {
        LevelCreator levelCreator = new LevelCreator(3, BreakoutGame.TEST_PATH);
        List<Brick> bricks = levelCreator.setupBricks();
        assertEquals(1, bricks.get(0).getDurability());
        assertEquals(2, bricks.get(1).getDurability());
        assertEquals(3, bricks.get(2).getDurability());
    }
}
