package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for primitives.Plane class
 *
 * @author Renana and Noa
 */
class PlaneTest {

    /**
     * Test method for func getNormal in primitives.Plane
     */
    @Test
    void testConstructor() {
        Point x = new Point(1, 2, 3);
        Point y = new Point(2, 2, 4);
        Point y1 = new Point(1, 2, 4);
        Point z = new Point(1, 2, 5);
        Vector vector1 = new Vector(0, -1, 0);

        Plane plane = new Plane(x, y, z);
        // ============ Equivalence Partitions Tests ==============
        assertDoesNotThrow(() -> new Plane(x, y, z));//*
        // =============== Boundary Values Tests ==================
        assertThrows(IllegalArgumentException.class, () -> new Plane(x, y1, z),
                "Error: need throw exception, the points are on the same line");

        assertThrows(IllegalArgumentException.class, () -> new Plane(x, x, z),
                "Error: need throw exception, the first and second points are connected");
    }

    /**
     * Test method for func getNormal in primitives.Plane
     */
    @Test
    void testGetNormal() {
        Point x = new Point(1, 2, 3);
        Point y = new Point(2, 2, 4);
        Point y1 = new Point(1, 2, 4);
        Point z = new Point(1, 2, 5);
        Vector vector1 = new Vector(0, -1, 0);

        Plane plane = new Plane(x, y, z);
        // ============ Equivalence Partitions Tests ==============
        assertEquals(vector1, plane.getNormal(),
                "Error: The plane normals don't correct");
        assertEquals(vector1, plane.getNormal(x),
                "Error: The plane normals don't correct");

        // =============== Boundary Values Tests ==================

    }

    ///**
    // * Test method for func getNormal in primitives.Point
    // */
    //@Test
    //void testTestGetNormal() {
    //}

    /**
     * Test method for func findIntersections in primitives.Plane
     */
    @Test
    void findIntersections() {
        // ============ Equivalence Partitions Tests ==============
        Plane plane = new Plane(
                new Point(1, 0, 0),
                new Point(0, 1, 0),
                new Point(0, 0, 0)
        );

        // the ray intersects tha plane
        var result = plane.findIntersections(new Ray(new Point(0, 0, -1), new Vector(0, 1, 1)));
        assertEquals(1,
                result.size(),
                "Error: there should be one intersection points");

        // the ray doesn't intersects the plane
        assertNull(plane.findIntersections(new Ray(new Point(0, 0, -1), new Vector(0, -1, -1))),
                "Error: there are not intersection points");

        // =============== Boundary Values Tests ==================
        // ??? - הקרן על המישור, מה צריכה להיות התוצאה
        assertNull(plane.findIntersections(new Ray(new Point(3, 4, 0), new Vector(1, 1, 0))),
                "Error: there are not intersection points");

        // the ray is parallel and not on the plane
        assertNull(plane.findIntersections(new Ray(new Point(3, 4, 1), new Vector(1, 1, 0))),
                "Error: there are not intersection points");

        // the ray is perpendicular and after the plane
        assertNull(plane.findIntersections(new Ray(new Point(3, 4, 1), new Vector(0, 0, 1))),
                "Error: there are not intersection points");

        // the ray is perpendicular to the plane
        assertNull(plane.findIntersections(new Ray(new Point(3, 4, 0), new Vector(0, 0, 1))),
                "Error: there are not intersection points");

        // the ray is perpendicular and in front of the plane
        result = plane.findIntersections(new Ray(new Point(3, 4, -1), new Vector(0, 0, 1)));
        assertEquals(1, result.size(), "Error: there are not intersection points");

        // the ray starts at the same point that the plane is represented by
        assertNull(plane.findIntersections(new Ray(new Point(1, 0, 0), new Vector(0, 1, 1))),
                "Error: there should be one intersection points");

        // the ray starts on the plane
        assertNull(plane.findIntersections(new Ray(new Point(3, 4, 0), new Vector(0, 1, 1))),
                "Error: there should be one intersection points");
    }


}