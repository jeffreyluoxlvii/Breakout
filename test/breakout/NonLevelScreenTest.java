package breakout;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

public class NonLevelScreenTest extends DukeApplicationTest {

    // create an instance of our game to be able to call in tests (like stexp())
    private final BreakoutGame myGame = new BreakoutGame();
    // keep created scene to allow mouse and keyboard events
    private Scene myScene;
    private Stage myStage;


    /**
     * Start special test version of application that does not animate on its own before each test.
     */
    @Override
    public void start (Stage stage) {
        myStage = stage;
        // create game's scene with all shapes in their initial positions and show it
        myScene = myGame.setupGame(BreakoutGame.TEST_PATH);
        stage.setScene(myScene);
        stage.show();
    }

    @Test
    public void testStartingScreen() {
        press(myScene, KeyCode.SPACE);
        assertEquals(myGame.getMyGameManager().getCurrentLevel(), 1);
    }

}
