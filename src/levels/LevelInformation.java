package levels;

import general.Velocity;
import sprites.Block;
import sprites.Sprite;

import java.util.List;

/**
 * @author Ido Barkai
 */
public interface LevelInformation {
    /**
     * @return the number of balls in the level
     */
    int numberOfBalls();

    /**
     * @return The initial velocity of each ball
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return the paddle speed
     */
    int paddleSpeed();

    /**
     * @return the paddle width
     */
    int paddleWidth();

    /**
     * @return the level's name
     */
    String levelName();

    /**
     * @return the background of the level
     */
    Sprite getBackground();

    /**
     * @return list of blocks of the level
     */
    List<Block> blocks();

    /**
     * @return the number of blocks to remove
     */
    int numberOfBlocksToRemove();
}
