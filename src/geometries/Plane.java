package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Plane implements Geometry{
    private final Point q;
    private final Vector normal;

    /**
     *
     * @param q on the plane
     * @param normal of the plane
     */
    public Plane(Point q, Vector normal) {
        this.q = q;
        this.normal = normal.normalize();
    }

    /**
     *
     * @param x Point on the plane
     * @param y Point on the plane
     * @param z Point on the plane
     */
    public Plane(Point x, Point y, Point z) {
        q = x;
        normal = (y.subtract(x).crossProduct(z.subtract(x))).normalize();
    }

    @Override
    public Vector getNormal(Point point) {
        return getNormal();
    }

    /**
     *
     * @return the normal on the plane
     */
    public  Vector getNormal() {
        return normal;
    }

    /**
     *
     * @param ray to calculation points of intersection with the plane
     * @return list of points of intersection with the plane
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
