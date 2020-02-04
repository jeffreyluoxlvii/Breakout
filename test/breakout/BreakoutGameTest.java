package breakout;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests for BreakoutGame class.
 */
public class BreakoutGameTest extends DukeApplicationTest {
    // create an instance of our game to be able to call in tests (like step())
    private final BreakoutGame myGame = new BreakoutGame();
    // keep created scene to allow mouse and keyboard events
    private Scene myScene;
    // keep any useful elements whose values you want to test directly in multiple tests
    private Ball myBall;
    private Platform myPlatform;
    private Brick myBrick_0;
    private Brick myBrick_1;



    /**
     * Start special test version of application that does not animate on its own before each test.
     */
    @Override
    public void start (Stage stage) {
        // create game's scene with all shapes in their initial positions and show it
        myScene = myGame.setupGame(BreakoutGame.SIZE, BreakoutGame.SIZE, BreakoutGame.BACKGROUND, "testOne");
        stage.setScene(myScene);
        stage.show();

        // find individual items within game by ID (must have been set in your code using setID())
        myBall = lookup("#ball").query();
        myPlatform = lookup("#platform").query();
        myBrick_0 = lookup("#brick_0").query();
        myBrick_1 = lookup("#brick_1").query();
    }

    // check initial stats of the ball
    @Test
    public void testBallInitialStats() {
        assertEquals(BreakoutGame.BALL_STARTING_X, myBall.getCenterX());
        assertEquals(BreakoutGame.BALL_STARTING_Y, myBall.getCenterY());
        assertEquals(Ball.BALL_RADIUS, myBall.getRadius());
        assertEquals(Ball.NORMAL_BALL_SPEED, myBall.getVelocity());
    }

    // check initial stats of the platform
    @Test
    public void testPlatformInitialStats() {
        assertEquals(myGame.SIZE * Platform.PLATFORM_HEIGHT, myPlatform.getHeight());
        assertEquals(myGame.SIZE * Platform.PLATFORM_WIDTH, myPlatform.getWidth());
        sleep(1, TimeUnit.SECONDS);
    }

    // check the positions of the first block of every row
    @Test
    public void testBrickPositions() {
        assertEquals(0, myBrick_0.getX());
        assertEquals(0, myBrick_0.getY());
        assertEquals(myBrick_0.getWidth(), myBrick_1.getX());
        assertEquals(myBrick_0.getHeight(), myBrick_1.getY());
    }

    @Test
    public void testBallHitCorner() {
        myBall.moveUp();
        myBall.moveRight();
        myBall.setCenterX(BreakoutGame.SIZE - myBall.getRadius() - myBall.getVelocity() * 0.05);
        myBall.setCenterY(myBall.getRadius() + myBall.getVelocity() * 0.05);
        sleep(2, TimeUnit.SECONDS);
        myGame.step(0.05);
        myGame.step(0.05);
        assertEquals(BreakoutGame.SIZE - myBall.getRadius() - myBall.getVelocity() * 0.05, myBall.getCenterX());
        assertEquals(myBall.getRadius() + myBall.getVelocity() * 0.05, myBall.getCenterY());
    }

    @Test
    public void testBallBouncesCorrectly() {
        myBall.setCenterX(myPlatform.getX() + myPlatform.getWidth() / 2 - myBall.getVelocity() * 0.2);
        myBall.setCenterY(myPlatform.getY() - myBall.getVelocity() * 0.2);
        myGame.step(0.2);
        myGame.step(0.2);
        assertEquals(myPlatform.getX() + myPlatform.getWidth() / 2 + myBall.getVelocity() * 0.2, myBall.getCenterX());
        assertEquals(myPlatform.getY() - myBall.getVelocity() * 0.2, myBall.getCenterY());
    }

    @Test
    public void testBallReset() {
        myBall.setCenterX(BreakoutGame.SIZE  - 1);
        myBall.setCenterY(BreakoutGame.SIZE - 1);
        myGame.step(1);
        assertEquals(BreakoutGame.BALL_STARTING_X, myBall.getCenterX());
        assertEquals(BreakoutGame.BALL_STARTING_Y, myBall.getCenterY());
    }
}
