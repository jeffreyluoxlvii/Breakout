package breakout;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class LosingScreen extends NonLevelScreen {

    private GameManager myGameManager;
    private int myLevel;
    public static final String PROMPT = "Oh no! You died on level";
    public static final String SCORE_UPDATE = "Your score was: ";
    public static final String ADD = "+";
    public static final String EQUALS = "=";

    public LosingScreen(GameManager myGameManager) {
        this.myGameManager = myGameManager;
        this.myLevel = myGameManager.getCurrentLevel();
    }

    private Text getLosingText() {
        Text text = new Text();
        String myString = PROMPT + SPACE + myLevel + EXCLAMATION + NEWLINE +
                SCORE_UPDATE + SPACE + myGameManager.getCurrentScore();
        text.setText(myString);
        text = setTextLocation(text);
        return text;
    }

    @Override
    public Scene getScene() {
        Group root = new Group();
        root.getChildren().add(getLosingText());
        root.getChildren().add(getNextScreenText());
        Scene scene = new Scene(root, BreakoutGame.GAME_WIDTH, BreakoutGame.GAME_HEIGHT, BreakoutGame.BACKGROUND);
        return scene;
    }
}
