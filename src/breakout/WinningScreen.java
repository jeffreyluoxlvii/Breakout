package breakout;

import javafx.scene.text.Text;

/**
 * The winning screen displayed at the end of the game.
 * @author roy
 */
public class WinningScreen extends NonLevelScreen {

    private GameManager myGameManager;
    public static final String PROMPT = "Congrats, you beat all the levels!\nWhat an achievement...";
    public static final String SCORE_UPDATE = "Your score was:";

    public WinningScreen(GameManager myGameManager) {
        this.myGameManager = myGameManager;
        addWinningText();
    }

    private void addWinningText() {
        Text text = new Text();
        String myString = PROMPT + NEWLINE + SCORE_UPDATE + SPACE + myGameManager.getCurrentScore();
        text.setText(myString);
        text = setTextLocation(text);
        addTextToScreen(text);
    }

}
