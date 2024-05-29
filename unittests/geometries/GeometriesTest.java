package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for primitives.Geometries class
 *
 * @author Renana and Noa
 */
class GeometriesTest {

    @Test
    void testAdd() {
    }

    @Test
    void testFindIntersections() {
        // ============ Equivalence Partitions Tests ==============

        // =============== Boundary Values Tests ==================
        // The bodies collection is empty
        Geometries geometries1 = new Geometries();
        assertNull(geometries1.findIntersections(
                        new Ray(new Point(30, 30, 30), new Vector(1, 1, 1))),
                "Error: there are not geometies in the list");

        // there are not intersection points
        Plane plane = new Plane(
                new Point(1, 0, 0),
                new Point(0, 1, 0),
                new Point(0, 0, 0)
        );
        Sphere sphere = new Sphere(2,
                new Point(0, 0, 1));

        Triangle triangle = new Triangle(
                new Point(1, 2, 3),
                new Point(2, 2, 4),
                new Point(1, 2, 5)
        );
        Geometries geometries2 = new Geometries(plane,sphere, triangle);
        assertNull(geometries2.findIntersections(
                        new Ray(new Point(30, 30, 30), new Vector(1, 1, 1))),
                "Error: there are not intersection points");

        // one geometry is cut
        plane = new Plane(
                new Point(1, 0, 4),
                new Point(0, 1, 4),
                new Point(0, 0, 4)
        );
        sphere = new Sphere(1,
                new Point(0, 0, -3));

        triangle = new Triangle(
                new Point(-1, -2, -3),
                new Point(-2, -2, -4),
                new Point(-1, -2, -5)
        );
        geometries2 = new Geometries(plane,sphere, triangle);
        var result = geometries2.findIntersections(
                new Ray(new Point(0, 0, 2), new Vector(0, 0, 1)));
        assertEquals(1, result.size(), "Error: ne geometry is cut, one intersection point");
    }
}