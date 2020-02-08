package breakout;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;

public class PlatformExtenderPowerup implements Powerup {

    public static final int POWERUP_WIDTH = 50;
    public static final int POWERUP_HEIGHT = 50;
    public static final int POWERUP_EXTENSION_LENGTH = 20;
    private Rectangle myShape;

    public PlatformExtenderPowerup(int centerX, int topY) {
        myShape = new Rectangle(POWERUP_WIDTH, POWERUP_HEIGHT);
        myShape.setX(centerX - myShape.getWidth() / 2.0);
        myShape.setY(topY);
    }

    @Override
    public void usePowerUp(Scene scene) {
        for(Node n: ((Group)scene.getRoot()).getChildren()) {
            if(n instanceof Platform) {
                ((Platform)n).updateWidth(POWERUP_EXTENSION_LENGTH);
            }
        }
    }

}
