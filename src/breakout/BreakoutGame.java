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
import java.util.Timer;
import java.util.TimerTask;


/**
 * A basic example JavaFX program for the first lab.
 *
 * @author Robert C. Duvall
 */
public class BreakoutGame extends Application {

    public static final String TITLE = "Breakout";
    public static final String WIN_MESSAGE = "Congratulations, you winner!";
    public static final String LOSE_MESSAGE = "Maybe next time you will be a winner.";

    public static final long SLOW_TIME = 3000l;
    public static final int GAME_WIDTH = 400;
    public static final int GAME_HEIGHT = 400;
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final Paint BACKGROUND = Color.AZURE;
    public static final double BALL_STARTING_X = GAME_WIDTH / 2;
    public static final double BALL_STARTING_Y = GAME_HEIGHT * (1 - Platform.PLATFORM_DISTANCE_FROM_BOTTOM);

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
    private Group myRoot;
    private List<Brick> hitBricks;

    /**
     * Initialize what will be displayed and how it will be updated.
     */
    @Override
    public void start (Stage stage) {
        myStage = stage;
        // attach scene to the stage and display it

        myScene = setupGame(REAL_PATH);

        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();
    }

    private Scene getStartingScene() {
        StartingScreen start = new StartingScreen();
        Scene temp = start.getScene();
        temp.setOnKeyPressed(e -> handleKeyInputForTransitionScreen(myScene, e.getCode()));
        return temp;
    }

    private void levelTransition() {
        TransitionScreen screen = new TransitionScreen(myGameManager);
        myScene = screen.getScene();
        myScene.setOnKeyPressed(e -> handleKeyInputForTransitionScreen(myScene, e.getCode()));
        myStage.setScene(myScene);
    }

    private void finishGame(NonLevelScreen screen) {
        myScene = screen.getScene();
        myScene.setOnKeyPressed(e -> handleKeyInputForFinishingScreen(myScene, e.getCode()));
        myStage.setScene(myScene);
    }

    public Scene goToNextLevel() {
        myGameManager.advanceLevel();
        myScene = getSceneForLevel(myGameManager.getCurrentLevel());
        // attach "game loop" to timeline to play it (basically just calling step() method repeatedly forever)
        return myScene;
    }

    private void animate() {
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(myScene, SECOND_DELAY));
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    public void restartGame() {
        myGameManager.restartGame();
        myScene = getStartingScene();
        myStage.setScene(myScene);
    }

    Scene getSceneForLevel(int level) {
        LevelCreator myLevelCreator = new LevelCreator(level, myLevelPath);

        myRoot = new Group();

        myPlatform = myLevelCreator.getPlatform();
        myBall = myLevelCreator.getBall();
        myBricks = myLevelCreator.setupBricks();
        myPowerups = new ArrayList<>();

        Text myScore = myGameManager.getScore();
        Text myLevel = myGameManager.getLevel();
        Text highScore = myGameManager.getHighScore();
        Text myLives = myGameManager.getLives();
        Text slowActive = myGameManager.getSlowActive();

        myRoot.getChildren().add(myPlatform);
        myRoot.getChildren().add(myBall);
        myRoot.getChildren().addAll(myBricks);
        myRoot.getChildren().add(myScore);
        myRoot.getChildren().add(highScore);
        myRoot.getChildren().add(myLevel);
        myRoot.getChildren().add(myLives);
        myRoot.getChildren().add(slowActive);

        // create a place to see the shapes
        Scene scene = new Scene(myRoot, BreakoutGame.GAME_WIDTH, BreakoutGame.GAME_HEIGHT, BreakoutGame.BACKGROUND);
        // respond to input
        setupSceneEventListeners(scene);
        return scene;
    }

    // Create the game's "scene": what shapes will be in the game and their starting properties
    Scene setupGame (String path) {

        myGameManager = new GameManager(path);
        myLevelPath = path;

        return getStartingScene();
    }

    public int getNumPowerups() {
        return myPowerups.size();
    }

