package geometry;
/*
Ido Barkai
326629987
 */

import java.util.List;

/**
 * @author Ido Barkai
 * a line-segement
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * Constructor.
     *
     * @param start a start point
     * @param end   an end point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor.
     *
     * @param x1 x coordinate of the start point
     * @param y1 y coordinate of the start point
     * @param x2 x coordinate of the end point
     * @param y2 y coordinate of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * @return the length of the line
     */
    public double length() {
        return start().distance(end);
    }

    /**
     * @return the middle point of the line
     */
    public Point middle() {
        double x = (start.getX() + end.getX()) / 2;
        double y = (start.getY() + end.getY()) / 2;
        return new Point(x, y);
    }

    /**
     * @return the start point of the line
     */
    public Point start() {
        return start;
    }

    /**
     * @return the end point of the line
     */
    public Point end() {
        return end;
    }

    /**
     * @return the slope of this line
     */
    private double slope() {
        //parallel to Y axis
        if (end.getX() - start.getX() == 0) {
            return 0;
        }
        return (end.getY() - start.getY()) / (end.getX() - start.getX());
    }

    /**
     * @return the constant of the line
     */
    private double constant() {
        return -slope() * start.getX() + start.getY();
    }

    /**
     * @param p an instance of point
     * @return true if the point is on the line, otherwise false
     */
    public boolean isPointOnLine(Point p) {
        double epsilon = Math.pow(10, -10);
        //the distance from start to p
        double firstDistance = start.distance(p);
        //the distance from end to p
        double secondDistance = end.distance(p);
        //the length of the line
        double distance = start.distance(end);
        return Math.abs(firstDistance + secondDistance - distance) < epsilon;
    }

    /**
     * @return true if the line is parallel to Y Axis, otherwise false
     */
    private boolean isParallelYAxis() {
        return (start.getX() - end.getX()) == 0;
    }

    /**
     * @return true if the line is parallel to X Axis, otherwise false
     */
    private boolean isParallelXAxis() {
        return (start.getY() - end.getY()) == 0;
    }

    /**
     * @param other another instance of line
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        double intersectionX = 0;
        Point intersection;
        if (isParallelXAxis() && other.isParallelYAxis()) {
            intersection = new Point(other.start.getX(), start().getY());
            return isPointOnLine(intersection) && other.isPointOnLine(intersection);
        }
        if (other.isParallelXAxis() && isParallelYAxis()) {
            intersection = new Point(start.getX(), other.start.getY());
            return isPointOnLine(intersection) && other.isPointOnLine(intersection);
        }
        if (isParallelYAxis()) {
            intersectionX = start.getX();
        } else if (other.isParallelYAxis()) {
            intersectionX = other.start.getX();
        } else {
            intersectionX = (other.constant() - constant()) / (slope() - other.slope());
        }
        if (slope() != other.slope()) {
            double intersectionY;
            if (slope() != 0) {
                intersectionY = slope() * intersectionX + constant();
            } else {
                intersectionY = other.slope() * intersectionX + other.constant();
            }
            intersection = new Point(intersectionX, intersectionY);
            return isPointOnLine(intersection) && other.isPointOnLine(intersection);
        }
        return isPointOnLine(other.start) || isPointOnLine(other.end);
    }

    /**
     * @param other another instance of line
     * @return the intersection point if the lines intersect, and null otherwise
     */
    public Point intersectionWith(Line other) {
        if (!isIntersecting(other)) {
            return null;
        }
        double intersectionX = 0;
        Point intersection;
        if (isParallelXAxis() && other.isParallelYAxis()) {
            intersection = new Point(other.start.getX(), start().getY());
            return intersection;
        }
        if (other.isParallelXAxis() && isParallelYAxis()) {
            intersection = new Point(start.getX(), other.start.getY());
            return intersection;
        }
        if (isParallelYAxis()) {
            intersectionX = start.getX();
        } else if (other.isParallelYAxis()) {
            intersectionX = other.start.getX();
        } else {
            intersectionX = (other.constant() - constant()) / (slope() - other.slope());
        }
        if (slope() != other.slope()) {
            double intersectionY;
            if (slope() != 0) {
                intersectionY = slope() * intersectionX + constant();
            } else {
                intersectionY = other.slope() * intersectionX + other.constant();
            }
            intersection = new Point(intersectionX, intersectionY);
            return intersection;
        }
        if (start().equals(other.start)) {
            if (other.isPointOnLine(end) || isPointOnLine(other.end)) {
                return null;
            } else {
                return start;
            }
        }
        if (end().equals(other.end)) {
            if (other.isPointOnLine(start) || isPointOnLine(other.start)) {
                return null;
            } else {
                return end;
            }
        }
        if (end.equals(other.start)) {
            if (other.isPointOnLine(start) || isPointOnLine(other.end)) {
                return null;
            } else {
                return end;
            }
        }
        if (start.equals(other.end)) {
            if (other.isPointOnLine(end) || isPointOnLine(other.start)) {
                return null;
            } else {
                return start;
            }
        }
        return null;
    }

    /**
     * @param other another instance of line
     * @return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return start.equals(other.start) && end.equals(other.end);
    }

    /**
     * @param rect an instance of a rectangle.
     * @return the closest intersection point to the start of the line.
     * if not intersects return null.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> intersections = rect.intersectionPoints(this);
        if (intersections.isEmpty()) {
            return null;
        }
        Point closestIntersection = intersections.get(0);
        double minDistance = start.distance(closestIntersection);
        for (Point p : intersections) {
            if (start.distance(p) < minDistance) {
                closestIntersection = p;
                minDistance = start.distance(p);
            }
        }
        return closestIntersection;
    }

    /**
     * @param points list of points
     * @return the index of the closest point, if list empty return -1.
     */
    public int closestPointToStart(List<Point> points) {
        if (points.isEmpty()) {
            return -1;
        }
        int minIndex = 0;
        double minDistance = start.distance(points.get(0));
        for (int i = 1; i < points.size(); i++) {
            double distance = start.distance(points.get(i));
            if (distance < minDistance) {
                minDistance = distance;
                minIndex = i;
            }
        }
        return minIndex;

    }

}