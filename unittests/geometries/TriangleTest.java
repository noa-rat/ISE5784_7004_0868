package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for primitives.Triangle class
 *
 * @author Renana and Noa
 */
class TriangleTest {

    /**
     * Test method for func getNormal in primitives.Triangle
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Triangle triangle = new Triangle(
                new Point(1, 2, 3),
                new Point(2, 2, 4),
                new Point(1, 2, 5)
        );
        assertEquals(new Vector(0, -1, 0), triangle.getNormal(new Point(1, 2, 3)),
                "Error: The triangle normals don't correct");

        // =============== Boundary Values Tests ==================

    }

    /**
     * Test method for func getNormal in primitives.Triangle
     */
    @Test
    void testfindIntersections() {
        Triangle triangle = new Triangle(
                new Point(1, 0, 0),
                new Point(3, 0, 0),
                new Point(1, 3, 0)
        );

        // ============ Equivalence Partitions Tests ==============
        //The point inside the triangle
        var result = triangle.findGeoIntersectionsHelper(new Ray(new Point(2, 0.5, -1), new Vector(0, 0, 1)));
        assertEquals(1, result.size(), "Error: there should be one intersection points");

        //The point outside the triangle opposite a side
        assertNull(triangle.findGeoIntersectionsHelper(new Ray(new Point(2, -1, -1), new Vector(0, 0, 1))),
                "Error: there are not intersection points");

        //The point outside the triangle opposite a vertex
        assertNull(triangle.findGeoIntersectionsHelper(new Ray(new Point(0.5, 4, -1), new Vector(0, 0, 1))),
                "Error: there are not intersection points");

        // =============== Boundary Values Tests ==================
        //The point on a side of the triangle
        assertNull(triangle.findGeoIntersectionsHelper(new Ray(new Point(2, 0, -1), new Vector(0, 0, 1))),
                "Error: there are not intersection points");

        //The point is on the vertex of the triangle
        assertNull(triangle.findGeoIntersectionsHelper(new Ray(new Point(1, 0, -1), new Vector(0, 0, 1))),
                "Error: there are not intersection points");

        //The point on the continuation of a side of the triangle
        assertNull(triangle.findGeoIntersectionsHelper(new Ray(new Point(4, 0, -1), new Vector(0, 0, 1))),
                "Error: there are not intersection points");
    }
}