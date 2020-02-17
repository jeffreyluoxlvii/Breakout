package breakout;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 * Brick superclass
 */
public abstract class Brick extends Rectangle {
    public static final double BRICK_WIDTH = 30;
    public static final double BRICK_HEIGHT = 15;
    public static final Paint[] BRICK_COLORS = new Paint[]{Color.GREEN, Color.YELLOW, Color.RED};
    private int durability;

    /**
     * Constructor for bricks
     * @param x position
     * @param y position
     * @param durability amount of times brick can be hit
     * @param color color of brick
     */
    public Brick(double x, double y, int durability, Paint color) {
        super(x, y, BRICK_WIDTH, BRICK_HEIGHT);
        this.durability = durability;
        this.setFill(color);
    }

    /**
     * Constructor for brick that lets you specify width and height
     * @param x position
     * @param y position
     * @param width
     * @param height
     * @param durability amount of times brick can be hit
     * @param color color of brick
     */
    public Brick(double x, double y, double width, double height, int durability, Paint color) {
        super(x, y, width, height);
        this.durability = durability;
        this.setFill(color);
    }

    /**
     * @return whether the brick is broken (got hit too many times)
     */
    public boolean isBroken() {
        return durability <= 0;
    }

    /**
     * @return number of times a brick can be hit
     */
    public int getDurability() {
        return durability;
    }

    /**
     * hit a brick and remove some durability
     * @param damage amount of durability to remove
     */
    public void hit(int damage) {
        durability -= damage;
        changeColor();
    }

    /**
     * helper method that changes block color
     */
    private void changeColor() {
        for(int i = 0; i < BRICK_COLORS.length; i++) {
            if(i + 1 == this.durability) {
                this.setFill(BRICK_COLORS[i]);
            }
        }
    }
}
