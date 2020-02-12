package breakout;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


public class MediumBrick extends Brick {
    public static final int MEDIUM_DURABILITY = 2;
    public static final Paint BRICK_COLOR = Color.YELLOW;

    public MediumBrick(double x, double y) {
        super(x, y, MEDIUM_DURABILITY, BRICK_COLOR);
    }

    public MediumBrick(double x, double y, double width, double height) {
        super(x, y, width, height, MEDIUM_DURABILITY, BRICK_COLOR);
    }
}
