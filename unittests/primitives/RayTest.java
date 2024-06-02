package primitives;

import geometries.Triangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    @Test
    void getPoint() {
        Ray ray=new Ray(new Point(1,0,0),new Vector(1,0,0));

        // ============ Equivalence Partitions Tests ==============

        assertEquals(new Point(3,0,0),ray.getPoint(2),"Error:The distance should be positive");
        assertEquals(new Point(-1,0,0),ray.getPoint(-2),"Error:The distance should be negative");

        // =============== Boundary Values Tests ==================
        assertEquals(new Point(1,0,0),ray.getPoint(0),"Error:The distance should be zero");
    }
}