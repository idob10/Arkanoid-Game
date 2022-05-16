package sprites;

import collision.Collidable;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Rectangle;
import geometry.Point;
import general.Velocity;
import general.GameLevel;

import java.awt.Color;

/**
 * paddle class.
 *
 * @author Ido Barkai
 */
public class Paddle implements Sprite, Collidable {
    private KeyboardSensor keyboard;
    private Rectangle rect;
    private Color color;
    private int velocity;

    /**
     * Constructor.
     *
     * @param rect an instance of rectangle.
     * @param velocity the speed of the paddle
     */
    public Paddle(Rectangle rect, int velocity) {
        this.rect = rect;
        color = Color.YELLOW;
        this.velocity = velocity;
    }

    /**
     * moving left the paddle by 10 units.
     */
    public void moveLeft() {
        Point newUpperPoint = new Point(rect.getUpperLeft().getX() - velocity, rect.getUpperLeft().getY());
        if (newUpperPoint.getX() <= 0) {
            newUpperPoint = new Point(0, newUpperPoint.getY());
            ;
        }
        rect = new Rectangle(newUpperPoint, rect.getWidth(), rect.getHeight());
    }

    /**
     * moving right the paddle by 10 units.
     */
    public void moveRight() {
        Point newUpperPoint = new Point(rect.getUpperLeft().getX() + velocity, rect.getUpperLeft().getY());
        if (newUpperPoint.getX() + rect.getWidth() >= 800) {
            newUpperPoint = new Point(800 - rect.getWidth(), newUpperPoint.getY());
        }
        rect = new Rectangle(newUpperPoint, rect.getWidth(), rect.getHeight());
    }

    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * draws the paddle on the screen.
     *
     * @param d an instance of draw surface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        Point upperLeft = rect.getUpperLeft();
        int x = (int) upperLeft.getX();
        int y = (int) upperLeft.getY();
        int width = (int) rect.getWidth();
        int height = (int) rect.getHeight();
        d.fillRectangle(x, y, width, height);
        d.setColor(Color.BLACK);
        d.drawRectangle(x, y, width, height);
    }

    /**
     * getter.
     *
     * @return the collision rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return rect;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (collisionPoint.getY() > rect.getUpperLeft().getY()) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        double length = rect.getWidth() / 5;
        double location = collisionPoint.getX() - rect.getUpperLeft().getX();
        int area = (int) Math.ceil(location / length);
        if (location == 0) {
            area = 1;
        }
        Point pStart = new Point(0, 0);
        Point pEnd = new Point(currentVelocity.getDx(), currentVelocity.getDy());
        double size = pStart.distance(pEnd);
        switch (area) {
            case 1:
                return Velocity.fromAngleAndSpeed(300, size);
            case 2:
                return Velocity.fromAngleAndSpeed(330, size);
            case 3:
                return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            case 4:
                return Velocity.fromAngleAndSpeed(30, size);
            case 5:
                return Velocity.fromAngleAndSpeed(60, size);
            default:
                return currentVelocity;
        }
    }

    /**
     * Add this paddle to the game.
     *
     * @param g an instance of a game.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * setter.
     *
     * @param keyboard1 the keyboard sensor.
     */
    public void setKeyboard(KeyboardSensor keyboard1) {
        this.keyboard = keyboard1;
    }
}
