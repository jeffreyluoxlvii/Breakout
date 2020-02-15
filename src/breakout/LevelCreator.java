package breakout;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

public class LevelCreator {

    public static final double BRICKS_HEIGHT_RATIO_TO_SCREEN = 0.7;
    public static final double BRICKS_STARTING_HEIGHT = 50;

    public static final double[] PLATFORM_WIDTHS_FOR_LEVELS = {0.3, 0.25, 0.2};
    public static final int[] BALL_SPEEDS_FOR_LEVELS = {200, 250, 300};
    private int myLevel;

    public LevelCreator(int myLevel) {
        this.myLevel = myLevel;
    }

    public Platform getPlatform() {
        return new Platform(BreakoutGame.SIZE, BreakoutGame.SIZE, PLATFORM_WIDTHS_FOR_LEVELS[myLevel]);
    }

    public Ball getBall() {
        return new Ball(BreakoutGame.BALL_STARTING_X, BreakoutGame.BALL_STARTING_Y, BALL_SPEEDS_FOR_LEVELS[myLevel]);
    }

    public List<Brick> setupBricksForLevel(String path, int gameWidth, int gameHeight) {
        int numRows = getNumberRows(path);
        int numColumns = getNumberColumns(path);
        List<Brick> bricks = new ArrayList<>();

        Scanner scan = getScannerForFile(path);
        int counter = 0;
        for(int i = 0; i < numRows; i++) {
            String line = scan.nextLine();
            for(int j = 0; j < numColumns; j++) {
                Brick brick = createBrick(line.substring(j, j+1),(gameWidth / numColumns) * j, (gameHeight * BRICKS_HEIGHT_RATIO_TO_SCREEN * i / numRows) + BRICKS_STARTING_HEIGHT,
                        gameWidth / numColumns, gameHeight * BRICKS_HEIGHT_RATIO_TO_SCREEN / numRows);
                if(brick != null) {
                    brick.setId("brick_" + counter);
                    bricks.add(brick);
                    counter++;
                }
            }
        }
        return bricks;
    }

    private int getNumberRows(String path) {
        Scanner scan = getScannerForFile(path);
        int counter = 0;
        while(scan.hasNextLine()) {
            scan.nextLine();
            counter++;
        }
        return counter;
    }
    private int getNumberColumns(String path) {
        Scanner scan = getScannerForFile(path);
        return scan.next().length();
    }
    private Scanner getScannerForFile(String path) {
        return new Scanner(LevelCreator.class.getClassLoader().getResourceAsStream(path));
    }

    private Brick createBrick(String brickType, double x, double y, double width, double height) {
        if(brickType.equals("1")) {
            return new WeakBrick(x, y, width, height);
        }
        if(brickType.equals("2")) {
            return new MediumBrick(x, y, width, height);
        }
        if(brickType.equals("3")) {
            return new StrongBrick(x, y, width, height);
        }
        return null;
    }
}
