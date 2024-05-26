package primitives;

public class Vector extends Point {
    /**
     * @param xyz to the field of the vector
     */
    public Vector(Double3 xyz) {
        super(xyz);

        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("Vector is zero");
    }

    /**
     * @param x to the x coordinate of xyz
     * @param y to the y coordinate of xyz
     * @param z to the z coordinate of xyz
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);

        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("Vector is zero");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return (o instanceof Vector other)
                && super.equals(other);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "->" + super.toString();
    }

    /**
     * @return length Squared of the vector,
     * between xyz to the beginning of labor
     */
    public double lengthSquared() {
        return dotProduct(this);
    }

    /**
     * @return length of the vector,
     * between xyz to the beginning of labor
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * add two vectors
     *
     * @param vector to add
     * @return vector
     */
    public Vector add(Vector vector) {
        return new Vector(xyz.add(vector.xyz));
    }

    /**
     * Vector multiplication in scalar
     *
     * @param scale to the multiplication
     * @return Vector
     */
    public Vector scale(double scale) {
        return new Vector(xyz.scale(scale));
    }

    /**
     * Scalar product between vectors
     *
     * @param vector to the Scalar product
     * @return double
     */
    public double dotProduct(Vector vector) {
        return xyz.d1 * vector.xyz.d1 + xyz.d2 * vector.xyz.d2 + xyz.d3 * vector.xyz.d3;
    }

    /**
     * Vector product between vectors
     *
     * @param vector to the Vector product
     * @return Vector
     */
    public Vector crossProduct(Vector vector) {
        return new Vector(
                (xyz.d2 * vector.xyz.d3) - (xyz.d3 * vector.xyz.d2),
                (xyz.d3 * vector.xyz.d1) - (xyz.d1 * vector.xyz.d3),
                (xyz.d1 * vector.xyz.d2) - (xyz.d2 * vector.xyz.d1)
        );
    }

    /**
     * Normalizes the vector to the unit vector
     *
     * @return Vector
     */
    public Vector normalize() {
        return new Vector(xyz.reduce(this.length()));
    }
}
