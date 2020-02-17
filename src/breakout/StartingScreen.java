package breakout;

import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.Scanner;

/**
 * The game's starting screen.
 * @author roy
 */
public class StartingScreen extends NonLevelScreen {

    public static final String INSTRUCTIONS_PATH = "gameInstructions";
    private String myInstructions;

    public StartingScreen() {
        myInstructions = readInstructions();
        addInstructionsText();
    }

    private String readInstructions() {
        Scanner input = new Scanner(LevelCreator.class.getClassLoader().getResourceAsStream(INSTRUCTIONS_PATH));
        String text = "";
        while(input.hasNextLine()) {
            text += input.nextLine();
            text += NEWLINE;
        }
        input.close();
        return text;
    }

    private void addInstructionsText() {
        Text instructionsText = new Text();
        instructionsText.setText(myInstructions);
        instructionsText.setTextAlignment(TextAlignment.CENTER);
        instructionsText.setX(BreakoutGame.GAME_WIDTH / 2 - instructionsText.getLayoutBounds().getWidth() / 2);
        instructionsText.setY(BreakoutGame.GAME_HEIGHT * INSTRUCTIONS_STARTING_Y);
        addTextToScreen(instructionsText);
    }

}
