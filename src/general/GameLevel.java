package general;

import animation.Animation;
import animation.AnimationRunner;
import animation.KeyPressStoppableAnimation;
import animation.CountdownAnimation;
import animation.PauseScreen;
import biuoop.KeyboardSensor;
import collision.Collidable;
import geometry.Point;
import geometry.Rectangle;
import biuoop.DrawSurface;
import levels.LevelInformation;
import observers.BallRemover;
import observers.BlockRemover;
import observers.HitListener;
import observers.ScoreTrackingListener;
import sprites.Sprite;
import sprites.ScoreIndicator;
import sprites.Block;
import sprites.SpriteCollection;
import sprites.Paddle;
import sprites.Ball;

import java.awt.Color;

/**
 * A game class.
 *
 * @author Ido Barkai
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Paddle paddle;
    private Counter counterBlocks;
    private Counter counterBalls;
    private Counter counterScore;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation level;

    /**
     * Constructor.
     *
     * @param level        the level of the current game.
     * @param counterScore counter of the score
     * @param ar           the animation runner
     * @param ks           the keyboard sensor
     */
    public GameLevel(LevelInformation level, KeyboardSensor ks, AnimationRunner ar, Counter counterScore) {
        this.level = level;
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        counterBlocks = new Counter(level.numberOfBlocksToRemove());
        counterBalls = new Counter(level.numberOfBalls());
        this.counterScore = counterScore;
        runner = ar;
        keyboard = ks;
    }

    /**
     * add an object into the collidables collection.
     *
     * @param c an instance of a collidable object
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * getter.
     *
     * @return the game environment.
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * add an object into the sprites collection.
     *
     * @param s an instance of a sprite object
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and sprites.Ball (and sprites.Paddle)
     * and add them to the game.
     */
    public void initialize() {
        addSprite(level.getBackground());
        HitListener h = new BlockRemover(this, counterBlocks);
        HitListener s = new ScoreTrackingListener(counterScore);
        HitListener r = new BallRemover(this, counterBalls);
        for (Velocity v : level.initialBallVelocities()) {
            Ball ball = new Ball(new Point(400, 570), 10, Color.WHITE);
            ball.setVelocity(v);
            ball.addToGame(this);
        }
        Block score = new Block(new Rectangle(new Point(0, 0), 800, 50), Color.WHITE, true);
        score.addToGame(this);
        ScoreIndicator sI = new ScoreIndicator(counterScore, this);
        addSprite(sI);
        int paddleX = 400 - level.paddleWidth() / 2;
        paddle = new Paddle(new Rectangle(new Point(paddleX, 585), level.paddleWidth(), 15), level.paddleSpeed());
        paddle.setKeyboard(keyboard);
        paddle.addToGame(this);
        for (Block block : level.blocks()) {
            block.addListener(h);
            block.addListener(s);
            block.addToGame(this);
        }
        Block death = new Block(new Rectangle(new Point(0, 600), 800, 10), Color.BLACK);
        death.addListener(r);
        death.addToGame(this);
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        runner.run(new CountdownAnimation(2, 3, sprites));
        this.running = true;
        this.runner.run(this);
    }

    /**
     * remove a collidable from the game.
     *
     * @param c an instance of collidable
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * remove a sprite from the game.
     *
     * @param s an instance of sprite
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(240, 240, 240, 255));
        d.fillRectangle(0, 0, 800, 600);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (counterBlocks.getValue() == 0) {
            counterScore.increase(100);
            this.running = false;
        }
        if (counterBalls.getValue() == 0) {
            this.running = false;
        }
        if (keyboard.isPressed("p")) {
            PauseScreen p = new PauseScreen(keyboard);
            KeyPressStoppableAnimation pause = new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY, p);
            runner.run(pause);
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * @return the level information
     */
    public LevelInformation getLevel() {
        return level;
    }

    /***
     *
     * @return if there are balls on the screen
     */
    public boolean hasBalls() {
        return counterBalls.getValue() > 0;
    }

    /**
     * @return if there are blocks on the screen
     */
    public boolean hasBlocks() {
        return counterBlocks.getValue() > 0;
    }
}
