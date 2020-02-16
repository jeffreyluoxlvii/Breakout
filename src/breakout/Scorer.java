package breakout;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Scorer {
    public static final String SCORE_FILE = "scores";
    public static int getHighScore() {
        try {
            Scanner scanner = new Scanner(Scorer.class.getClassLoader().getResourceAsStream(SCORE_FILE));
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
    public static void writeScore(int score) {
        try {
            // This doesn't work yet
            Files.writeString((Path) Scorer.class.getClassLoader().getResourceAsStream(SCORE_FILE), String.valueOf(score));
        }
        catch(Exception e) {
            System.out.println("File not found");
        }
    }
}
