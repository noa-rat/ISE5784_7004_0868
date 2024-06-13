package primitives;

import java.util.List;
import java.util.Objects;
import geometries.Intersectable.GeoPoint;

public class Ray {
    private final Point head;
    private final Vector direction;

    /**
     * @param head      to the head of the ray
     * @param direction the direction of the ray
     */
    public Ray(Point head, Vector direction) {
        this.head = head;
        this.direction = direction.normalize();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return (o instanceof Ray other)
                && this.head.equals(other.head)
                && this.direction.equals(other.direction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(head, direction);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "head=" + head +
                ", direction=" + direction +
                '}';
    }

    public Point getHead() {
        return head;
    }

    public Vector getDirection() {
        return direction;
    }

    /**
     * The method calculates a point on the ray line at a given distance from the ray head
     * @param t The given distance from the head of the ray
     * @return point on thr ray
     */
    public Point getPoint(double t)
    {
        if(Util.isZero(t))
        {
           return head;
        }
        return head.add(direction.scale(t));
    }

    /**
     * Finds the point from the list that is closest to the corner
     * @param points - list of points
     * @return the point from the list that closest to the ray
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }
    public GeoPoint findClosestGeoPoint(List<GeoPoint> points)
    {
        if (points.isEmpty())
            return null;

        GeoPoint closestPoint = points.getFirst();
        for(GeoPoint geoPoint : points)
        {
            if (geoPoint.point.distance(head) < closestPoint.point.distance(head))
                closestPoint = geoPoint;
        }
        return closestPoint;

    }

}
