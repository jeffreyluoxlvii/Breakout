package breakout;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class WinningScreen extends NonLevelScreen {

    private GameManager myGameManager;
    public static final String PROMPT = "Congrats, you beat all the levels!\nWhat an achievement...";
    public static final String SCORE_UPDATE = "Your score was: ";

    public WinningScreen(GameManager myGameManager) {
        this.myGameManager = myGameManager;
    }

    private Text getWinningText() {
        Text text = new Text();
        String myString = PROMPT + NEWLINE + SCORE_UPDATE + SPACE + myGameManager.getCurrentScore();
        text.setText(myString);
        text = setTextLocation(text);
        return text;
    }

    @Override
    public Scene getScene() {
        Group root = new Group();
        root.getChildren().add(getWinningText());
        root.getChildren().add(getNextScreenText());
        Scene scene = new Scene(root, BreakoutGame.GAME_WIDTH, BreakoutGame.GAME_HEIGHT, BreakoutGame.BACKGROUND);
        return scene;
    }

}
