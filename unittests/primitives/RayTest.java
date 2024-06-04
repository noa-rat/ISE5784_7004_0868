package primitives;

import geometries.Triangle;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 */
class RayTest {

    /**
     *
     */
    @Test
    void getPoint() {
        Ray ray=new Ray(new Point(1,0,0),new Vector(1,0,0));

        // ============ Equivalence Partitions Tests ==============

        assertEquals(new Point(3,0,0),ray.getPoint(2),"Error:The distance should be positive");
        assertEquals(new Point(-1,0,0),ray.getPoint(-2),"Error:The distance should be negative");

        // =============== Boundary Values Tests ==================
        assertEquals(new Point(1,0,0),ray.getPoint(0),"Error:The distance should be zero");
    }

    /**
     *
     */
    @Test
    void testFindClosestPoint() {
        // ============ Equivalence Partitions Tests ==============
        List<Point> points = new LinkedList<Point>();
        points.add(new Point(0, 10,0));
        points.add(new Point(0, 1,0));
        points.add(new Point(0, 5, 0));

        assertEquals(new Point(0,1,0), (new Ray(Point.ZERO, new Vector(1,0,0))).findClosestPoint(points),
                "Error: the point should be (0,1,0)");

        // =============== Boundary Values Tests ==================

    }
}