package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
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
        // o -> the center of the circle on the tube
        Point o = (axis.getHead()).add((axis.getDirection()).scale(alignZero(t)));
        // normal calculate to the circle
        return (point.subtract(o)).normalize();
    }

    /**
     *
     * @param ray to calculation points of intersection with the tube
     * @return list of points of intersection with the tube
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
