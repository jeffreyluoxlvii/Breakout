package breakout;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LevelCreator {

    public static final double BRICKS_HEIGHT_RATIO_TO_SCREEN = 0.7;
    public static final double BRICKS_STARTING_HEIGHT = 50;

    public static final double[] PLATFORM_WIDTHS_FOR_LEVELS = {0.3, 0.25, 0.2};
    public static final int[] BALL_SPEEDS_FOR_LEVELS = {200, 250, 300};
    //myLevel is one indexed
    private int myLevel;
    private String myLevelPath;

    public LevelCreator(int myLevel, String myLevelPath) {
        this.myLevel = myLevel;
        this.myLevelPath = myLevelPath;
    }

    /**
     * @return number of levels
     */
    public static int getNumLevels() {
        return BALL_SPEEDS_FOR_LEVELS.length;
    }

    /**
     * @return platform in the level
     */
    public Platform getPlatform() {
        return new Platform(BreakoutGame.GAME_WIDTH, BreakoutGame.GAME_HEIGHT, PLATFORM_WIDTHS_FOR_LEVELS[myLevel - 1]);
    }

    /**
     * @return ball in the level
     */
    public Ball getBall() {
        return new Ball(BreakoutGame.BALL_STARTING_X, BreakoutGame.BALL_STARTING_Y, BALL_SPEEDS_FOR_LEVELS[myLevel - 1]);
    }

    /**
     * @return set up bricks in a level
     */
    public List<Brick> setupBricks() {
        int numRows = getNumberRows(myLevelPath);
        int numColumns = getNumberColumns(myLevelPath);
        List<Brick> bricks = new ArrayList<>();

        Scanner scan = getScannerForFile(myLevelPath);
        int counter = 0;
        for(int i = 0; i < numRows; i++) {
            String line = scan.nextLine();
            for(int j = 0; j < numColumns; j++) {
                Brick brick = createBrick(line.substring(j, j+1),(BreakoutGame.GAME_WIDTH / numColumns) * j, (BreakoutGame.GAME_HEIGHT * BRICKS_HEIGHT_RATIO_TO_SCREEN * i / numRows) + BRICKS_STARTING_HEIGHT,
                        BreakoutGame.GAME_WIDTH / numColumns, BreakoutGame.GAME_HEIGHT * BRICKS_HEIGHT_RATIO_TO_SCREEN / numRows);
                if(brick != null) {
                    brick.setId("brick_" + counter);
                    bricks.add(brick);
                    counter++;
                }
            }
        }
        return bricks;
    }

    /**
     * @param path of the levels
     * @return number of rows in the level
     */
    private int getNumberRows(String path) {
        Scanner scan = getScannerForFile(path);
        int counter = 0;
        while(scan.hasNextLine()) {
            scan.nextLine();
            counter++;
        }
        return counter;
    }

    /**
     * @param path of the levels
     * @return number of columns in the level
     */
    private int getNumberColumns(String path) {
        Scanner scan = getScannerForFile(path);
        return scan.next().length();
    }

    /**
     * @param path of the levels
     * @return scanner to read the files
     */
    private Scanner getScannerForFile(String path) {
        return new Scanner(LevelCreator.class.getClassLoader().getResourceAsStream(path + myLevel));
    }

    /**
     * @param brickType type of brick (either 1, 2, 3)
     * @param x position
     * @param y position
     * @param width width of brick
     * @param height height of brick
     * @return brick with the given properties
     */
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
