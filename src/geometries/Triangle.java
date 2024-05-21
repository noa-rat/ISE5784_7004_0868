package geometries;

import primitives.Point;
import primitives.Ray;

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
        return null;
    }
}
