package general;
/*
Ido Barkai
326629987
 */

import geometry.Point;

/**
 * @author Ido Barkai
 * This class represents generalGame.Velocity
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructor.
     *
     * @param dx change in x coordinate
     * @param dy change in y coordinate
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * getter dx.
     *
     * @return change in x
     */
    public double getDx() {
        return dx;
    }

    /**
     * getter dy.
     *
     * @return change in y
     */
    public double getDy() {
        return dy;
    }

    /**
     * Constructor.
     *
     * @param angel the angel of the velocity
     * @param speed the units of the velocity
     * @return new instance of velocity
     */
    public static Velocity fromAngleAndSpeed(double angel, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angel));
        double dy = -speed * Math.cos(Math.toRadians(angel));
        return new Velocity(dx, dy);
    }

    /**
     * @param p an instance of a point
     * @return a new point with position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * dx setter.
     *
     * @param dx1 the change in x
     */
    public void setDx(double dx1) {
        this.dx = dx1;
    }

    /**
     * dy setter.
     *
     * @param dy1 the change in y
     */
    public void setDy(double dy1) {
        this.dy = dy1;
    }

    /**
     * @param other another instance of velocity.
     * @return true if the velocity equals otherwise false.
     */
    public boolean equals(Velocity other) {
        double epsilon = Math.pow(10, -5);
        double dxDelta = dx - other.getDx();
        double dyDelta = dy - other.getDy();
        return dxDelta < epsilon && dyDelta < epsilon;
    }

}
