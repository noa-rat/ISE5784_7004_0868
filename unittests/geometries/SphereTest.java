package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for primitives.Sphere class
 *
 * @author Renana and Noa
 */
class SphereTest {

    /**
     * Test method for func getNormal in primitives.Sphere
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Sphere sphere = new Sphere(2, new Point(0, 0, 1));
        assertEquals(new Vector(0, 0, 1), sphere.getNormal(new Point(0, 0, 3)),
                "Error: The sphere normals don't correct");

        // =============== Boundary Values Tests ==================
    }

    /**
     * Test method for func findIntersections in primitives.Sphere
     */
    @Test
    void findIntersections() {
        // ============ Equivalence Partitions Tests ==============
        Sphere sphere = new Sphere(2, new Point(0, 0, 1));

        // The ray starts Sphere and hits two points
        var result = sphere.findGeoIntersections(new Ray(new Point(3, 3, 0), new Vector(-1, -1, 0)));
        assertEquals(2, result.size(), "Error: there should be two intersection points");

        // The ray starts within the Sphere and hits one point
        result = sphere.findGeoIntersections(new Ray(new Point(1, 1, 0), new Vector(-1, -1, 0)));
        assertEquals(1, result.size(), "Error: there should be one intersection point");

        // The ray begins in Sphere and there is no harm
        assertNull(sphere.findGeoIntersections(new Ray(new Point(3, 3, 0), new Vector(1, 1, 0))),
                "Error: there are not intersection points");

        // The ray is outside the Sphere and does not cut it
        assertNull(sphere.findGeoIntersections(new Ray(new Point(3, 3, 0), new Vector(0, -1, 0))),
                "Error: there are not intersection points");

        // =============== Boundary Values Tests ==================
        // starts on the edge of the sphere and does not damage it
        assertNull(sphere.findGeoIntersections(new Ray(new Point(2, 0, 1), new Vector(1, 0, 0.5))),
                "Error: there are not intersection points");

        // Starts on the edge and goes inside there is one hit
        result = sphere.findGeoIntersections(new Ray(new Point(2, 0, 1), new Vector(-1, -0.5, 0)));
        assertEquals(1,
                result.size(),
                "Error: there should be one intersection points");

        // The ray launches into the sphere and does not harm it
        assertNull(sphere.findGeoIntersections(new Ray(new Point(2, -1, 1), new Vector(0, 1, 0))),
                "Error: there are not intersection points");

        // The ray starts the sphere and does not damage it
        assertNull(sphere.findGeoIntersections(new Ray(new Point(2, 0, 1), new Vector(0, 1, 0))),
                "Error: there are not intersection points");

        // The ray after the sphere and if we reversed the direction it would touch down
        assertNull(sphere.findGeoIntersections(new Ray(new Point(2, 1, 1), new Vector(0, 1, 0))),
                "Error: there are not intersection points");

        // The ray starts in the middle of the sphere and there is one hit
        result = sphere.findGeoIntersections(new Ray(new Point(0, 0, 1), new Vector(1, 1, 0)));
        assertEquals(1, result.size(), "Error: there should be one intersection points");

        // The ray starts on the edge and does not affect the sphere
        assertNull(sphere.findGeoIntersections(new Ray(new Point(0, -2, 1), new Vector(0, -1, 0))),
                "Error: there are not intersection points");

        //The ray starts on the count and goes through the center and there is one hit
        result = sphere.findGeoIntersections(new Ray(new Point(0, 2, 1), new Vector(0, -1, 0)));
        assertEquals(1, result.size(), "Error: there should be one intersection points");

        //The ray starts after the sphere and if we reverse the direction it will go through the center
        assertNull(sphere.findGeoIntersections(new Ray(new Point(0, -3, 1), new Vector(0, -1, 0))),
                "Error: there are not intersection points");

        //The ray starts in front of the shpere and goes through the center

        result = sphere.findGeoIntersections(new Ray(new Point(0, -1, 1), new Vector(0, -1, 0)));
        assertEquals(1, result.size(), "Error: there should be one intersection points");

        //The ray starts inside the sphere and if we reversed the direction it would pass through the center
        result = sphere.findGeoIntersections(new Ray(new Point(0, 3, 1), new Vector(0, -1, 0)));
        assertEquals(2, result.size(), "Error: there should be two intersection points");

        //If you draw a line from the center point to the top of the horn then it is perpendicular to the horn and it is
        // outside the sphere
        assertNull(sphere.findGeoIntersections(new Ray(new Point(2.5, 0, 1), new Vector(0, 1, 0))),
                "Error: there are not intersection points");

        //If you draw a line from the center point to the top of the ray then it is perpendicular to the ray and it is
        // inside the sphere
        result = sphere.findGeoIntersections(new Ray(new Point(1.5, 0, 1), new Vector(0, 1, 0)));
        assertEquals(1, result.size(), "Error: there should be one intersection points");
    }
}