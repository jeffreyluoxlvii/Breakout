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
    private int normalvelocity;
    private int slowvelocity;

    /**
     * Creates a ball at the given x and y coordinates of the center of the circle.
     * @param x - x coordinate
     * @param y - y coordinate
     */
    public Ball(double x, double y) {
        super(x, y, BALL_RADIUS);
        this.setFill(Color.RED);
        xDirection = 1;
        yDirection = -1;
        this.velocity = NORMAL_BALL_SPEED;
        this.normalvelocity = NORMAL_BALL_SPEED;
        this.slowvelocity = NORMAL_BALL_SPEED / 2;
        this.setId("ball");
    }

    /**
     * Creates a ball at a given x and y and assigns it a velocity
     * @param x
     * @param y
     * @param velocity
     */
    public Ball(double x, double y, int velocity) {
        super(x, y, BALL_RADIUS);
        this.setFill(Color.RED);
        xDirection = 1;
        yDirection = -1;
        this.velocity = velocity;
        this.normalvelocity = velocity;
        this.slowvelocity = velocity / 2;
        this.setId("ball");
    }

    /**
     * Make the ball move left
     */
    public void moveLeft() {
        // I'm using the negative of Math.abs in case I want to make the ball move at different angles
        xDirection = - Math.abs(xDirection);
    }

    /**
     * Make the ball move right
     */
    public void moveRight() {
        xDirection = Math.abs(xDirection);
    }

    /**
     * Make the ball move up
     */
    public void moveUp() {
        yDirection = - Math.abs(yDirection);
    }

    /**
     * Make the ball move down
     */
    public void moveDown() {
        yDirection = Math.abs(yDirection);
    }

    /**
     * @return horizontal direction that the ball is moving in
     */
    public int getXDirection() {
        return xDirection;
    }

    /**
     * @return vertical direction that the ball is moving in
     */
    public int getYDirection() {
        return yDirection;
    }

    /**
     * @return velocity of the ball
     */
    public int getVelocity() {
        return velocity;
    }

    /**
     * Move the ball
     * @param elapsedTime time for the ball to move
     */
    public void move(double elapsedTime) {
        this.setCenterX(this.getCenterX() + velocity * this.getXDirection() * elapsedTime);
        this.setCenterY(this.getCenterY() + velocity * this.getYDirection() * elapsedTime);
    }

    /**
     * increase the ball's speed by SPEED_INCREASE
     */
    public void increaseSpeed() {
        velocity += SPEED_INCREASE;
    }

    /**
     * slow the ball down to half speed
     */
    public void halfSpeed() {
        velocity = slowvelocity;
    }

    /**
     * make the ball return to normal speed
     */
    public void fullSpeed() {
        velocity = normalvelocity;
    }
}
