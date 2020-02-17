package breakout;

import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;

public abstract class Powerup extends Rectangle {
    public static final int POWERUP_WIDTH = 15;
    public static final int POWERUP_HEIGHT = 15;
    public static final double VELOCITY = 50;
    public static final double DROP_CHANCE = 0.1;

    /**
     * constructor for powerups
     * @param x position
     * @param y position
     */
    public Powerup(double x, double y) {
        super((x - POWERUP_WIDTH / 2.0), y, POWERUP_WIDTH, POWERUP_HEIGHT);
    }

    public abstract void usePowerUp(Scene scene, GameManager manager);

    /**
     * move the powerup towards the bottom of the screen
     * @param elapsedTime time for the powerup to move
     */
    public void move(double elapsedTime) {
        this.setY(this.getY() + VELOCITY * elapsedTime);
    }
}
