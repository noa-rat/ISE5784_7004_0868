package geometries;

import primitives.*;

import java.util.List;

public class Triangle extends Polygon{
    public Triangle(Point... vertices) {
        super(vertices);

        if (vertices.length != 3)
            throw new IllegalArgumentException("Triangle must have 3 vertices");
    }

    /**
     *
     * @param ray to calculation points of intersection with the triangle
     * @return list of points of intersection with the triangle
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        Vector v1 = (vertices.get(0)).subtract(ray.getHead());
        Vector v2 = (vertices.get(1)).subtract(ray.getHead());
        Vector v3 = (vertices.get(2)).subtract(ray.getHead());
        Vector n1 = (v1.crossProduct(v2)).normalize();
        Vector n2 = (v2.crossProduct(v3)).normalize();
        Vector n3 = (v3.crossProduct(v1)).normalize();

        double d1 = (ray.getDirection()).dotProduct(n1);
        double d2 = (ray.getDirection()).dotProduct(n2);
        double d3 = (ray.getDirection()).dotProduct(n3);

        if (Util.isZero(d1) || Util.isZero(d2) || Util.isZero(d3)) {
            return null;
        }
        if (((d1 > 0) && (d2 > 0) && (d3 > 0)) || ((d1 < 0) && (d2 < 0) && (d3 < 0))) {
            Plane plane1 = new Plane(
                    vertices.get(0),
                    vertices.get(1),
                    vertices.get(2)
            );
            return plane1.findIntersections(ray);
        }
        else
            return null;
    }
}
