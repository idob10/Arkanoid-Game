package general;

import collision.Collidable;
import collision.CollisionInfo;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ido Barkai
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * Constructor.
     */
    public GameEnvironment() {
        collidables = new java.util.ArrayList<Collidable>();
    }

    /**
     * Add the given collidable to the environment.
     *
     * @param c a collidable object.
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * getter.
     *
     * @return the list of the collidables.
     */
    public List<Collidable> getCollidables() {
        return collidables;
    }

    /**
     * @param trajectory the representing line of the trajectory.
     * @return the information about the closest collision that is going to occur
     * if not occurs return null.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Point> intersections = new ArrayList<Point>();
        List<Collidable> closeCollidables = new ArrayList<Collidable>();
        for (Collidable c : collidables) {
            Rectangle rectCollision = c.getCollisionRectangle();
            Point closest = trajectory.closestIntersectionToStartOfLine(rectCollision);
            if (closest != null) {
                intersections.add(closest);
                closeCollidables.add(c);
            }
        }
        if (intersections.isEmpty()) {
            return null;
        } else {
            int minIndex = trajectory.closestPointToStart(intersections);
            return new CollisionInfo(closeCollidables.get(minIndex), intersections.get(minIndex));
        }
    }

    /**
     * remove a collidable from the list.
     *
     * @param c an instance of collidable
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }
}