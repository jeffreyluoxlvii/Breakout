package breakout;

import javafx.scene.Node;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class handles collisions between different types of objects, such as power-ups, the platform,
 * the ball, and the game's walls.
 * @author roy, jeffrey
 */
public final class CollisionManager {

    /**
     * Return a list of power-ups that have collided with the platform.
     * @param powerups the power-ups to consider
     */
    public static List<Powerup> handlePowerupCollisions(List<Powerup> powerups, Platform platform) {
        List<Powerup> collisionPowerups = new ArrayList<>();
        for(Iterator<Powerup> iter = powerups.iterator(); iter.hasNext();) {
            Powerup p = iter.next();
            if(isPowerupCollision(p, platform)) {
                collisionPowerups.add(p);
                iter.remove();
            }
        }
        return collisionPowerups;
    }

    /**
     * Return whether or not there is a collision between a power-up and a platform
     */
    private static boolean isPowerupCollision(Powerup p, Platform platform) {
        return Shape.intersect(p, platform).getBoundsInLocal().getWidth() != -1;
    }

    /**
     * Handle collision between ball and platform
     */
    public static void handlePlatformCollision(Ball ball, Platform platform) {
        if(Shape.intersect(ball, platform).getBoundsInLocal().getWidth() != -1) {
            ball.moveUp();
        }
    }

    /**
     * Handle collision between ball and walls. Return true iff the ball hits the bottom wall
     */
    public static boolean handleWallCollision(Ball ball) {
        // hit top wall
        if(ball.getCenterY() - ball.getRadius() <= 0) {
            ball.moveDown();
        }
        // hit bottom wall
        if(ball.getCenterY() - ball.getRadius() >= BreakoutGame.GAME_HEIGHT) {
            return true;
        }
        // hit right wall
        if(ball.getCenterX() + ball.getRadius() >= BreakoutGame.GAME_WIDTH) {
            ball.moveLeft();
        }
        // hit left wall
        if(ball.getCenterX() - ball.getRadius() <= 0) {
            ball.moveRight();
        }
        return false;
    }

    /**
     * Returns a list of the bricks that are colliding with the ball.
     */
    public static List<Brick> handleBrickCollision(Ball ball, Iterator<Node> iterator) {
        List<Brick> hitBricks = new ArrayList<>();
        while(iterator.hasNext()) {
            Node node = iterator.next();
            if(node instanceof Brick) {
                Brick brick = (Brick) node;
                if (isBrickCollision(ball, brick) && brick.isBroken()) {
                    hitBricks.add(brick);
                    iterator.remove();
                }
            }
        }
        return hitBricks;
    }

    /**
     * Handle a collision between ball and brick
     */
    private static boolean isBrickCollision(Ball ball, Brick brick) {
        if(Shape.intersect(ball, brick).getBoundsInLocal().getWidth() != -1) {
            // TODO: Ball needs a damage instance variable
            brick.hit(1);
            // hit was to the left of brick
            if(ball.getCenterX() < brick.getX()) {
                ball.moveLeft();
            }
            // hit was to the right of brick
            else if(ball.getCenterX() > brick.getX() + brick.getWidth()) {
                ball.moveRight();
            }
            // hit was below the brick
            else if(ball.getCenterY() > brick.getY() + (brick.getHeight() / 2)) {
                ball.moveDown();
            }
            // hit was above the brick
            else if(ball.getCenterY() < brick.getY() + (brick.getHeight() / 2)) {
                ball.moveUp();
            }
            return true;
        }
        return false;
    }

}
