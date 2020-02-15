package breakout;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class PlatformExtenderPowerup extends Powerup {

    public static final int POWERUP_EXTENSION_LENGTH = 20;
    public static final double DROP_CHANCE = 0.1;
    public static final Paint myFill = Color.BLUEVIOLET;

    public PlatformExtenderPowerup(double x, double y) {
        super(x, y);
        this.setFill(myFill);
    }

    @Override
    public void usePowerUp(Scene scene, GameManager manager) {
        for(Node n: ((Group)scene.getRoot()).getChildren()) {
            if(n instanceof Platform) {
                ((Platform)n).updateWidth(POWERUP_EXTENSION_LENGTH);
            }
        }
    }
}
