package animation;

import biuoop.DrawSurface;

/**
 * @author Ido Barkai
 */
public interface Animation {
    /**
     * Doing one frame of the animation.
     *
     * @param d a draw surface of gui
     */
    void doOneFrame(DrawSurface d);

    /**
     * @return if the animation should stop
     */
    boolean shouldStop();
}
