package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
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
        /**
         * If the resulting point is equal to the head of the horn
         */
        if( point.equals(axis.getHead()))
            return axis.getDirection();
        double t = (axis.getDirection()).dotProduct(point.subtract(axis.getHead()));
        /**
         * If t is less than 0 or greater than the height, then we have exceeded the area of the cylinder
         */
        if(t<0||t>height)
            throw new IllegalArgumentException("The point is outside the area of the cylinder");
        /**
         * If t is equal to 0 or the height then we are on one of the bases
         */
        if(t==0||t==height)
            return axis.getDirection();
        /**
         * t is in the domain of the cylinder
         */
        else {
            Point o = (axis.getHead()).add((axis.getDirection()).scale(alignZero(t)));
            // normal calculate to the circle
            return (point.subtract(o)).normalize();
        }

    }

    /**
     *
     * @param ray to calculation points of intersection with the cylinder
     * @return list of points of intersection with the cylinder
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        return super.findIntersections(ray);
    }

}
