package breakout;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScorerTest {

    @Test
    void getHighScore() {
        assertEquals(10, Scorer.getHighScore());
        Scorer.writeScore(11);
        assertEquals(11, Scorer.getHighScore());
    }
}