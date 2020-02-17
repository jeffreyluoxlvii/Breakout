package breakout;

import java.util.Random;



public class PowerupGenerator {
    /**
     * @param x position
     * @param y position
     * @param random random number to check if less than powerup drop chance
     * @return a random powerup or null if random number >= powerup drop chance
     */
    public static Powerup getPowerup(double x, double y, double random) {
        if(random < Powerup.DROP_CHANCE) {
            return getRandomPowerup(x, y);
        }
        return null;
    }

    /**
     * @param x position
     * @param y position
     * @return random powerup
     */
    public static Powerup getRandomPowerup(double x, double y)
    {
        Powerup[] POWERUP_LIST = new Powerup[] { new LifeUpPowerup(x, y), new PlatformExtenderPowerup(x, y), new SlowPowerup(x, y)};
        Random generator = new Random();
        int randomIndex = generator.nextInt(POWERUP_LIST.length);
        return POWERUP_LIST[randomIndex];
    }
}
