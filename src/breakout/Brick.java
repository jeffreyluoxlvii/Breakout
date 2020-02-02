package breakout;

import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Brick extends Rectangle {
    public static final double BRICK_WIDTH = 30;
    public static final double BRICK_HEIGHT = 15;
    public static final Paint BRICK_COLOR = Color.GREEN;

    public Brick(double x, double y) {
        super(x, y, BRICK_WIDTH, BRICK_HEIGHT);
        this.setFill(BRICK_COLOR);
    }

    public Brick(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.setFill(BRICK_COLOR);
    }
}
