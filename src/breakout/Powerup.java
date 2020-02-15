package breakout;

import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;

public abstract class Powerup extends Rectangle {
    public static final int POWERUP_WIDTH = 15;
    public static final int POWERUP_HEIGHT = 15;
    public static final double VELOCITY = 50;

    public Powerup(double x, double y) {
        super((x - POWERUP_WIDTH / 2.0), y, POWERUP_WIDTH, POWERUP_HEIGHT);
    }

    public abstract void usePowerUp(Scene scene, GameManager manager);

    public void move(double elapsedTime) {
        this.setY(this.getY() + VELOCITY * elapsedTime);
    }
}
