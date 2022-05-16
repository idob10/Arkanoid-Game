package geometry;

import java.util.ArrayList;

/**
 * Class represents a rectangle.
 *
 * @author Ido Barkai
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Constructor.
     *
     * @param upperLeft the upper left of geometry.Point of the rectangle.
     * @param width     the width of the rectangle.
     * @param height    the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * @param line an instance of a line
     * @return a (possibly empty) List of intersection points with the specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        Point upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        Point lowerLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        Point lowerRight = new Point(upperRight.getX(), upperRight.getY() + height);
        java.util.List<Point> intersections = new ArrayList<Point>();
        java.util.List<Line> sides = new ArrayList<Line>();
        sides.add(new Line(upperLeft, upperRight));
        sides.add(new Line(upperRight, lowerRight));
        sides.add(new Line(upperLeft, lowerLeft));
        sides.add(new Line(lowerLeft, lowerRight));
        for (Line l : sides) {
            Point intersect = line.intersectionWith(l);
            if (intersect != null) {
                intersections.add(intersect);
            }
        }
        return intersections;
    }

    /**
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return width;
    }

    /**
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return height;
    }

    /**
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * @param p an instance of a point.
     * @return true if the point on the left line of the rectangle
     * otherwise, false.
     */
    public boolean isPointOnLeftLine(Point p) {
        Point lowerLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        Line leftVertical = new Line(upperLeft, lowerLeft);
        return leftVertical.isPointOnLine(p);
    }

    /**
     * @param p an instance of a point.
     * @return true if the point on the right line of the rectangle
     * otherwise, false.
     */
    public boolean isPointOnRightLine(Point p) {
        Point upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        Point lowerRight = new Point(upperRight.getX(), upperRight.getY() + height);
        Line rightVertical = new Line(upperRight, lowerRight);
        return rightVertical.isPointOnLine(p);
    }

    /**
     * @param p an instance of a point.
     * @return true if the point on the top line of the rectangle
     * otherwise, false.
     */
    public boolean isPointOnTopLine(Point p) {
        Point upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        Line upperHorizontal = new Line(upperLeft, upperRight);
        return upperHorizontal.isPointOnLine(p);
    }

    /**
     * @param p an instance of a point.
     * @return true if the point on the bottom line of the rectangle
     * otherwise, false.
     */
    public boolean isPointOnBottomLine(Point p) {
        Point lowerLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        Point lowerRight = new Point(upperLeft.getX() + width, lowerLeft.getY());
        Line lowerHorizontal = new Line(lowerLeft, lowerRight);
        return lowerHorizontal.isPointOnLine(p);
    }

    /**
     * @param p an instance of a point
     * @return true if the point is on the horizontal sides
     * of the rectangle, otherwise false.
     */
    public boolean isPointOnHorizontal(Point p) {
        return isPointOnBottomLine(p) || isPointOnTopLine(p);
    }

    /**
     * @param p an instance of a point
     * @return true if the point is on the vertical sides
     * of the rectangle, otherwise false.
     */
    public boolean isPointOnVertical(Point p) {
        return isPointOnLeftLine(p) || isPointOnRightLine(p);
    }
}
