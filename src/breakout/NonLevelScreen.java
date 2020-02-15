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

    public NonLevelScreen(BreakoutGame game) {
        screenText = new ArrayList<>();
    }

    private void handleKeyInput (Scene scene, KeyCode code) {
        if(code == KeyCode.SPACE) {
            game.goToNextLevel();
        }
    }

}
