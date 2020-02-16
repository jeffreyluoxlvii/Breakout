package breakout;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class SlowPowerup extends Powerup {
    public static final Paint myFill = Color.YELLOWGREEN;

    public SlowPowerup(double x, double y) {
        super(x, y);
        this.setFill(myFill);
    }

    @Override
    public void usePowerUp(Scene scene, GameManager manager) {
        if(!manager.getCanSlow()) {
            manager.toggleCanSlow();
            manager.updateSlowActive();
        }
    }
}
