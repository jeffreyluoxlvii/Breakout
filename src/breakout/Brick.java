package breakout;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;


public abstract class Brick extends Rectangle {
    public static final double BRICK_WIDTH = 30;
    public static final double BRICK_HEIGHT = 15;
    public static final Paint[] BRICK_COLORS = new Paint[]{Color.GREEN, Color.YELLOW, Color.RED};
    private int durability;

    public Brick(double x, double y, int durability, Paint color) {
        super(x, y, BRICK_WIDTH, BRICK_HEIGHT);
        this.durability = durability;
        this.setFill(color);
    }

    public Brick(double x, double y, double width, double height, int durability, Paint color) {
        super(x, y, width, height);
        this.durability = durability;
        this.setFill(color);
    }

    public boolean isBroken() {
        return durability <= 0;
    }

    public int getDurability() {
        return durability;
    }

    public void hit(int damage) {
        durability -= damage;
        changeColor();
    }

    private void changeColor() {
        for(int i = 0; i < BRICK_COLORS.length; i++) {
            if(i + 1 == this.durability) {
                this.setFill(BRICK_COLORS[i]);
            }
        }
    }
}
