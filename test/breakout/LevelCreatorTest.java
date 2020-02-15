package breakout;


import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class LevelCreatorTest {
    @Test
    public void testBrickSetup() {
        List<Brick> bricks = LevelCreator.setupBricks("test1", 80, 80);
        assertEquals(10, bricks.get(0).getWidth());
        assertEquals(10, bricks.get(1).getX());
        assertEquals(80 / 2.0 * LevelCreator.BRICKS_HEIGHT_RATIO_TO_SCREEN + LevelCreator.BRICKS_STARTING_HEIGHT
                ,bricks.get(1).getY());
    }

    @Test
    public void testBrickSetup2() {
        List<Brick> bricks = LevelCreator.setupBricks("test2", 40, 100);
        assertEquals(10, bricks.get(1).getWidth());
        assertEquals(100 * LevelCreator.BRICKS_HEIGHT_RATIO_TO_SCREEN / 5 + LevelCreator.BRICKS_STARTING_HEIGHT,
                bricks.get(2).getY());
        assertEquals(20, bricks.get(2).getX());
    }

    // Checks that different types of bricks can be read
    @Test
    public void testBrickSetup3() {
        List<Brick> bricks = LevelCreator.setupBricks("test3", 40, 100);
        assertEquals(1, bricks.get(0).getDurability());
        assertEquals(2, bricks.get(1).getDurability());
        assertEquals(3, bricks.get(2).getDurability());
    }
}
