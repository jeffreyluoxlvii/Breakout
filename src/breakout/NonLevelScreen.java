package breakout;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * The NonLevelScreen is an super-class for all screens that are not an
 * actual part of the game play. All classes that extend this class will
 * have the same prompt at the bottom to go to the next screen. Additionally,
 * they will all have the same location of text within the screen, creating
 * a uniform feel to the game.
 *
 * @author roy
 */
public abstract class NonLevelScreen {

    public static final String GO_TO_NEXT_SCREEN_TEXT = "Press the [spacebar] to continue";
    public static final double NEXT_SCREEN_TEXT_Y = 0.8;
    public static final double INSTRUCTIONS_STARTING_Y = 0.2;

    public static final String NEWLINE = "\n";
    public static final String SPACE = " ";
    public static final String EXCLAMATION = "!";
    public static final String ADD = "+";
    public static final String EQUALS = "=";

    protected Scene myScene;

    /**
     * Construct a new NonLevelScreen. Initializes the Scene object with a prompt at the bottom to continue.
     */
    public NonLevelScreen() {
        Group root = new Group();
        root.getChildren().add(getNextScreenText());
        myScene = new Scene(root, BreakoutGame.GAME_WIDTH, BreakoutGame.GAME_HEIGHT, BreakoutGame.BACKGROUND);
    }

    /**
     * Adds the text to the screen.
     */
    public void addTextToScreen(Text text) {
        ((Group)myScene.getRoot()).getChildren().add(text);
    }

    /**
     * Return the Scene associated with this screen.
     */
    public Scene getScene() {
        return myScene;
    }

    /**
     * Returns the given text centered in the screen.
     */
    public Text setTextLocation(Text text) {
        text.setTextAlignment(TextAlignment.CENTER);
        text.setX(BreakoutGame.GAME_WIDTH / 2 - text.getLayoutBounds().getWidth() / 2);
        text.setY(BreakoutGame.GAME_HEIGHT * INSTRUCTIONS_STARTING_Y);
        return text;
    }

    /**
     * This same prompt is added to each screen.
     */
    private Text getNextScreenText() {
        Text nextScreenText = new Text();
        nextScreenText.setText(NonLevelScreen.GO_TO_NEXT_SCREEN_TEXT);
        nextScreenText.setX(BreakoutGame.GAME_WIDTH / 2 - nextScreenText.getLayoutBounds().getWidth() / 2);
        nextScreenText.setY(BreakoutGame.GAME_HEIGHT * NonLevelScreen.NEXT_SCREEN_TEXT_Y);
        return nextScreenText;
    }
}
