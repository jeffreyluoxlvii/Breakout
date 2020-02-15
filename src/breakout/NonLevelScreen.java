package breakout;

import javafx.animation.Animation;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.List;

public abstract class NonLevelScreen {

    public static final String GO_TO_NEXT_SCREEN_TEXT = "Press the [spacebar] to continue";
    public static final double NEXT_SCREEN_TEXT_Y = 0.8;
    public static final double INSTRUCTIONS_STARTING_Y = 0.2;

    public static final String NEWLINE = "\n";
    public static final String SPACE = " ";
    public static final String EXCLAMATION = "!";
    public static final String ADD = "+";
    public static final String EQUALS = "=";

    public abstract Scene getScene();

    protected Text setTextLocation(Text text) {
        text.setTextAlignment(TextAlignment.CENTER);
        text.setX(BreakoutGame.GAME_WIDTH / 2 - text.getLayoutBounds().getWidth() / 2);
        text.setY(BreakoutGame.GAME_HEIGHT * INSTRUCTIONS_STARTING_Y);
        return text;
    }

    protected Text getNextScreenText() {
        Text nextScreenText = new Text();
        nextScreenText.setText(NonLevelScreen.GO_TO_NEXT_SCREEN_TEXT);
        nextScreenText.setX(BreakoutGame.GAME_WIDTH / 2 - nextScreenText.getLayoutBounds().getWidth() / 2);
        nextScreenText.setY(BreakoutGame.GAME_HEIGHT * NonLevelScreen.NEXT_SCREEN_TEXT_Y);
        return nextScreenText;
    }
}
