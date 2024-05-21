package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for primitives.Sphere class
 * @author Renana and Noa
 */
class SphereTest {

    private final Sphere sphere = new Sphere(2, new Point(0,0,1));

    /**
     * Test method for func getNormal in primitives.Sphere
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
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

        // =============== Boundary Values Tests ==================
    }
}