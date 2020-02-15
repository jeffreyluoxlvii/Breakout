package breakout;

import javafx.scene.Scene;
import javafx.scene.shape.Shape;

public abstract class Powerup {

    public abstract void usePowerUp(Scene scene, GameManager manager);

    public abstract void move(double elapsedTime);

    public abstract Shape getShape();

}
