package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

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
        return null;
    }
}
