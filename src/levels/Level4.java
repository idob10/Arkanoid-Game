package levels;

import backgrounds.Level4Background;
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
public class Level4 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> lst = new ArrayList<>();
        lst.add(Velocity.fromAngleAndSpeed(0, 10));
        lst.add(Velocity.fromAngleAndSpeed(45, 10));
        lst.add(Velocity.fromAngleAndSpeed(-45, 10));
        return lst;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 400;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return new Level4Background();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Color c = getRandomColor();
            for (int j = 16; j >= 0; j--) {
                Rectangle rect = new Rectangle(new Point(50 * j, 100 + 25 * i), 50, 25);
                blocks.add(new Block(rect, c));
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 112;
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
