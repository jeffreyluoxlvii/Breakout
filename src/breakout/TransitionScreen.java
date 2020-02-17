package breakout;

import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * Transition screen between levels.
 * @author roy
 */
public class TransitionScreen extends NonLevelScreen {

    private int myLevel;
    private GameManager myGameManager;
    public static final String PROMPT = "Congratulations! For passing level";
    public static final String SCORE_UPDATE = "your score is now";

    public TransitionScreen(GameManager myGameManager) {
        this.myGameManager = myGameManager;
        this.myLevel = myGameManager.getCurrentLevel();
        addCongratulatoryText();
    }

    private void addCongratulatoryText() {
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
        addTextToScreen(text);
    }
}
