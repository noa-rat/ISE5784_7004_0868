package geometries;

import primitives.*;

import java.util.List;

public class Triangle extends Polygon {
    /**
     * Builds a triangle by 3 vertices
     * and checks that they are not on the same line
     *
     * @param vertices - The list of vertices of the triangle
     */
    public Triangle(Point... vertices) {
        super(vertices);

        // Denominator מכנה
        // numerator מונה
        Vector v1 = (this.vertices.get(0)).subtract(this.vertices.get(1));
        Vector v2 = (this.vertices.get(1)).subtract(this.vertices.get(2));
        Vector v3 = (this.vertices.get(2)).subtract(this.vertices.get(3));
        double numerator1 = (v1).dotProduct(v2);
        double Denominator1 = (v1.length())*(v2.length());
        double angle1 = numerator1 / Denominator1;
        double result1 = Math.cos(angle1);

        if (Util.isZero(result1 - 1))
        {
            double numerator2 = (v1).dotProduct(v3);
            double Denominator2 = (v1.length())*(v3.length());
            double angle2 = numerator2 / Denominator2;
            double result2 = Math.cos(angle2);

            if (Util.isZero(result2 - 1)) {
                throw new IllegalArgumentException("the point on the same straight");
            }
        }
    }

    /**
     * Finds points of intersection of the triangle with the ray
     *
     * @param ray to calculation points of intersection with the triangle
     * @return list of points of intersection with the triangle
     */
    @Override
    public List<Point> findIntersections(Ray ray) {

        List<Point> intersection = plane.findIntersections(ray);

        if (intersection != null) {
            Vector v1 = (vertices.get(0)).subtract(ray.getHead());
            Vector v2 = (vertices.get(1)).subtract(ray.getHead());
            Vector v3 = (vertices.get(2)).subtract(ray.getHead());

            Vector n1 = (v1.crossProduct(v2)).normalize();
            Vector n2 = (v2.crossProduct(v3)).normalize();
            Vector n3 = (v3.crossProduct(v1)).normalize();

            double d1 = (ray.getDirection()).dotProduct(n1);
            double d2 = (ray.getDirection()).dotProduct(n2);
            double d3 = (ray.getDirection()).dotProduct(n3);

            if (((d1 > 0) && (d2 > 0) && (d3 > 0)) || ((d1 < 0) && (d2 < 0) && (d3 < 0))) {
                return intersection;
            } else {
                return null;
            }
        }
        return null;
    }
}
