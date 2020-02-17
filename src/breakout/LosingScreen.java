package breakout;

import javafx.scene.text.Text;

/**
 * The screen after a player loses all of their lives.
 * @author roy
 */
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
        addLosingText();
    }

    private void addLosingText() {
        Text text = new Text();
        String myString = PROMPT + SPACE + myLevel + EXCLAMATION + NEWLINE +
                SCORE_UPDATE + SPACE + myGameManager.getCurrentScore();
        text.setText(myString);
        text = setTextLocation(text);
        addTextToScreen(text);
    }

}
