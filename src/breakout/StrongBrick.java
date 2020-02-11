package breakout;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


public class StrongBrick extends Brick {
    public static final Paint BRICK_COLOR = Color.RED;

    public StrongBrick(double x, double y) {
        super(x, y, 3, BRICK_COLOR);
    }

    public StrongBrick(double x, double y, double width, double height) {
        super(x, y, width, height, 3, BRICK_COLOR);
        this.setFill(BRICK_COLOR);
    }
}
