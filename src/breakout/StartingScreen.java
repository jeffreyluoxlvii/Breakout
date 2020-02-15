package breakout;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.Scanner;

public class StartingScreen extends NonLevelScreen {

    public static final String INSTRUCTIONS_PATH = "gameInstructions";
    private String myInstructions;
    public static final double INSTRUCTIONS_STARTING_Y = 0.2;
    public static final double INSTRUCTIONS_WIDTH = 0.8;
    public static final String NEWLINE = "\n";

    public StartingScreen(BreakoutGame game) {
        super(game);
        myInstructions = readInstructions();
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



    private Text getInstructionsText() {
        Text instructionsText = new Text();
        instructionsText.setText(myInstructions);
        instructionsText.setTextAlignment(TextAlignment.CENTER);
        instructionsText.setX(BreakoutGame.GAME_WIDTH / 2 - instructionsText.getLayoutBounds().getWidth() / 2);
        instructionsText.setY(BreakoutGame.GAME_HEIGHT * INSTRUCTIONS_STARTING_Y);
        return instructionsText;
    }

    @Override
    public Scene getScene() {
        Group root = new Group();
        root.getChildren().add(getInstructionsText());
        root.getChildren().add(getNextScreenText());
        Scene scene = new Scene(root, BreakoutGame.GAME_WIDTH, BreakoutGame.GAME_HEIGHT, BreakoutGame.BACKGROUND);
        return scene;
    }
}
