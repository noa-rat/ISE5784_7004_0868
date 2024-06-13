package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.*;

public class Sphere extends RadialGeometry {
    private final Point center;

    /**
     * Constructs a sphere by radius and center point
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
     * Finds the points of intersection of the ray with the sphere
     *
     * @param ray to calculation points of intersection with the sphere
     * @return list of points of intersection with the sphere
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        if(center.equals(ray.getHead())) {
            GeoPoint geoPoint = new GeoPoint(this, ray.getPoint(radius));
            return List.of(geoPoint);
        }
        Vector u = center.subtract(ray.getHead());
        double tm = (ray.getDirection()).dotProduct(u);
        if ((tm * tm) > u.lengthSquared())
            return null;
        if(Util.alignZero(tm)<0&&u.length()>=radius)
            return null;

        double d = Math.sqrt(u.lengthSquared() - (tm * tm));
        if ((d * d) > (radius * radius) || d >= radius)
            return null;
        double th = Math.sqrt((radius * radius) - (d * d));
        double t1 = tm + th;
        double t2 = tm - th;

        if ((Util.isZero(t1) && Util.isZero(t2)) || (t1 < 0 && t2 < 0)) {
            return null;
        }
        if (Util.isZero(t1) || t1 < 0) {
            Point p2 = (ray.getPoint(t2));
            GeoPoint geoPoint=new GeoPoint(this,p2);
            return List.of(geoPoint);
        }
        if (Util.isZero(t2) || t2 < 0) {
            Point p1 = (ray.getPoint(t1));
            GeoPoint geoPoint=new GeoPoint(this,p1);
            return List.of(geoPoint);
        } else {
            Point p1 = (ray.getPoint(t1));
            Point p2 = (ray.getPoint(t2));
            GeoPoint geoPoint1=new GeoPoint(this,p1);
            GeoPoint geoPoint2=new GeoPoint(this,p2);
            if (t1 < t2) {
                return List.of(geoPoint1,geoPoint2);
            } else {
                return List.of(geoPoint2,geoPoint1);
            }
        }
    }
}
