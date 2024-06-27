package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

/**
 * Unit tests for primitives.Point class
 *
 * @author Renana and Noa
 */
public class PointTest {

    private final Point p1 = new Point(1, 2, 3);
    private final Point p2 = new Point(2, 4, 6);
    private final Point p3 = new Point(2, 4, 5);
    private final Vector v1 = new Vector(1, 2, 3);
    private final Vector v1Opposite = new Vector(-1, -2, -3);

    private final double DELTA = 0.000001;

    /**
     * Test method for func add in primitives.Point
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(p2, p1.add(v1),
                "ERROR: (point + vector) = other point does not work correctly");

        // =============== Boundary Values Tests ==================
        assertEquals(Point.ZERO, p1.add(v1Opposite),
                "ERROR: (point + vector) = center of coordinates does not work correctly");
    }

    /**
     * Test method for func subtract in primitives.Point
     */
    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(v1, p2.subtract(p1),
                "ERROR: (point2 - point1) does not work correctly");

        // =============== Boundary Values Tests ==================
        assertThrows(IllegalArgumentException.class, () -> p1.subtract(p1),
                "ERROR: (point2 - point1) does not work correctly");
    }

    /**
     * Test method for func distanceSquared in primitives.Point
     */
    @Test
    void testDistanceSquared() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(9, p1.distanceSquared(p3), DELTA,
                "ERROR: squared distance between points is wrong");
        assertEquals(9, p3.distanceSquared(p1), 0.001,
                "ERROR: squared distance between points is wrong");

        // =============== Boundary Values Tests ==================
        assertEquals(0, p1.distanceSquared(p1), DELTA,
                "ERROR: point squared distance to itself is not zero");
    }

    /**
     * Test method for func distance in primitives.Point
     */
    @Test
    void testDistance() {
        Point p1 = new Point(1, 2, 3);
        Point p3 = new Point(2, 4, 5);

        // ============ Equivalence Partitions Tests ==============
        assertEquals(3, p1.distance(p3), DELTA,
                "ERROR: distance between points to itself is wrong");
        assertEquals(3, p3.distance(p1), DELTA,
                "ERROR: distance between points to itself is wrong");

        // =============== Boundary Values Tests ==================
        assertEquals(0, p1.distance(p1), DELTA,
                "ERROR: point distance to itself is not zero");
    }
}