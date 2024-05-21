package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for primitives.Sphere class
 * @author Renana and Noa
 */
class SphereTest {

    /**
     * Test method for func getNormal in primitives.Sphere
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Sphere sphere = new Sphere(2, new Point(0,0,1));
        assertEquals(new Vector(0,0,1), sphere.getNormal(new Point(0,0,3)),
                "Error: The sphere normals don't correct");

        // =============== Boundary Values Tests ==================
    }

    /**
     * Test method for func findIntersections in primitives.Sphere
     */
    @Test
    void findIntersections() {
        // ============ Equivalence Partitions Tests ==============
        Sphere sphere = new Sphere(2, new Point(0,0,1));

        var result = sphere.findIntersections(new Ray(new Point(3, 3, 0), new Vector(-1,-1,0)));
        assertEquals(2, result.size(), "Error: there should be two intersection points");

        result = sphere.findIntersections(new Ray(new Point(1, 1, 0), new Vector(-1,-1,0)));
        assertEquals(1, result.size(), "Error: there should be one intersection point");

        assertEquals(null,
                sphere.findIntersections(new Ray(new Point(3, 3, 0), new Vector(1,1,0))),
                "Error: there are not intersection points");

        assertEquals(null,
                sphere.findIntersections(new Ray(new Point(3, 3, 0), new Vector(0,-1,0))),
                "Error: there are not intersection points");

        // =============== Boundary Values Tests ==================
        assertEquals(null,
                sphere.findIntersections(new Ray(new Point(2, 0, 1), new Vector(1,0.5,0))),
                "Error: there are not intersection points");

        result = sphere.findIntersections(new Ray(new Point(2, 0, 1), new Vector(-1,-0.5,0)));
        assertEquals(1,
                result.size(),
                "Error: there should be one intersection points");

        assertEquals(null,
                sphere.findIntersections(new Ray(new Point(2, -1, 1), new Vector(0,1,0))),
                "Error: there are not intersection points");

        assertEquals(null,
                sphere.findIntersections(new Ray(new Point(2, 0, 1), new Vector(0,1,0))),
                "Error: there are not intersection points");

        assertEquals(null,
                sphere.findIntersections(new Ray(new Point(2, 1, 1), new Vector(0,1,0))),
                "Error: there are not intersection points");

        result = sphere.findIntersections(new Ray(new Point(0, 0, 1), new Vector(1,1,0)));
        assertEquals(1, result.size(), "Error: there should be one intersection points");

        assertEquals(null,
                sphere.findIntersections(new Ray(new Point(0, -2, 1), new Vector(0,-1,0))),
                "Error: there are not intersection points");

        result = sphere.findIntersections(new Ray(new Point(0, 2, 1), new Vector(0,-1,0)));
        assertEquals(1, result.size(), "Error: there should be one intersection points");

        assertEquals(null,
                sphere.findIntersections(new Ray(new Point(0, -3, 1), new Vector(0,-1,0))),
                "Error: there are not intersection points");

        result = sphere.findIntersections(new Ray(new Point(0, -1, 1), new Vector(0,-1,0)));
        assertEquals(1, result.size(), "Error: there should be one intersection points");

        result = sphere.findIntersections(new Ray(new Point(0, 3, 1), new Vector(0,-1,0)));
        assertEquals(2, result.size(), "Error: there should be two intersection points");

        assertEquals(null,
                sphere.findIntersections(new Ray(new Point(2.5, 0, 1), new Vector(0,1,0))),
                "Error: there are not intersection points");

        result = sphere.findIntersections(new Ray(new Point(1.5, 0, 1), new Vector(0,1,0)));
        assertEquals(1, result.size(), "Error: there should be one intersection points");
    }
}