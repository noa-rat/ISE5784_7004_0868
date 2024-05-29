package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for primitives.Tube class
 *
 * @author Renana and Noa
 */
class TubeTest {

    /**
     * Test method for func getNormal in primitives.Tube
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Tube tube = new Tube(2, new Ray(new Point(1, 0, 0), new Vector(0, 1, 0)));

        assertEquals(new Vector(0, 0, 1), tube.getNormal(new Point(1, 4, 2)),
                "Error: The tube normals don't correct");

        // =============== Boundary Values Tests ==================////*
        assertThrows(IllegalArgumentException.class, () -> tube.getNormal(new Point(2, 0, 0)),
                "Error: need throw exception, The zero vector is created, " +
                        "because the point is in front of the head of the ray");


        // assertEquals(new Vector(0,1,0), tube.getNormal(new Point(2,0,0)),
        //        "Error: need throw exception, The zero vector is created, " +
        //        "because the point is in front of the head of the ray");


        // p = (2,0,0)
        // p0 = (1,0,0)
        // v = (0,1,0)
        // p - p0 = (1,0,0)
        // t = (0,1,0)x(1,0,0) = 0
        // o = (1,0,0) + 0 x (0,1,0)
        // p - o = (1,0,0)
    }
}