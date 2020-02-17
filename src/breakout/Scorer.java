package breakout;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Scorer {
    public static final String SCORE_FILE = "data/scores";
    public static final String SCORE_RESOURCE = "scores";

    /**
     * @return the highest score inside of the scores file
     */
    public static int getHighScore() {
        try {
            Scanner scanner = new Scanner(Scorer.class.getClassLoader().getResourceAsStream(SCORE_RESOURCE));
            List<Integer> scores = new ArrayList<>();
            while (scanner.hasNext()) {
                if (scanner.hasNextInt()) {
                    scores.add(scanner.nextInt());
                } else {
                    scanner.next();
                }
            }
            Collections.sort(scores, Collections.reverseOrder());
            return scores.get(0);
        }
        catch(Exception e) {
            return 0;
        }
    }

    /**
     * Write a score to the scores file
     * @param score score to be written
     */
    public static void writeScore(int score) {
        try {
            PrintWriter writer = new PrintWriter((new FileWriter(SCORE_FILE)));
            writer.println(score);
            writer.close();
        }
        catch(Exception e) {
            System.out.println("File not found");
        }
    }
}
