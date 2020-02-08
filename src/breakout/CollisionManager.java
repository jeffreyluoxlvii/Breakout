package breakout;

import breakout.Ball;
import breakout.Platform;
import javafx.scene.Node;
import javafx.scene.shape.Shape;

import java.util.Iterator;

public class CollisionManager {

    // Handle collision between ball and platform
    public void handlePlatformCollision(Ball ball, Platform platform) {
        if(Shape.intersect(ball, platform).getBoundsInLocal().getWidth() != -1) {
            ball.moveUp();
        }
    }

    public boolean handleWallCollision(Ball ball) {
        // hit top wall
        if(ball.getCenterY() - ball.getRadius() <= 0) {
            ball.moveDown();
        }
        // hit bottom wall
        if(ball.getCenterY() - ball.getRadius() >= BreakoutGame.SIZE) {
            return true;
        }
        // hit right wall
        if(ball.getCenterX() + ball.getRadius() >= BreakoutGame.SIZE) {
            ball.moveLeft();
        }
        // hit left wall
        if(ball.getCenterX() - ball.getRadius() <= 0) {
            ball.moveRight();
        }
        return false;
    }

    public boolean handleBrickCollision(Ball ball, Iterator<Node> iterator) {
        boolean hitBrick = false;
        while(iterator.hasNext()) {
            Node node = iterator.next();
            if(node instanceof Brick) {
                Brick brick = (Brick) node;
                if (isBrickCollision(ball, brick)) {
                    hitBrick = true;
                    iterator.remove();
                }
            }
        }
        return hitBrick;
    }

    // Handle a collision between ball and a brick
    private boolean isBrickCollision(Ball ball, Brick brick) {
        if(Shape.intersect(ball, brick).getBoundsInLocal().getWidth() != -1) {
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
