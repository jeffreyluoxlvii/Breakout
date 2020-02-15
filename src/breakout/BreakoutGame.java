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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
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

    public static final int GAME_WIDTH = 400;
    public static final int GAME_HEIGHT = 400;
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final Paint BACKGROUND = Color.AZURE;
    public static final int BALL_STARTING_X = 30;
    public static final int BALL_STARTING_Y = 300;

    public static final String TEST_PATH = "test";
    public static final String REAL_PATH = "level";

    // some things needed to remember during game
    private Stage myStage;
    private Scene myScene;
    private Platform myPlatform;
    private Ball myBall;
    private List<Brick> myBricks;
    private String myLevelPath;
    private List<Powerup> myPowerups;
    private Timeline animation;
    private GameManager myGameManager;
    private CollisionManager myCollisionManager;

    /**
     * Initialize what will be displayed and how it will be updated.
     */
    @Override
    public void start (Stage stage) {
        myStage = stage;
        // attach scene to the stage and display it

        myScene = setupGame(TEST_PATH);
        myScene.setOnKeyPressed(e -> handleKeyInputForNonLevelScreen(myScene, e.getCode()));
        myCollisionManager = new CollisionManager();

        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();
    }

    private void levelTransition() {
        animation.stop();
        TransitionScreen screen = new TransitionScreen(myGameManager);
        Scene scene = screen.getScene();
        scene.setOnKeyPressed(e -> handleKeyInputForNonLevelScreen(scene, e.getCode()));
        myStage.setScene(scene);
    }

    private void end(String displayText) {
        animation.stop();
        myStage.setScene(endGame(GAME_WIDTH, GAME_HEIGHT, BACKGROUND, displayText));
        myStage.show();
    }

    public void goToNextLevel() {
        myGameManager.advanceLevel();
        Scene nextLevelScene = getSceneForLevel(myGameManager.getCurrentLevel());
        myStage.setScene(nextLevelScene);
        // attach "game loop" to timeline to play it (basically just calling step() method repeatedly forever)
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(nextLevelScene, SECOND_DELAY));
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    private Scene endGame(int width, int height, Paint background, String displayText) {

        Group root = new Group();

        Text text = new Text();
        text.setText(displayText);
        text.setX(GAME_WIDTH / 2 - text.getLayoutBounds().getWidth() / 2);
        text.setY(GAME_HEIGHT / 2 - text.getLayoutBounds().getHeight() / 2);

        root.getChildren().add(text);
        return new Scene(root, width, height);

    }

    Scene getSceneForLevel(int level) {
        LevelCreator myLevelCreator = new LevelCreator(level, myLevelPath);

        Group root = new Group();

        myPlatform = myLevelCreator.getPlatform();
        myBall = myLevelCreator.getBall();
        myBricks = myLevelCreator.setupBricks();
        myPowerups = new ArrayList<>();

        Text myScore = myGameManager.getScore();
        Text myLevel = myGameManager.getLevel();
        Text highScore = myGameManager.getHighScore();
        Text myLives = myGameManager.getLives();

        root.getChildren().add(myPlatform);
        root.getChildren().add(myBall);
        root.getChildren().addAll(myBricks);
        root.getChildren().add(myScore);
        root.getChildren().add(highScore);
        root.getChildren().add(myLevel);
        root.getChildren().add(myLives);

        // create a place to see the shapes
        Scene scene = new Scene(root, BreakoutGame.GAME_WIDTH, BreakoutGame.GAME_HEIGHT, BreakoutGame.BACKGROUND);
        // respond to input
        setupSceneEventListeners(scene);
        return scene;
    }

    // Create the game's "scene": what shapes will be in the game and their starting properties
    Scene setupGame (String path) {

        myGameManager = new GameManager(path);
        myLevelPath = path;

        StartingScreen start = new StartingScreen();
        return start.getScene();
    }

    public int getNumPowerups() {
        return myPowerups.size();
    }

    private void setupSceneEventListeners(Scene scene) {
        scene.setOnKeyPressed(e -> handleKeyInput(scene, e.getCode()));
        scene.setOnMouseClicked(e -> handleMouseInput(e.getX(), e.getY()));
        scene.setOnMouseMoved(e -> handleMouseMoved(e.getX(), e.getY()));
    }

    private void addPowerup(Scene scene, Powerup p) {
        myPowerups.add(p);
        ((Group)scene.getRoot()).getChildren().add(p.getShape());
    }

    // Change properties of shapes in small ways to animate them over time
    // Note, there are more sophisticated ways to animate shapes, but these simple ways work fine to start
    void step (Scene scene, double elapsedTime) {
        myBall.move(elapsedTime);

        for(Powerup p: myPowerups) {
            p.move(elapsedTime);
        }

        // Check for collisions
        List<Powerup> p = myCollisionManager.handlePowerupCollisions(myPowerups, myPlatform);
        for(Powerup powerup: p) {
            powerup.usePowerUp(myScene, myGameManager);
            ((Group)myScene.getRoot()).getChildren().remove(powerup.getShape());
        }
        myCollisionManager.handlePlatformCollision(myBall, myPlatform);
        List<Brick> hitBricks = myCollisionManager.handleBrickCollision(myBall, ((Group)scene.getRoot()).getChildren().iterator());
        myGameManager.addScore(hitBricks.size());
        myBricks.removeAll(hitBricks);
        for(Brick b: hitBricks) {
            Powerup temp = PowerupGenerator.getPowerup(b.getX() + b.getWidth() / 2, b.getY() + b.getHeight(), Math.random());
            if(temp != null) {
                addPowerup(scene, temp);
            }
        }

        if(myCollisionManager.handleWallCollision(myBall)) {
            myGameManager.loseLife();
            resetBall(myBall);
        }

        checkLevelFinished();
    }

    private void checkLevelFinished() {
        if(myBricks.isEmpty()) {
            levelTransition();
        }
        else if(myGameManager.checkGameOver()) {
            end(LOSE_MESSAGE);
        }
    }

    // What to do each time a key is pressed
    private void handleKeyInput (Scene scene, KeyCode code) {
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
            myGameManager.addLife();
        }
        if(code == KeyCode.S) {
            myBall.increaseSpeed();
        }
        if(code == KeyCode.P) {
            Powerup temp = PowerupGenerator.getPowerup(myBall.getCenterX(), myBall.getCenterY() - myBall.getRadius(), 0);
            addPowerup(scene, temp);
        }
    }

    private void handleKeyInputForNonLevelScreen(Scene scene, KeyCode code) {
        if(code == KeyCode.SPACE) {
            goToNextLevel();
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
        if(animation == null || animation.getStatus() == Animation.Status.RUNNING) {
            myPlatform.move(x);
        }
    }

    public GameManager getMyGameManager() {
        return myGameManager;
    }
    /**
     * Start the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
}
