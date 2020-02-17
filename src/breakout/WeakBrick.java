package breakout;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


public class WeakBrick extends Brick {
    public static final int WEAK_DURABILITY = 1;
    public static final Paint BRICK_COLOR = Color.GREEN;

    /**
     * create brick that only needs to be hit one time
     * @param x position
     * @param y position
     */
    public WeakBrick(double x, double y) {
        super(x, y, WEAK_DURABILITY, BRICK_COLOR);
    }

    /**
     * create brick that only needs to be hit one time with a given width and height
     * @param x position
     * @param y position
     * @param width
     * @param height
     */
    public WeakBrick(double x, double y, double width, double height) {
        super(x, y, width, height, WEAK_DURABILITY, BRICK_COLOR);
    }
}
