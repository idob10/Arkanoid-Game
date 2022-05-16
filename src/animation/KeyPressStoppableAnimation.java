package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Ido Barkai
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed;
    private boolean running;

    /**
     * Constructor.
     * @param sensor keyboard sensor
     * @param key the pressing key
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboardSensor = sensor;
        this.key = key;
        this.animation = animation;
        this.isAlreadyPressed = true;
        this.running = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (keyboardSensor.isPressed(key)) {
            if (!isAlreadyPressed) {
                running = false;
            } else {
                isAlreadyPressed = false;
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return !running;
    }
}
