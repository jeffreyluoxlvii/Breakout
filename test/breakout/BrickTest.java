package breakout;

import org.junit.jupiter.api.Test;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import static org.junit.jupiter.api.Assertions.*;

class BrickTest {
    Brick weak = new WeakBrick(10, 10);
    Brick medium = new MediumBrick(10, 10);
    Brick strong = new StrongBrick(10, 10);

    @Test
    public void testBroken() {
        assertEquals(false, strong.isBroken());
        assertEquals(false, medium.isBroken());
        assertEquals(false, weak.isBroken());
    }

    @Test
    public void testPosition() {
        assertEquals(10, weak.getX());
        assertEquals(10, weak.getY());
    }
}