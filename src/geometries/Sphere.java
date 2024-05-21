package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Sphere extends RadialGeometry{
    private final Point center;

    /**
     *
     * @param radius to the sphere
     * @param center on the sphere
     */
    public Sphere(double radius, Point center) {
        super(radius);
        this.center = center;
    }

    @Override
    public Vector getNormal(Point point) {
        return (point.subtract(center)).normalize();
    }

    /**
     *
     * @param ray to calculation points of intersection with the sphere
     * @return list of points of intersection with the sphere
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        Vector u = center.subtract(ray.getHead());
        double tm = (ray.getDirection()).dotProduct(u);
        if ((tm * tm) > u.lengthSquared())
            return null;
        double d = Math.sqrt(u.lengthSquared() - (tm * tm));
        if ((d * d) > (radius * radius) || d >= radius)
            return null;
        double th = Math.sqrt((radius * radius) - (d * d));
        double t1 = tm + th;
        double t2 = tm - th;
        List<Point> intersections = new LinkedList<Point>();
        if ((Util.isZero(t1) && Util.isZero(t2)) || (t1 < 0 && t2 < 0)) {
            return null;
        }
        if (Util.isZero(t1) || t1 < 0) {
            Point p2 = (ray.getHead()).add((ray.getDirection()).scale(t2));
            intersections.add(p2);
            return intersections;
        }
        if (Util.isZero(t2) || t2 < 0) {
            Point p1 = (ray.getHead()).add((ray.getDirection()).scale(t1));
            intersections.add(p1);
            return intersections;
        }
        else {
            Point p1 = (ray.getHead()).add((ray.getDirection()).scale(t1));
            Point p2 = (ray.getHead()).add((ray.getDirection()).scale(t2));
            intersections.add(p1);
            intersections.add(p2);
            return intersections;
        }
    }
}
