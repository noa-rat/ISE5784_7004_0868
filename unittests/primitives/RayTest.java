package primitives;

import geometries.Triangle;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for primitives.Ray class
 *
 * @author Renana and Noa
 */
class RayTest {

    /**
     * Test method for func getPoint in primitives.Ray
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
     * Test method for func testFindClosestPoint in primitives.Ray
     */
    @Test
    void testFindClosestPoint() {
        // ============ Equivalence Partitions Tests ==============
        List<Point> points = new LinkedList<Point>();
        points.add(new Point(10, 0,0));
        points.add(new Point(1, 0,0));
        points.add(new Point(5, 0, 0));

        assertEquals(new Point(1,0,0), (new Ray(Point.ZERO, new Vector(1,0,0))).findClosestPoint(points),
                "Error: the point should be (1,0,0)");

        // =============== Boundary Values Tests ==================
        // the list is empty
        points.clear();
        assertNull((new Ray(Point.ZERO, new Vector(1,0,0))).findClosestPoint(points),
                "Error: the list is empty");

        // the close point is first in the list
        points.add(new Point(1, 0,0));
        points.add(new Point(10, 0,0));
        points.add(new Point(5, 0, 0));
        assertEquals(new Point(1,0,0), (new Ray(Point.ZERO, new Vector(1,0,0))).findClosestPoint(points),
                "Error: the point should be (1,0,0)");

        // the close point is last in the list
        points.clear();
        points.add(new Point(10, 0,0));
        points.add(new Point(5, 0, 0));
        points.add(new Point(1, 0,0));
        assertEquals(new Point(1,0,0), (new Ray(Point.ZERO, new Vector(1,0,0))).findClosestPoint(points),
                "Error: the point should be (1,0,0)");
    }
}