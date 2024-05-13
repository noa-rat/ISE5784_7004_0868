package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

public class Tube extends RadialGeometry{
    protected final Ray axis;

    public Tube(double radius, Ray axis) {
        super(radius);
        this.axis = axis;
    }

    @Override
    public Vector getNormal(Point point) {
        // t -> scalar to to calculate
        double t = (axis.getDirection()).dotProduct(point.subtract(axis.getHead()));
        // Treatment of the edge case
        if(isZero(t))
            return axis.getDirection();
        // o -> the center of the circle on the tube
        Point o = (axis.getHead()).add((axis.getDirection()).scale(t));
        // normal calculate to the circle
        return (point.subtract(o)).normalize();
    }
}
