package levels;

import backgrounds.Level2Background;
import general.Velocity;
import geometry.Point;
import geometry.Rectangle;
import sprites.Block;
import sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Ido Barkai
 */
public class Level2 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 11;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> lst = new ArrayList<>();
        lst.add(Velocity.fromAngleAndSpeed(0, 9));
        for (int i = 1; i < numberOfBalls(); i += 2) {
            lst.add(Velocity.fromAngleAndSpeed(-15 * i + 90, 9));
            lst.add(Velocity.fromAngleAndSpeed(15 * i - 90, 9));
        }
        return lst;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 400;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new Level2Background();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        for (int i = 0; i < 16; i += 2) {
            Color c = getRandomColor();
            blocks.add(new Block(new Rectangle(new Point(50 * i, 300), 50, 50), c));
            blocks.add(new Block(new Rectangle(new Point(50 * (i + 1), 300), 50, 50), c));
        }
        Color c = new Color(240, 240, 240, 255);
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 16;
    }

    /**
     * sets a random color.
     *
     * @return random color.
     */
    public Color getRandomColor() {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return new Color(r, g, b);
    }
}
