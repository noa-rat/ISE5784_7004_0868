package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

/**
 * Unit tests for primitives.Vector class
 * @author Renana and Noa
 */
class VectorTest {

    private Vector v1 = new Vector(1, 2, 3);
    private Vector v1Opposite = new Vector(-1, -2, -3);
    private Vector v2 = new Vector(-2, -4, -6);
    private Vector v3 = new Vector(0, 3, -2);
    private Vector v4 = new Vector(1, 2, 2);

    private final double DELTA = 0.000001;

    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // =============== Boundary Values Tests ==================
        assertThrows(IllegalArgumentException.class, ()->new Vector(Double3.ZERO),
                "ERROR: zero vector does not throw an exception");
        assertThrows(IllegalArgumentException.class,()-> new Vector(0,0,0),
                "ERROR: zero vector does not throw an exception");
    }

    /**
     * Test method for func LengthSquared in primitives.Vector
     */
    @Test
    void testLengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(9, v4.lengthSquared(), DELTA,
                "ERROR: lengthSquared() wrong value");

        // =============== Boundary Values Tests ==================

    }

    /**
     * Test method for func Length in primitives.Vector
     */
    @Test
    void testLength() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(3, v4.length(), DELTA,
                "ERROR: length() wrong value");

        // =============== Boundary Values Tests ==================

    }

    /**
     * Test method for func add in primitives.Vector
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(v1Opposite, v1.add(v2),
                "ERROR: Vector + Vector does not work correctly");
        assertEquals(new Vector(3, 6, 9), v1.subtract(v2),
                "ERROR: Vector + Vector does not work correctly");

        // =============== Boundary Values Tests ==================
        assertThrows(IllegalArgumentException.class, ()->v1.add(v1Opposite),
                "ERROR: Vector + -itself does not throw an exception");
        assertThrows(IllegalArgumentException.class, ()->v1.subtract(v1),
                "ERROR: Vector - itself does not throw an exception");
    }

    /**
     * Test method for func scale in primitives.Vector
     */
    @Test
    void testScale() {
    }

    /**
     * Test method for func dotProduct in primitives.Vector
     */
    @Test
    void testDotProduct() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(-28, v1.dotProduct(v2), DELTA,
                "ERROR: dotProduct() wrong value");

        // =============== Boundary Values Tests ==================
        assertEquals(0, v1.dotProduct(v3), DELTA,
                "ERROR: dotProduct() for orthogonal vectors is not zero");
    }

    /**
     * Test method for func crossProduct in primitives.Vector
     */
    @Test
    void testCrossProduct() {
        // ============ Equivalence Partitions Tests ==============
        Vector vr = v1.crossProduct(v3);
        assertEquals(vr.length(), v1.length() * v3.length(), DELTA,
                "ERROR: crossProduct() wrong result length");
        assertEquals(0, vr.dotProduct(v1), DELTA,
                "ERROR: crossProduct() result is not orthogonal to its operands");
        assertEquals(0, vr.dotProduct(v3), DELTA,
                "ERROR: crossProduct() result is not orthogonal to its operands");

        // =============== Boundary Values Tests ==================
        assertThrows(
                IllegalArgumentException.class, ()->v1.crossProduct(v2),
                "ERROR: crossProduct() for parallel vectors does not throw an exception");
    }

    /**
     * Test method for func normalize in primitives.Vector
     */
    @Test
    void testNormalize() {
        // ============ Equivalence Partitions Tests ==============
        Vector v = new Vector(1, 2, 3);
        Vector u = v.normalize();
        assertEquals(1, u.length(), DELTA,
                "ERROR: the normalized vector is not a unit vector");
        assertThrows(IllegalArgumentException.class, ()->v.crossProduct(u),
                "ERROR: the normalized vector is not parallel to the original one");

        // =============== Boundary Values Tests ==================

    }
}