package breakout;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;


public abstract class Brick extends Rectangle {
    public static final double BRICK_WIDTH = 30;
    public static final double BRICK_HEIGHT = 15;
    private int durability;
    private Paint color;

    public Brick(double x, double y, int durability, Paint color) {
        super(x, y, BRICK_WIDTH, BRICK_HEIGHT);
        this.durability = durability;
        this.color = color;
        this.setFill(color);
    }

    public Brick(double x, double y, double width, double height, int durability, Paint color) {
        super(x, y, width, height);
        this.durability = durability;
        this.color = color;
        this.setFill(color);
    }

    public boolean isBroken() {
        return durability <= 0;
    }

    public int getDurability() {
        return durability;
    }

}
