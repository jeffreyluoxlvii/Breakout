package breakout;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Paint;

public class Platform extends Rectangle {

    public static final double PLATFORM_WIDTH = 0.3;
    public static final double PLATFORM_HEIGHT = 0.02;
    public static final double PLATFORM_DISTANCE_FROM_BOTTOM = 0.08;
    public static final Paint PLATFORM_COLOR = Color.BLUEVIOLET;

    public Platform(double gameWidth, double gameHeight) {
        super(gameWidth * PLATFORM_WIDTH, gameHeight * PLATFORM_HEIGHT);
        this.setFill(PLATFORM_COLOR);
        this.setX(gameWidth / 2 - this.getWidth() / 2);
        this.setY(gameHeight * (1 - PLATFORM_DISTANCE_FROM_BOTTOM));
        this.setId("platform");
    }

    public void move(double x) {
        this.setX(x - this.getWidth() / 2);
    }

}
