package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for primitives.Cylinder class
 *
 * @author Renana and Noa
 */
class CylinderTest {

    /**
     * Test method for func getNormal in primitives.Cylinder
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Cylinder cylinder = new Cylinder(2,
                new Ray(new Point(1, 0, 0), new Vector(0, 1, 0)),
                4);
        assertEquals(new Vector(1, 0, 0), cylinder.getNormal(new Point(3, 1, 0)),
                "Error: The cylinder normals don't correct");
        assertEquals(new Vector(0, 1, 0), cylinder.getNormal(new Point(2, 0, 0)),
                "Error: The cylinder normals don't correct");
        assertEquals(new Vector(0, 1, 0), cylinder.getNormal(new Point(2, 4, 0)),
                "Error: The cylinder normals don't correct");

        // =============== Boundary Values Tests ==================
        assertEquals(new Vector(0, 1, 0), cylinder.getNormal(new Point(1, 0, 0)),
                "Error: The cylinder normals don't correct");
        assertEquals(new Vector(0, 1, 0), cylinder.getNormal(new Point(1, 4, 0)),
                "Error: The cylinder normals don't correct");
    }
}