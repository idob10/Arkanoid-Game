package sprites;

import biuoop.DrawSurface;
import general.Counter;
import general.GameLevel;

/**
 * @author Ido Barkai
 */
public class ScoreIndicator implements Sprite {
    private Counter scoreCounter;
    private GameLevel game;

    /**
     * Constructor.
     *
     * @param scoreCounter an instance of a counter
     * @param game the instance of the game
     */
    public ScoreIndicator(Counter scoreCounter, GameLevel game) {
        this.scoreCounter = scoreCounter;
        this.game = game;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(java.awt.Color.black);
        d.drawText(260, 25, "Score:" + (scoreCounter.getValue()), 20);
        d.drawText(390, 25, "Level Name:" + (game.getLevel().levelName()), 20);
    }

    @Override
    public void timePassed() {
    }
}
