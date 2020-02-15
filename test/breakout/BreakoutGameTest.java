package breakout;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests for BreakoutGame class.
 */
public class BreakoutGameTest extends DukeApplicationTest {
    // create an instance of our game to be able to call in tests (like stexp())
    private final BreakoutGame myGame = new BreakoutGame();
    // keep created scene to allow mouse and keyboard events
    private Scene myScene;
    private GameManager gameManager;
    // keep any useful elements whose values you want to test directly in multiple tests
    private Ball myBall;
    private Platform myPlatform;
    private Brick myBrick_0;
    private Brick myBrick_1;
    private Text myScore;
    private Text myLives;



    /**
     * Start special test version of application that does not animate on its own before each test.
     */
    @Override
    public void start (Stage stage) {
        // create game's scene with all shapes in their initial positions and show it
        myScene = myGame.setupGame(BreakoutGame.SIZE, BreakoutGame.SIZE, BreakoutGame.BACKGROUND, "test1");
        stage.setScene(myScene);
        stage.show();

        // find individual items within game by ID (must have been set in your code using setID())
        myBall = lookup("#ball").query();
        myPlatform = lookup("#platform").query();
        myBrick_0 = lookup("#brick_0").query();
        myBrick_1 = lookup("#brick_1").query();
        myScore = lookup("#score").query();
        myLives = lookup("#lives").query();
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
        moveTo(myBrick_0);
        sleep(1);
        assertEquals(myBrick_0.getX() + (myBrick_0.getWidth() / 2) - (myPlatform.getWidth() / 2), myPlatform.getX());
    }

    // check the positions of the first block of every row
    @Test
    public void testBrickPositions() {
        assertEquals(0, myBrick_0.getX());
        assertEquals(LevelCreator.BRICKS_STARTING_HEIGHT, myBrick_0.getY());
        assertEquals(myBrick_0.getWidth(), myBrick_1.getX());
        assertEquals(myBrick_0.getHeight() + LevelCreator.BRICKS_STARTING_HEIGHT, myBrick_1.getY());
    }

    @Test
    public void testBallHitCorner() {
        myBall.moveUp();
        myBall.moveRight();
        myBall.setCenterX(BreakoutGame.SIZE - myBall.getRadius() - myBall.getVelocity() * 0.05);
        myBall.setCenterY(myBall.getRadius() + myBall.getVelocity() * 0.05);
        myGame.step(myScene, 0.05);
        myGame.step(myScene, 0.05);
        assertEquals(BreakoutGame.SIZE - myBall.getRadius() - myBall.getVelocity() * 0.05, myBall.getCenterX());
        assertEquals(myBall.getRadius() + myBall.getVelocity() * 0.05, myBall.getCenterY());
    }

    @Test
    public void testBallBouncesCorrectly() {
        myBall.setCenterX(myPlatform.getX() + myPlatform.getWidth() / 2 - myBall.getVelocity() * 0.2);
        myBall.setCenterY(myPlatform.getY() - myBall.getVelocity() * 0.2);
        myGame.step(myScene, 0.2);
        myGame.step(myScene, 0.2);
        assertEquals(myPlatform.getX() + myPlatform.getWidth() / 2 + myBall.getVelocity() * 0.2, myBall.getCenterX());
        assertEquals(myPlatform.getY() - myBall.getVelocity() * 0.2, myBall.getCenterY());
    }

    @Test
    public void testBallReset() {
        myBall.setCenterX(BreakoutGame.SIZE  - 1);
        myBall.setCenterY(BreakoutGame.SIZE - 1);
        myGame.step(myScene, 1);
        assertEquals(BreakoutGame.BALL_STARTING_X, myBall.getCenterX());
        assertEquals(BreakoutGame.BALL_STARTING_Y, myBall.getCenterY());
    }

    // Check that life gets lost when hitting bottom wall
    @Test
    public void testLostLife() {
        myBall.setCenterX(BreakoutGame.SIZE  - 1);
        myBall.setCenterY(BreakoutGame.SIZE - 1);
        myGame.step(myScene, 1);
        assertEquals("LIVES: 2", myLives.getText());
    }

    // Test to check that score gets added
    @Test
    public void testAddScore() {
        myBall.setCenterX(myBrick_0.getX() + myBrick_0.getWidth() / 2 - myBall.getVelocity() * 1);
        myBall.setCenterY(myBrick_0.getY() - myBall.getVelocity() * 1);
        javafxRun(() -> myGame.step(myScene, 1));
        assertEquals("SCORE: 1", myScore.getText());
        // check that brick was deleted
        assertEquals(null, myBrick_0.getScene());
    }

    @Test
    public void testPlatformExtenderPowerup() {
        double prevWidth = myPlatform.getWidth();
        Powerup p = new PlatformExtenderPowerup(0, 0);
        p.usePowerUp(myScene, null);
        assertEquals(prevWidth + PlatformExtenderPowerup.POWERUP_EXTENSION_LENGTH, myPlatform.getWidth());
    }

    @Test
    public void testLifeUpPowerup() {
        Powerup p = new LifeUpPowerup(0, 0);
        p.usePowerUp(myScene, myGame.getGameManager());
        assertEquals("LIVES: 4", myLives.getText());
    }

    @Test
    public void testCheatKeys() {
        press(myScene, KeyCode.S);
        assertEquals(Ball.NORMAL_BALL_SPEED + Ball.SPEED_INCREASE, myBall.getVelocity());
        press(myScene, KeyCode.L);
        assertEquals("LIVES: 4", myLives.getText());
        int numPowerups = myGame.getNumPowerups();
        press(myScene, KeyCode.P);
        assertEquals(numPowerups + 1, myGame.getNumPowerups());
    }
}
