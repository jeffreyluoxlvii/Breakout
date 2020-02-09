package breakout;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Ball that the player wants to keep bouncing.
 */
public class Ball extends Circle {

    public static final double BALL_RADIUS = 5;
    public static final int NORMAL_BALL_SPEED = 200;
    public static final int SPEED_INCREASE = 40;

    private int xDirection;
    private int yDirection;
    private int velocity;

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
        velocity = NORMAL_BALL_SPEED;
        this.setId("ball");
    }

    public void moveLeft() {
        // I'm using the negative of Math.abs because I want to later add that the ball moves in different angles
        xDirection = - Math.abs(xDirection);
    }

    public void moveRight() {
        xDirection = Math.abs(xDirection);
    }

    public void moveUp() {
        yDirection = - Math.abs(yDirection);
    }

    public void moveDown() {
        yDirection = Math.abs(yDirection);
    }

    public int getXDirection() {
        return xDirection;
    }

    public int getYDirection() {
        return yDirection;
    }

    public int getVelocity() {
        return velocity;
    }

    public void move(double elapsedTime) {
        this.setCenterX(this.getCenterX() + velocity * this.getXDirection() * elapsedTime);
        this.setCenterY(this.getCenterY() + velocity * this.getYDirection() * elapsedTime);
    }

    public void increaseSpeed() {
        velocity += SPEED_INCREASE;
    }
}
