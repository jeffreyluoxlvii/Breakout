package breakout;

import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

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

    /**
     * Start special test version of application that does not animate on its own before each test.
     */
    @Override
    public void start (Stage stage) {
        // create game's scene with all shapes in their initial positions and show it
        myScene = myGame.setupGame(BreakoutGame.SIZE, BreakoutGame.SIZE, BreakoutGame.BACKGROUND);
        stage.setScene(myScene);
        stage.show();

        // find individual items within game by ID (must have been set in your code using setID())
        myBall = lookup("#ball").query();
        myPlatform = lookup("#platform").query();
    }
}
