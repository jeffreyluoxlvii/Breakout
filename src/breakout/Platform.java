package breakout;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Paint;

/**
 * The platform used in the breakout game.
 * @author roy, jeffrey
 */
public class Platform extends Rectangle {

    public static final double PLATFORM_WIDTH = 0.3;
    public static final double PLATFORM_HEIGHT = 0.02;
    public static final double PLATFORM_DISTANCE_FROM_BOTTOM = 0.1;
    public static final Paint PLATFORM_COLOR = Color.BLUEVIOLET;

    /**
     * Construct a new platform, given the game's width, height, and how long the platform should be in proportion.
     * @param gameWidth the game's width
     * @param gameHeight the game's height
     * @param widthRatio the proportion of the platform to game width
     */
    public Platform(double gameWidth, double gameHeight, double widthRatio) {
        super(gameWidth * widthRatio, gameHeight * PLATFORM_HEIGHT);
        this.setFill(PLATFORM_COLOR);
        this.setToStartingPosition(gameWidth, gameHeight);
        this.setId("platform");
    }

    /**
     * Move the platform horizontally by x.
     */
    public void move(double x) {
        this.setX(x - this.getWidth() / 2);
    }

    /**
     * Update the platform's width by x.
     */
    public void updateWidth(double x) {
        this.setWidth(this.getWidth() + x);
    }

    /**
     * Reset the platform to its starting position.
     */
    public void setToStartingPosition(double gameWidth, double gameHeight) {
        this.setX(gameWidth / 2 - this.getWidth() / 2);
        this.setY(gameHeight * (1 - PLATFORM_DISTANCE_FROM_BOTTOM));
    }

}
