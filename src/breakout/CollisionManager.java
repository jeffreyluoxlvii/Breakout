package breakout;

import breakout.Ball;
import breakout.Platform;
import javafx.scene.Node;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CollisionManager {

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

    private static boolean isPowerupCollision(Powerup p, Platform platform) {
        return Shape.intersect(p.getShape(), platform).getBoundsInLocal().getWidth() != -1;
    }

    // Handle collision between ball and platform
    public static void handlePlatformCollision(Ball ball, Platform platform) {
        if(Shape.intersect(ball, platform).getBoundsInLocal().getWidth() != -1) {
            ball.moveUp();
        }
    }

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

    // Handle a collision between ball and a brick
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
