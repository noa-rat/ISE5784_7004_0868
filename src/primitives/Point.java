package primitives;

import java.util.Objects;

public class Point {
    protected final Double3 xyz;
    public static final Point ZERO = new Point(0, 0, 0);

    /**
     * @param xyz to the field of the point
     */
    public Point(Double3 xyz) {
        this.xyz = xyz;
    }

    /**
     * @param x to the x coordinate of xyz
     * @param y to the y coordinate of xyz
     * @param z to the z coordinate of xyz
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return (o instanceof Point other)
                && this.xyz.equals(other.xyz);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(xyz);
    }

    @Override
    public String toString() {
        return xyz.toString();
    }

    /**
     * add point and vector
     *
     * @param vector to add
     * @return point
     */
    public Point add(Vector vector) {
        return new Point(this.xyz.add(vector.xyz));
    }

    /**
     * subtract point and point
     *
     * @param point to sub
     * @return vector
     */
    public Vector subtract(Point point) {
        return new Vector(this.xyz.subtract(point.xyz));
    }

    /**
     * @param point Calculate the distance squared
     * @return the distance squared
     */
    public double distanceSquared(Point point) {
        return (this.xyz.d1 - point.xyz.d1) * (this.xyz.d1 - point.xyz.d1) +
                (this.xyz.d2 - point.xyz.d2) * (this.xyz.d2 - point.xyz.d2) +
                (this.xyz.d3 - point.xyz.d3) * (this.xyz.d3 - point.xyz.d3);
    }

    /**
     * @param point to Calculate the distance
     * @return distance between two points
     */
    public double distance(Point point) {
        return Math.sqrt(distanceSquared(point));
    }
}
