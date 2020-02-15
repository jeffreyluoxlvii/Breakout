package breakout;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class LifeUpPowerup extends Powerup {

    public static final int POWERUP_WIDTH = 15;
    public static final int POWERUP_HEIGHT = 15;
    public static final int POWERUP_EXTENSION_LENGTH = 20;
    public static final double DROP_CHANCE = 0.1;
    public static final double VELOCITY = 50;
    public static final Paint myFill = Color.ORANGERED;
    private Rectangle myShape;

    public LifeUpPowerup(double centerX, double topY) {
        myShape = new Rectangle(POWERUP_WIDTH, POWERUP_HEIGHT);
        myShape.setX(centerX - myShape.getWidth() / 2.0);
        myShape.setY(topY);
        myShape.setFill(myFill);
    }

    @Override
    public void usePowerUp(Scene scene, GameManager manager) {
        manager.addLife();
    }

    @Override
    public void move(double elapsedTime) {
            myShape.setY(myShape.getY() + VELOCITY * elapsedTime);
    }

    @Override
    public Shape getShape() {
        return myShape;
    }
}
