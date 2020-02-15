package breakout;

import javafx.animation.Animation;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public abstract class NonLevelScreen {
    private List<Text> screenText;
    private BreakoutGame game;
    public static final String GO_TO_NEXT_SCREEN_TEXT = "Press the [spacebar] to continue";
    public static final double NEXT_SCREEN_TEXT_Y = 0.8;

    public NonLevelScreen(BreakoutGame game) {
        screenText = new ArrayList<>();
    }

    public abstract Scene getScene();

    protected Text getNextScreenText() {
        Text nextScreenText = new Text();
        nextScreenText.setText(NonLevelScreen.GO_TO_NEXT_SCREEN_TEXT);
        nextScreenText.setX(BreakoutGame.GAME_WIDTH / 2 - nextScreenText.getLayoutBounds().getWidth() / 2);
        nextScreenText.setY(BreakoutGame.GAME_HEIGHT * NonLevelScreen.NEXT_SCREEN_TEXT_Y);
        return nextScreenText;
    }
}
