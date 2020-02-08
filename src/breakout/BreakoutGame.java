package breakout;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Iterator;
import java.util.List;


/**
 * A basic example JavaFX program for the first lab.
 *
 * @author Robert C. Duvall
 */
public class BreakoutGame extends Application {

    public static final String TITLE = "Breakout";
    public static final String WIN_MESSAGE = "Congratulations, you winner!";
    public static final String LOSE_MESSAGE = "Maybe next time you will be a winner.";

    public static final int SIZE = 400;
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final Paint BACKGROUND = Color.AZURE;
    public static final int BALL_STARTING_X = 30;
    public static final int BALL_STARTING_Y = 300;

    // some things needed to remember during game
    private Stage myStage;
    private Scene myScene;
    private Platform myPlatform;
    private Ball myBall;
    private List<Brick> myBricks;
    private Timeline animation;
    private GameManager gameManager;
    private CollisionManager myCollisionManager;
    private Text myScore;
    private Text myLives;


    /**
     * Initialize what will be displayed and how it will be updated.
     */
    @Override
    public void start (Stage stage) {
        myStage = stage;
        // attach scene to the stage and display it
        myScene = setupGame(SIZE, SIZE, BACKGROUND, "levelOne");
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();
        // attach "game loop" to timeline to play it (basically just calling step() method repeatedly forever)
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    private void end() {
        myStage.setScene(endGame(SIZE, SIZE, BACKGROUND));
        myStage.show();
    }

    private Scene endGame(int width, int height, Paint background) {

        Group root = new Group();

        Text text = new Text();
        text.setText(LOSE_MESSAGE);
        text.setX(SIZE / 2 - text.getLayoutBounds().getWidth() / 2);
        text.setY(SIZE / 2 - text.getLayoutBounds().getHeight() / 2);

        root.getChildren().add(text);
        Scene scene = new Scene(root, width, height);
        return scene;

    }

    // Create the game's "scene": what shapes will be in the game and their starting properties
    Scene setupGame (int width, int height, Paint background, String path) {

        // create one top level collection to organize the things in the scene
        Group root = new Group();

        myCollisionManager = new CollisionManager();

        myPlatform = new Platform(width, height);
        myBall = new Ball(BALL_STARTING_X, BALL_STARTING_Y);
        myBricks = LevelCreator.setupBricksForLevel(path, width, height);
        gameManager = new GameManager();
        myScore = gameManager.getScore();
        myLives = gameManager.getLives();

        root.getChildren().add(myPlatform);
        root.getChildren().add(myBall);
        root.getChildren().addAll(myBricks);
        root.getChildren().add(myScore);
        root.getChildren().add(myLives);

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
    void step (double elapsedTime) {
        myBall.move(elapsedTime);

        // Check for collisions
        myCollisionManager.handlePlatformCollision(myBall, myPlatform);
        if(myCollisionManager.handleBrickCollision(myBall, ((Group)myScene.getRoot()).getChildren().iterator())) {
            gameManager.addScore(1);
        }

        if(myCollisionManager.handleWallCollision(myBall)) {
            gameManager.loseLife();
            if(gameManager.checkGameOver()) {
                end();
            }
            resetBall(myBall);
        }
    }

    // What to do each time a key is pressed
    private void handleKeyInput (KeyCode code) {
        // Pause / unpause game when space pressed
        if(code == KeyCode.SPACE) {
            if(animation.getStatus() == Animation.Status.RUNNING) {
                animation.pause();
            }
            else {
                animation.play();
            }
        }
        // Resets ball position
        if(code == KeyCode.R) {
            resetBall(myBall);
        }
        if(code == KeyCode.L) {
            gameManager.addLife();
        }
    }

    private void resetBall(Ball ball) {
        ball.setCenterX(BALL_STARTING_X);
        ball.setCenterY(BALL_STARTING_Y);
        ball.moveDown();
        ball.moveRight();
    }

    // What to do each time a key is pressed
    private void handleMouseInput (double x, double y) {
    }

    //What to do when mouse moves
    private void handleMouseMoved(double x, double y) {
        if(animation.getStatus() == Animation.Status.RUNNING) {
            myPlatform.move(x);
        }
    }

    /**
     * Start the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
}
