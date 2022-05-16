package sprites;
/*
Ido Barkai
326629987
 */

import collision.CollisionInfo;
import biuoop.DrawSurface;
import geometry.Point;
import general.Velocity;
import general.GameEnvironment;
import geometry.Line;
import geometry.Rectangle;
import general.GameLevel;

import java.awt.Color;
import java.util.Random;

/**
 * @author Ido Barkai
 * This class represent one dimention ball
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment game;

    /**
     * Constructor.
     *
     * @param center the center of the point
     * @param r      the radius of the sprites.Ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        setVelocity(6, 6);
    }

    /**
     * Constructor.
     *
     * @param x     the x coordinate of the center
     * @param y     the y coordinate of the center
     * @param r     the radius of the ball
     * @param color the color of the ball
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        setVelocity(6, 6);
    }

    /**
     * Constructor set a random color.
     *
     * @param center the center of the point
     * @param r      the radius of the ball
     */
    public Ball(Point center, int r) {
        this.center = center;
        this.radius = r;
        setRandomColor();
        setVelocity(1, 1);
    }

    /**
     * @return the x coordinate of the center
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * @return the y coordinate of the center
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * @return the radius of the sprites.Ball
     */
    public int getSize() {
        return radius;
    }

    /**
     * @return the color of the sprites.Ball
     */
    public java.awt.Color getColor() {
        return color;
    }

    /**
     * draw the ball on the given DrawSurface.
     *
     * @param surface the given DrawSurface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle(getX(), getY(), getSize());
    }

    @Override
    public void timePassed() {
        moveOneStep(0, 0, 800, 600);
    }

    /**
     * setter of velocity.
     *
     * @param v an instance of velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * setter of velocity.
     *
     * @param dx change in x
     * @param dy change in y
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * getter of velocity.
     *
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     * Fix the ball to be in the frame if comes outsides.
     *
     * @param startX the x coordinate start of the frame
     * @param startY the y coordinate start of the frame
     * @param endX   the x coordinate end of the frame
     * @param endY   the y coordinate end of the frame
     */
    private void makeInFrame(int startX, int startY, int endX, int endY) {
        if (center.getX() + radius >= endX) {
            this.velocity.setDx(-this.velocity.getDx());
            this.center.setX(endX - radius);
        } else if (center.getX() - radius <= startX) {
            this.velocity.setDx(-this.velocity.getDx());
            this.center.setX(radius + startX);
        }
        if (center.getY() - radius <= startY) {
            this.velocity.setDy(-this.velocity.getDy());
            this.center.setY(radius + startY);
        }
    }

    /**
     * change the center of the point.
     * (moves one step)
     * default frame 200*200
     */
    public void moveOneStep() {
        moveOneStep(0, 0, 200, 200);
    }

    /**
     * change the center of the point.
     * (moves one step)
     *
     * @param startX the x coordinate start of the frame
     * @param startY the y coordinate start of the frame
     * @param endX   the x coordinate end of the frame
     * @param endY   the y coordinate end of the frame
     */
    public void moveOneStep(int startX, int startY, int endX, int endY) {
        Point endTrajectory = velocity.applyToPoint(this.center);
        Line trajectory = new Line(center, endTrajectory);
        CollisionInfo collision = game.getClosestCollision(trajectory);
        if (collision == null) {
            center = endTrajectory;
            makeInFrame(startX, startY, endX, endY);
        } else {
            Point collisionPoint = collision.collisionPoint();
            Rectangle rect = collision.collisionObject().getCollisionRectangle();
            if (rect.isPointOnRightLine(collisionPoint)) {
                center = new Point(collisionPoint.getX() + radius, collisionPoint.getY());
            }
            if (rect.isPointOnLeftLine(collisionPoint)) {
                center = new Point(collisionPoint.getX() - radius, collisionPoint.getY());
            }
            if (rect.isPointOnTopLine(collisionPoint)) {
                center = new Point(collisionPoint.getX(), collisionPoint.getY() - radius);
            }
            if (rect.isPointOnBottomLine(collisionPoint)) {
                center = new Point(collisionPoint.getX(), collisionPoint.getY() + radius);
            }
            Velocity newV = collision.collisionObject().hit(this, collisionPoint, velocity);
            velocity = newV;
        }
    }

    /**
     * sets a random color.
     */
    public void setRandomColor() {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        this.color = new Color(r, g, b);
    }

    /**
     * Setter of game environment.
     *
     * @param gameEnv an instance of generalGame.GameEnvironment.
     */
    public void setGame(GameEnvironment gameEnv) {
        this.game = gameEnv;
    }

    /**
     * adding the ball into the game.
     *
     * @param g the instance of the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        setGame(g.getEnvironment());
    }

    /**
     * @return the game environment.
     */
    public GameEnvironment getGameEnvironment() {
        return game;
    }

    /**
     * remove this ball from the game.
     *
     * @param g an instance of a game
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}