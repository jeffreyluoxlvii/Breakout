package breakout;

public class PowerupGenerator {
    public static Powerup getPowerup(double x, double y, double random) {
        if(random < PlatformExtenderPowerup.DROP_CHANCE) {
            return new PlatformExtenderPowerup(x, y);
        }
        else {
            return null;
        }
    }
}