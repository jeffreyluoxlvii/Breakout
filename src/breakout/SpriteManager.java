package breakout;

import java.util.*;

/**
 * Class responsible for holding all Sprite objects, and cleaning up Sprite objects that need to be removed.
 */
public class SpriteManager {
    /** All the sprite objects currently in play */
    private List gameActors = new ArrayList<>();

    /** A list used to check collisions between sprites */
    private List checkCollisionList = new ArrayList<>();

    /** A set used to clean up sprites */
    private Set cleanUpSprites = new HashSet<>();

    public List getAllSprites() {
        return gameActors;
    }

    /**
     * Adds sprite objects to the game.
     * @param sprites varargs of sprite objects.
     */
    public void addSprites(Sprite ...sprites) {
        gameActors.addAll(Arrays.asList(sprites));
    }

    /**
     * Removes sprite objects from the game.
     * @param sprites varargs of sprite objects.
     */
    public void removeSprites(Sprite ...sprites) {
        gameActors.removeAll(Arrays.asList(sprites));
    }

    /**
     * Returns a set of sprite objects to be removed.
     * @return set of sprite objects to be removed.
     */
    public Set getSpritesToBeRemoved() {
        return cleanUpSprites;
    }

    /**
     * Adds sprite objects to be removed.
     * @param sprites varargs of sprite objects.
     */
    public void addSpritesToBeRemoved(Sprite... sprites) {
        if (sprites.length > 1) {
            cleanUpSprites.addAll(Arrays.asList((Sprite[]) sprites));
        } else {
            cleanUpSprites.add(sprites[0]);
        }
    }

    /**
     * Returns a list of sprite objects to assist in collision checks.
     * @return list of sprite objects to assist in collision checks.
     */
    public List getCollisionsToCheck() {
        return checkCollisionList;
    }

    /**
     * Resets the list of sprite objects in the collision check collection to reflect the current sprites in game.
     */
    public void resetCollisionsToCheck() {
        checkCollisionList.clear();
        checkCollisionList.addAll(gameActors);
    }

    /**
     * Removes sprite objects that need to be cleaned from the game.
     */
    public void cleanupSprites() {

        // remove sprites from actors list
        gameActors.removeAll(cleanUpSprites);

        // clear the list of sprites needed to be cleaned up
        cleanUpSprites.clear();
    }
}
