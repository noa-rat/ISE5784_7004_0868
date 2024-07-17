package geometries;

import primitives.*;

import java.util.List;
import java.util.Objects;

/**
 * Implements the composite design pattern
 */
public abstract class Intersectable {
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }


    /**
     * Connects the cutting point to the geometric body in which it is cut
     */
    public static class GeoPoint {
        // the geometry that cut
        public Geometry geometry;
        //the cutting point of this geometry
        public Point point;

        /**
         * constructor
         *
         * @param geometry to put in field geometry
         * @param point    to put in field point
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof GeoPoint geoPoint)) return false;
            return Objects.equals(geometry, geoPoint.geometry) && point.equals(geoPoint.point);
        }


        @Override
        public int hashCode() {
            return Objects.hash(geometry, point);
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
    }

    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersectionsHelper(ray);
    }

    public abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);


}
