package breakout;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


public class MediumBrick extends Brick {
    public static final Paint BRICK_COLOR = Color.YELLOW;

    public MediumBrick(double x, double y) {
        super(x, y, 2, BRICK_COLOR);
    }

    public MediumBrick(double x, double y, double width, double height) {
        super(x, y, width, height, 2, BRICK_COLOR);
    }
}
