package animation;

import biuoop.DrawSurface;
import sprites.SpriteCollection;

import java.awt.Color;

/**
 * @author Ido Barkai
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private int currentSec;
    private double start;

    /**
     * Constructor.
     *
     * @param numOfSeconds the num of seconds to appear
     * @param countFrom    the seconds to count from
     * @param gameScreen   the game screen collection
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.currentSec = countFrom;
        start = System.currentTimeMillis();
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        gameScreen.drawAllOn(d);
        if (System.currentTimeMillis() < (start + numOfSeconds * 1000 / countFrom)) {
            d.setColor(Color.WHITE);
            d.drawText(375, 500, Integer.toString(currentSec), 50);
        } else {
            currentSec--;
            start = System.currentTimeMillis();
        }
    }

    @Override
    public boolean shouldStop() {
        return currentSec <= 0;
    }
}
