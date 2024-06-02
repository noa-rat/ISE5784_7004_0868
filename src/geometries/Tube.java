package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Tube extends RadialGeometry {
    protected final Ray axis;

    public Tube(double radius, Ray axis) {
        super(radius);
        this.axis = axis;
    }

    @Override
    public Vector getNormal(Point point) {
        // t -> scalar to to calculate
        double t = (axis.getDirection()).dotProduct(point.subtract(axis.getHead()));
        // o -> the center of the circle on the tub
          Point o = (axis.getPoint(t));
        // normal calculate to the circle
        return (point.subtract(o)).normalize();
    }

    /**
     * Finds intersection points of the tube with the ray
     *
     * @param ray to calculation points of intersection with the tube
     * @return list of points of intersection with the tube
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
