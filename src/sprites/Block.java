package sprites;

import collision.Collidable;
import biuoop.DrawSurface;
import geometry.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import general.Velocity;
import general.GameLevel;
import geometry.Point;
import observers.HitListener;

/**
 * sprites.Block representing class.
 *
 * @author Ido Barkai
 */
public class Block implements Collidable, Sprite {
    private Rectangle rect;
    private java.awt.Color color;
    private List<HitListener> hitListeners;
    private boolean isDeathBlock;

    /**
     * Constructor.
     *
     * @param c    color.
     * @param rect the rectangle member.
     */
    public Block(Rectangle rect, Color c) {
        this.rect = rect;
        color = c;
        hitListeners = new ArrayList<HitListener>();
        isDeathBlock = false;
    }

    /**
     * Constructor.
     *
     * @param c    color.
     * @param rect the rectangle member.
     * @param b    boolean is death block
     */
    public Block(Rectangle rect, Color c, boolean b) {
        this.rect = rect;
        color = c;
        hitListeners = new ArrayList<HitListener>();
        isDeathBlock = b;
    }

    /**
     * Constructor.
     *
     * @param rect the rectangle member.
     */
    public Block(Rectangle rect) {
        this(rect, Color.black);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return rect;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        if (rect.isPointOnVertical(collisionPoint)) {
            dx = -dx;
        }
        if (rect.isPointOnHorizontal(collisionPoint)) {
            dy = -dy;
        }
        notifyHit(hitter);
        return new Velocity(dx, dy);
    }

    /**
     * draw the sprites.Block on the given DrawSurface.
     *
     * @param surface the given DrawSurface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        Point upperLeft = rect.getUpperLeft();
        int x = (int) upperLeft.getX();
        int y = (int) upperLeft.getY();
        int width = (int) rect.getWidth();
        int height = (int) rect.getHeight();
        surface.fillRectangle(x, y, width, height);
        if (!isDeathBlock) {
            surface.setColor(Color.BLACK);
            surface.drawRectangle(x, y, width, height);
        }
    }

    @Override
    public void timePassed() {
        return;
    }

    /**
     * add the block into the game.
     *
     * @param g the instance of the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * remove this block from the game.
     *
     * @param game an instance of a game
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * notify hit to the listeners.
     *
     * @param hitter an instance of a ball
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Adding a listener to the list.
     *
     * @param h an instance of hit listener
     */
    public void addListener(HitListener h) {
        hitListeners.add(h);
    }

    /**
     * @return if the block kills the balls
     */
    public boolean isDeathBlock() {
        return isDeathBlock;
    }
}
