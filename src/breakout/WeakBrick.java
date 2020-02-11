package breakout;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


public class WeakBrick extends Brick {
    public static final Paint BRICK_COLOR = Color.GREEN;

    public WeakBrick(double x, double y) {
        super(x, y, 1, BRICK_COLOR);
    }

    public WeakBrick(double x, double y, double width, double height) {
        super(x, y, width, height, 1, BRICK_COLOR);
    }
}
