package collision;
import geometry.Rectangle;
import general.Velocity;
import geometry.Point;
import sprites.Ball;

/**
 * Collision.Collidable interface.
 *
 * @author Ido Barkai
 */
public interface Collidable {
    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * @param collisionPoint  the collision point.
     * @param currentVelocity an instance of velocity.
     * @param hitter the hitter ball.
     * @return the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