    private void setupSceneEventListeners(Scene scene) {
        scene.setOnKeyPressed(e -> handleKeyInput(scene, e.getCode()));
        scene.setOnMouseMoved(e -> handleMouseMoved(e.getX(), e.getY()));
    }

    private void addPowerup(Scene scene, Powerup p) {
        myPowerups.add(p);
        ((Group)scene.getRoot()).getChildren().add(p);
    }

    // Change properties of shapes in small ways to animate them over time
    // Note, there are more sophisticated ways to animate shapes, but these simple ways work fine to start
    void step (Scene scene, double elapsedTime) {
        myBall.move(elapsedTime);

        for(Powerup p: myPowerups) {
            p.move(elapsedTime);
        }

        // Check for collisions
        List<Powerup> p = CollisionManager.handlePowerupCollisions(myPowerups, myPlatform);
        for(Powerup powerup: p) {
            powerup.usePowerUp(myScene, myGameManager);
            ((Group)myScene.getRoot()).getChildren().remove(powerup);
        }
        CollisionManager.handlePlatformCollision(myBall, myPlatform);
        hitBricks = CollisionManager.handleBrickCollision(myBall, ((Group)scene.getRoot()).getChildren().iterator());
        myGameManager.addScore(hitBricks.size());
        myGameManager.updateHighScore();
        myBricks.removeAll(hitBricks);
        for(Brick b: hitBricks) {
            Powerup temp = PowerupGenerator.getPowerup(b.getX() + b.getWidth() / 2, b.getY() + b.getHeight(), Math.random());
            if(temp != null) {
                addPowerup(scene, temp);
            }
        }

        if(CollisionManager.handleWallCollision(myBall)) {
            myGameManager.loseLife();
            resetBall(myBall);
        }

        checkLevelFinished();
    }

    private void checkLevelFinished() {
        if(myBricks.isEmpty()) {
            animation.stop();
            if(myGameManager.getCurrentLevel() == LevelCreator.getNumLevels()) {
                finishGame(new WinningScreen(myGameManager));
            }
            else {
                levelTransition();
            }
        }
        else if(myGameManager.checkGameOver()) {
            animation.stop();
            finishGame(new LosingScreen(myGameManager));
        }
    }

    // What to do each time a key is pressed
    private void handleKeyInput(Scene scene, KeyCode code) {
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
        if(code == KeyCode.D) {
            myRoot.getChildren().remove(myBricks.get(0));
            myBricks.remove(0);
        }
        if(code == KeyCode.DIGIT1 || code == KeyCode.NUMPAD1) {
            skipToLevel(1);
        }
        if(code == KeyCode.DIGIT2 || code == KeyCode.NUMPAD2) {
            skipToLevel(2);
        }
        if(code == KeyCode.DIGIT3 || code == KeyCode.NUMPAD3) {
            skipToLevel(3);
        }
        if(code == KeyCode.Q) {
            if(myGameManager.getCanSlow()) {
                myGameManager.toggleCanSlow();
                myBall.halfSpeed();
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    public void run() {
                        myBall.fullSpeed();
                    }
                };
                timer.schedule(task, SLOW_TIME);
            }
        }
        // Cheat key gives instant slow ability
        if(code == KeyCode.W) {
            if(!myGameManager.getCanSlow()) {
                myGameManager.toggleCanSlow();
            }
        }
    }

    private void skipToLevel(int level) {
        if(animation != null) {
            animation.stop();
        }
        myGameManager.setLevel(level);
        myScene = getSceneForLevel(level);
        myStage.setScene(myScene);
        animate();
    }

    private void handleKeyInputForTransitionScreen(Scene scene, KeyCode code) {
        if(code == KeyCode.SPACE) {
            myStage.setScene(goToNextLevel());
            animate();
        }
    }

    private void handleKeyInputForFinishingScreen(Scene scene, KeyCode code) {
        if(code == KeyCode.SPACE) {
            restartGame();
        }
    }

    private void resetBall(Ball ball) {
        ball.setCenterX(BALL_STARTING_X);
        ball.setCenterY(BALL_STARTING_Y);
        ball.moveUp();
        ball.moveRight();
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
