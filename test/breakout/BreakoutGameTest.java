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
    private Brick myBrick_4;
    private Brick myBrick_8;
    private Brick myBrick_12;
    private Brick myBrick_16;
    private Brick myBrick_20;
    private Brick myBrick_24;
    private Brick myBrick_28;
    private Brick myBrick_32;
    private Brick myBrick_36;
    private Brick myBrick_40;
    private Brick myBrick_44;
    private Brick myBrick_48;



    /**
     * Start special test version of application that does not animate on its own before each test.
     */
    @Override
    public void start (Stage stage) {
        // create game's scene with all shapes in their initial positions and show it
        myScene = myGame.setupGame(BreakoutGame.SIZE, BreakoutGame.SIZE, BreakoutGame.BACKGROUND, "levelOne");
        stage.setScene(myScene);
        stage.show();

        // find individual items within game by ID (must have been set in your code using setID())
        myBall = lookup("#ball").query();
        myPlatform = lookup("#platform").query();
        myBrick_0 = lookup("#brick_0").query();
    }

    // check initial stats of the ball
    @Test
    public void testBallInitialStats() {
        assertEquals(30, myBall.getCenterX());
        assertEquals(300, myBall.getCenterY());
        assertEquals(Ball.BALL_RADIUS, myBall.getRadius());
        assertEquals(Ball.NORMAL_BALL_SPEED, myBall.getVelocity());
    }

    // check initial stats of the platform
    @Test
    public void testPlatformInitialStats() {
        assertEquals(myGame.SIZE * Platform.PLATFORM_HEIGHT, myPlatform.getHeight());
        assertEquals(myGame.SIZE * Platform.PLATFORM_WIDTH, myPlatform.getWidth());
        sleep(1, TimeUnit.SECONDS);
        moveTo(myBrick_0);
        assertEquals(myBrick_0.getX() + myBrick_0.getWidth() / 2, myPlatform.getX());
    }

    // check the positions of the first block of every row
    @Test
    public void testBrickPositions() {
        
    }

    @Test
    public void testBallReset() {
        myBall.setCenterX(BreakoutGame.SIZE  - 1);
        myBall.setCenterY(BreakoutGame.SIZE - 1);
        myGame.step(1);
        assertEquals(30, myBall.getCenterX());
        assertEquals(300, myBall.getCenterY());
    }
}
