package breakout;

import javafx.scene.Node;

/**
 * This class represents an image or a node to be displayed.
 */
public abstract class Sprite {

    /** Current display node */
    public Node node;

    /** velocity vector x direction */
    public double velocityX = 0;

    /** velocity vector y direction */
    public double velocityY = 0;

    /**
     * Updates this sprite object's velocity.
     */
    public abstract void update();

    /**
     * Check if sprite collided into the other sprite.
     *
     * @param other - The other sprite.
     * @return
     */
    public boolean collide(Sprite other) {
        return false;
    }
}
