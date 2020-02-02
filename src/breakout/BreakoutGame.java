package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;


/**
 * A basic example JavaFX program for the first lab.
 *
 * @author Robert C. Duvall
 */
public class BreakoutGame extends Application {
    public static final String TITLE = "Breakout";
    public static final int SIZE = 400;
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final Paint BACKGROUND = Color.AZURE;

    // some things needed to remember during game
    private Scene myScene;
    private Platform myPlatform;
    private Ball myBall;
    private List<Brick> myBricks;


    /**
     * Initialize what will be displayed and how it will be updated.
     */
    @Override
    public void start (Stage stage) {
        // attach scene to the stage and display it
        myScene = setupGame(SIZE, SIZE, BACKGROUND);
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();
        // attach "game loop" to timeline to play it (basically just calling step() method repeatedly forever)
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    // Create the game's "scene": what shapes will be in the game and their starting properties
    private Scene setupGame (int width, int height, Paint background) {
        // create one top level collection to organize the things in the scene
        Group root = new Group();

        myPlatform = new Platform(width, height);
        myBall = new Ball(30, 300);
        myBricks = LevelCreator.setupBricksForLevel("levelOne", width, height);

        root.getChildren().add(myPlatform);
        root.getChildren().add(myBall);
        root.getChildren().addAll(myBricks);

        // create a place to see the shapes
        Scene scene = new Scene(root, width, height, background);
        // respond to input

        scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        scene.setOnMouseClicked(e -> handleMouseInput(e.getX(), e.getY()));
        scene.setOnMouseMoved(e -> handleMouseMoved(e.getX(), e.getY()));
        return scene;
    }

    // Change properties of shapes in small ways to animate them over time
    // Note, there are more sophisticated ways to animate shapes, but these simple ways work fine to start
    private void step (double elapsedTime) {
        myBall.setCenterX(myBall.getCenterX() + myBall.NORMAL_BALL_SPEED * myBall.getXDirection() * elapsedTime);
        myBall.setCenterY(myBall.getCenterY() + myBall.NORMAL_BALL_SPEED * myBall.getYDirection() * elapsedTime);

        // Check for collisions
        handlePlatformCollision(myBall, myPlatform);
        for(Iterator<Brick> iterator = myBricks.iterator(); iterator.hasNext();) {
            Brick currentBrick = iterator.next();
            if(isBrickCollision(myBall, currentBrick))
            {
                iterator.remove();
                ((Group)myScene.getRoot()).getChildren().remove(currentBrick);
            }
        }
        handleWallCollision(myBall);
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

    // Handle collision between ball and platform
    private void handlePlatformCollision(Ball ball, Platform platform) {
        if(Shape.intersect(ball, platform).getBoundsInLocal().getWidth() != -1) {
            ball.moveUp();
        }
    }

    private void handleWallCollision(Ball ball) {
        // hit top wall
        if(ball.getCenterY() - ball.getRadius() <= 0) {
            ball.moveDown();
        }
        // hit bottom wall
        if(ball.getCenterY() - ball.getRadius() >= myScene.getHeight()) {
            ball.setCenterX(30);
            ball.setCenterY(300);
        }
        // hit right wall
        if(ball.getCenterX() + ball.getRadius() >= myScene.getWidth()) {
            ball.moveLeft();
        }
        // hit left wall
        if(ball.getCenterX() - ball.getRadius() <= 0) {
            ball.moveRight();
        }
    }

    // What to do each time a key is pressed
    private void handleKeyInput (KeyCode code) {
    }

    // What to do each time a key is pressed
    private void handleMouseInput (double x, double y) {
    }

    //What to do when mouse moves
    private void handleMouseMoved(double x, double y) {
        myPlatform.setX(x - myPlatform.getWidth() / 2);
    }

    /**
     * Start the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
}
