package breakout;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Ball that the player wants to keep bouncing.
 *
 * @author Jeffrey Luo
 */
public class Ball extends Circle {

    public static final double BALL_RADIUS = 5;
    public static final int NORMAL_BALL_SPEED = 200;
    public static final int SLOW_BALL_SPEED = 40;

    private int xDirection;
    private int yDirection;

    /**
     * Creates a ball at the given x and y coordinates of the center of the circle.
     * @param x - x coordinate
     * @param y - y coordinate
     */
    public Ball(int x, int y) {
        super(x, y, BALL_RADIUS);
        this.setFill(Color.RED);
        xDirection = 1;
        yDirection = 1;
    }

    public void moveLeft() {
        xDirection = -1;
    }

    public void moveRight() {
        xDirection = 1;
    }

    public void moveUp() {
        yDirection = -1;
    }

    public void moveDown() {
        yDirection = 1;
    }

    public int getXDirection() {
        return xDirection;
    }

    public int getYDirection() {
        return yDirection;
    }
}
