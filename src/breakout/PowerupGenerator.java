package breakout;

import java.util.Random;



public class PowerupGenerator {
    public static Powerup getPowerup(double x, double y, double random) {
        if(random < PlatformExtenderPowerup.DROP_CHANCE) {
            return getRandomPowerup(x, y);
        }
        return null;
    }

    public static Powerup getRandomPowerup(double x, double y)
    {
        Powerup[] POWERUP_LIST = new Powerup[] { new LifeUpPowerup(x, y), new PlatformExtenderPowerup(x, y)};
        Random generator = new Random();
        int randomIndex = generator.nextInt(POWERUP_LIST.length);
        return POWERUP_LIST[randomIndex];
    }
}
