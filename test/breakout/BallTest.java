package breakout;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Test class for ball methods
class BallTest {

    Ball ball = new Ball(50, 50);

    @Test
    void testMoveLeft() {
        ball.moveLeft();
        assertEquals(-1, ball.getXDirection());
    }

    @Test
    void testMoveRight() {
        ball.moveRight();
        assertEquals(1, ball.getXDirection());
    }

    @Test
    void testMoveUp() {
        ball.moveUp();
        assertEquals(-1, ball.getYDirection());
    }

    @Test
    void testMoveDown() {
        ball.moveDown();
        assertEquals(1, ball.getYDirection());
    }

    @Test
    void testIncreaseSpeed() {
        ball.increaseSpeed();
        assertEquals(Ball.NORMAL_BALL_SPEED + Ball.SPEED_INCREASE, ball.getVelocity());
    }
}