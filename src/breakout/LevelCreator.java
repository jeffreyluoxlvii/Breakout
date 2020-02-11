package breakout;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LevelCreator {

    public static final double BRICKS_RATIO_TO_SCREEN = 0.7;

    public static List<Brick> setupBricksForLevel(String path, int gameWidth, int gameHeight) {
        int numRows = getNumberRows(path);
        int numColumns = getNumberColumns(path);
        List<Brick> bricks = new ArrayList<>();

        Scanner scan = getScannerForFile(path);
        int counter = 0;
        for(int i = 0; i < numRows; i++) {
            String line = scan.nextLine();
            for(int j = 0; j < numColumns; j++) {
                if(line.substring(j, j + 1).equals("1")) {
                    Brick brick = new WeakBrick((gameWidth / numColumns) * j, (gameHeight * BRICKS_RATIO_TO_SCREEN) * i / numRows,
                            gameWidth / numColumns, gameHeight * BRICKS_RATIO_TO_SCREEN / numRows);
                    brick.setId("brick_" + counter);
                    bricks.add(brick);
                    counter++;
                }
            }
        }
        return bricks;
    }

    private static int getNumberRows(String path) {
        Scanner scan = getScannerForFile(path);
        int counter = 0;
        while(scan.hasNextLine()) {
            scan.nextLine();
            counter++;
        }
        return counter;
    }
    private static int getNumberColumns(String path) {
        Scanner scan = getScannerForFile(path);
        return scan.next().length();
    }
    private static Scanner getScannerForFile(String path) {
        return new Scanner(LevelCreator.class.getClassLoader().getResourceAsStream(path));
    }
}
