package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import general.Counter;
import general.GameLevel;

/**
 * @author Ido Barkai
 */
public class EndScreen implements Animation {
    private KeyboardSensor keyboardSensor;
    private GameLevel level;
    private Counter counterScore;

    /**
     * Constructor.
     *
     * @param ks           the keyboard sensor
     * @param level        the game's level
     * @param counterScore the counter of the score
     */
    public EndScreen(KeyboardSensor ks, GameLevel level, Counter counterScore) {
        keyboardSensor = ks;
        this.level = level;
        this.counterScore = counterScore;
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        if (level.hasBalls()) {
            d.drawText(150, 300, "You Win! Your Score is:" + (counterScore.getValue()), 40);
        } else {
            d.drawText(130, 300, "Game Over. Your Score is:" + (counterScore.getValue()), 40);
        }
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
