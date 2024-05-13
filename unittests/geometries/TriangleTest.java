package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for primitives.Triangle class
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
                new Point(1,2,3),
                new Point(2,2,4),
                new Point(1,2,5)
        );
        assertEquals(new Vector(0,-1,0), triangle.getNormal(new Point(1,2,3)),
                "Error: The triangle normals don't correct");

        // =============== Boundary Values Tests ==================

    }
}