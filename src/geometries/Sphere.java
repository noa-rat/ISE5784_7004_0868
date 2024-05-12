package geometries;

import primitives.Point;
import primitives.Vector;

public class Sphere extends RadialGeometry{
    private Point center;

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
        return null;
    }
}
