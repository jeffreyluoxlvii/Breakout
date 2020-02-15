package breakout;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class TransitionScreen extends NonLevelScreen {

    private int myLevel;
    private GameManager myGameManager;
    public static final String PROMPT = "Congratulations! For passing level";
    public static final String SPACE = " ";
    public static final String EXCLAMATION = "!";
    public static final String SCORE_UPDATE = "your score is now";
    public static final String ADD = "+";
    public static final String EQUALS = "=";

    public TransitionScreen(GameManager myGameManager) {
        this.myGameManager = myGameManager;
        this.myLevel = myGameManager.getCurrentLevel();
    }

    private Text getCongratulatoryText() {
        Text text = new Text();
        String myString = PROMPT + SPACE + myLevel;
        myString += NEWLINE;
        myString += SCORE_UPDATE + SPACE + myGameManager.getCurrentScore() + SPACE + ADD + SPACE + myLevel + SPACE +
                EQUALS + SPACE;
        myGameManager.addScore(myLevel);
        myString += myGameManager.getCurrentScore();
        text.setText(myString);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setX(BreakoutGame.GAME_WIDTH / 2 - text.getLayoutBounds().getWidth() / 2);
        text.setY(BreakoutGame.GAME_HEIGHT * INSTRUCTIONS_STARTING_Y);
        return text;
    }

    @Override
    public Scene getScene() {
        Group root = new Group();
        root.getChildren().add(getCongratulatoryText());
        root.getChildren().add(getNextScreenText());
        Scene scene = new Scene(root, BreakoutGame.GAME_WIDTH, BreakoutGame.GAME_HEIGHT, BreakoutGame.BACKGROUND);
        return scene;
    }
}
