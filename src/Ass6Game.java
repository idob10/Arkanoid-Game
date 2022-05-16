import biuoop.GUI;
import animation.AnimationRunner;
import levels.Level1;
import levels.Level2;
import levels.Level3;
import levels.Level4;
import levels.LevelInformation;
import general.GameFlow;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ido Barkai
 */
public class Ass6Game {
    /**
     * @param args command line arugments.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner ar = new AnimationRunner(gui, 60);
        List<LevelInformation> fourLevels = new ArrayList<>();
        List<LevelInformation> levels = new ArrayList<>();
        fourLevels.add(new Level1());
        fourLevels.add(new Level2());
        fourLevels.add(new Level3());
        fourLevels.add(new Level4());
        for (String s : args) {
            try {
                int level = Integer.parseInt(s);
                levels.add(fourLevels.get(level - 1));
            } catch (Exception ex) {
                continue;
            }
        }
        if (levels.isEmpty()) {
            levels.addAll(fourLevels);
        }
        GameFlow game = new GameFlow(ar, gui.getKeyboardSensor());
        game.runLevels(levels);
    }
}
