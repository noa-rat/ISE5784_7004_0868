package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

public class Cylinder extends Tube{
    private final double height;

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
        // t -> scalar to to calculate
        double t = (axis.getDirection()).dotProduct(point.subtract(axis.getHead()));
        // Treatment of the edge case where the point is on the base
        if(isZero(t))
            return axis.getDirection();
        // o -> the center of the circle on the tube
        Point o = (axis.getHead()).add((axis.getDirection()).scale(t));
        // normal calculate to the circle
        return (point.subtract(o)).normalize();
    }
}
