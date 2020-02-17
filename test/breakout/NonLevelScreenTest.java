package breakout;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.Collection;

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
        assertEquals(0, myGame.getMyGameManager().getCurrentLevel());
        myGame.goToNextLevel();
        assertEquals(1, myGame.getMyGameManager().getCurrentLevel());
    }

    @Test
    public void testWinningScreen() {
        myGame.goToNextLevel();
        myGame.goToNextLevel();
        myScene = myGame.finishGame(new WinningScreen(myGame.getMyGameManager()));
        assertEquals(true,
                containsText(myScene.getRoot().getChildrenUnmodifiable(), "Your score was: 0"));
    }

    private boolean containsText(ObservableList<Node> children, String text) {
        boolean correctText = false;
        for(Node n: children) {
            if(n instanceof Text) {
                Text temp = (Text)n;
                if(temp.getText().contains(text)) {
                    correctText = true;
                }
            }
        }
        return correctText;
    }

    @Test
    public void testLosingScreen() {
        myGame.goToNextLevel();
        myGame.goToNextLevel();
        myScene = myGame.finishGame(new LosingScreen(myGame.getMyGameManager()));

        assertEquals(true,
                containsText(myScene.getRoot().getChildrenUnmodifiable(), "level 2"));
    }

    @Test
    public void testTransitionScreen() {
        myGame.goToNextLevel();
        myScene = myGame.levelTransition();

        assertEquals(true,
                containsText(myScene.getRoot().getChildrenUnmodifiable(), "level 1"));
    }



}
