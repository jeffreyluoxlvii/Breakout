package breakout;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class LifeUpPowerup extends Powerup {
    public static final Paint myFill = Color.ORANGERED;

    public LifeUpPowerup(double x, double y) {
        super(x, y);
        this.setFill(myFill);
    }

    @Override
    public void usePowerUp(Scene scene, GameManager manager) {
        manager.addLife();
    }
}
