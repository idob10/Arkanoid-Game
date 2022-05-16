package general;

import animation.AnimationRunner;
import animation.EndScreen;
import animation.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;
import levels.LevelInformation;

import java.util.List;

/**
 * @author Ido Barkai
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter counterScore;

    /**
     * Constructor.
     *
     * @param ar animation runner
     * @param ks keyboard sensor
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        counterScore = new Counter(0);
    }

    /**
     * running the levels.
     *
     * @param levels list of the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        if (levels.isEmpty()) {
            return;
        }
        GameLevel level = null;
        for (LevelInformation levelInfo : levels) {
            level = new GameLevel(levelInfo, keyboardSensor, animationRunner, counterScore);
            level.initialize();

            while (level.hasBalls() && level.hasBlocks()) {
                level.run();
            }

            if (!level.hasBalls()) {
                break;
            }
        }
        EndScreen es = new EndScreen(keyboardSensor, level, counterScore);
        KeyPressStoppableAnimation end = new KeyPressStoppableAnimation(keyboardSensor, KeyboardSensor.SPACE_KEY, es);
        animationRunner.run(end);
        animationRunner.close();
    }
}
