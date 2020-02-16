package breakout;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScorerTest {

    @Test
    void getHighScore() {
        Scorer.writeScore(1100);
        assertEquals(1100, Scorer.getHighScore());
    }
}