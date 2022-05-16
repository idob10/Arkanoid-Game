package geometry;
/*
Ido Barkai
326629987
 */

/**
 * @author Ido Barkai
 * one dimensional point
 */
public class Point {
    private double x;
    private double y;

    /**
     * Constructor.
     *
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param other another instance of point
     * @return the distance of this point to the other point
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(x - other.getX(), 2) + Math.pow(y - other.getY(), 2));
    }

    /**
     * @param other another instance of point
     * @return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        double epsilon = Math.pow(10, -5);
        double xDelta = x - other.getX();
        double yDelta = y - other.getY();
        return Math.abs(xDelta) < epsilon && Math.abs(yDelta) < epsilon;
    }

    /**
     * @return the x coordinate of the point
     */
    public double getX() {
        return x;
    }

    /**
     * @return the y coordinate of the point
     */
    public double getY() {
        return y;
    }

    /**
     * x setter.
     *
     * @param xCoordinate the x coordinate of the point
     */
    public void setX(double xCoordinate) {
        this.x = xCoordinate;
    }

    /**
     * y setter.
     *
     * @param yCoordinate the y coordinate of the point
     */
    public void setY(double yCoordinate) {
        this.y = yCoordinate;
    }
}