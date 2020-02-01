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
    public static final int NORMAL_BALL_SPEED = 50;
    public static final int SLOW_BALL_SPEED = 20;

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
        // Ball will initially shoot up and to the right.
        xDirection = 1;
        yDirection = 1;
    }

    public void changeXDirection() {
        xDirection = -xDirection;
    }

    public void changeYDirection() {
        yDirection = -yDirection;
    }

    public int getXDirection() {
        return xDirection;
    }

    public int getYDirection() {
        return yDirection;
    }
}
