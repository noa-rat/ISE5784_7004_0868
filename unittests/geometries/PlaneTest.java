package geometries;

import org.junit.jupiter.api.Test;
import primitives.Double3;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for primitives.Plane class
 * @author Renana and Noa
 */
class PlaneTest {

    private final Point x = new Point(1,2,3);
    private final Point y = new Point(2,2,4);
    private final Point y1 = new Point(1,2,4);
    private final Point z = new Point(1,2,5);
    private final Vector vector1 = new Vector(0,-1,0);

    private final Plane plane = new Plane(x, y, z);

    /**
     * Test method for func getNormal in primitives.Plane
     */
    @Test
    void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        assertDoesNotThrow(() ->new Plane(x,y,z));//*
        // =============== Boundary Values Tests ==================
        assertThrows(IllegalArgumentException.class, () -> new Plane(x,y1,z),
                "Error: need throw exception, the points are on the same line");

        assertThrows(IllegalArgumentException.class, () -> new Plane(x,x,z),
                "Error: need throw exception, the first and second points are connected");
    }

    /**
     * Test method for func getNormal in primitives.Plane
     */
    @Test
    void testGetNormal() {
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

        // =============== Boundary Values Tests ==================
    }


}