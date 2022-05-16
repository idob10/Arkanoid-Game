package sprites;

import biuoop.DrawSurface;

/**
 * sprites.Sprite interface.
 *
 * @author Ido Barkai
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d an instance of draw surface.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}
