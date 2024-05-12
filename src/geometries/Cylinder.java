package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends Tube{
    private double height;

    /**
     *
     * @param radius on the cylinder
     * @param axis of the cylinder
     * @param height of the cylinder
     */
    public Cylinder(double radius, Ray axis, double height) {
        super(radius, axis);
        this.height = height;
    }

    @Override
    public Vector getNormal(Point point) {
        super.getNormal(point);
        return null;
    }
}
