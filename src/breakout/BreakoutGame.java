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

        root.getChildren().add(myPlatform);
        root.getChildren().add(myBall);

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
        handleCollision(myBall, myPlatform);
    }

    // Handle a collision between ball and a shape object
    private void handleCollision(Ball ball, Rectangle object) {
        if(Shape.intersect(ball, object).getBoundsInLocal().getWidth() != -1) {
            // hit was below the object
            if(ball.getCenterY() >= object.getY() - (object.getHeight() / 2)) {
                ball.changeYDirection();
                return;
            }
            // hit was above the object
            if(ball.getCenterY() <= object.getY() - (object.getHeight() / 2)) {
                ball.changeYDirection();
                return;
            }
            // hit was to the left of object
            if(ball.getCenterX() < object.getX()) {
                ball.changeXDirection();
                return;
            }
            // hit was to the right of object
            if(ball.getCenterX() > object.getX()) {
                ball.changeYDirection();
                return;
            }
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
