package breakout;

import javafx.scene.Scene;
import javafx.scene.shape.Shape;

public interface Powerup {

    public double getDropChance();

    public void usePowerUp(Scene scene);

    public void move(double elapsedTime);

    public Shape getShape();

}
