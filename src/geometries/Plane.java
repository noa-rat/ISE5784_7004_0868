package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Plane extends Geometry {
    private final Point q;
    private final Vector normal;

    /**
     * Constructs a plane by point and normal
     *
     * @param q      on the plane
     * @param normal of the plane
     */
    public Plane(Point q, Vector normal) {
        this.q = q;
        this.normal = normal.normalize();
    }

    /**
     * Constructs a plane by 3 points on the plane
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
        return normal;
    }

    /**
     * Returns the normal to the plane
     *
     * @return the normal on the plane
     */
    public Vector getNormal() {
        return normal;
    }

    /**
     * Finds the points of intersection of the ray with the plane
     *
     * @param ray to calculation points of intersection with the plane
     * @return list of points of intersection with the plane
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        // Denominator מכנה
        // numerator מונה
        if (q.equals(ray.getHead())) {
            return null;
        }

        double tNumerator = normal.dotProduct(q.subtract(ray.getHead()));
        double tDenominator = normal.dotProduct(ray.getDirection());
        if (Util.isZero(tDenominator)) {
            return null;
        }
        double t = tNumerator / tDenominator;

        if (Util.isZero(t) || t < 0) {
            return null;
        } else {
            Point point = (ray.getPoint(t));
            GeoPoint geoPoint = new GeoPoint(this, point);
            return List.of(geoPoint);
        }
    }
}
