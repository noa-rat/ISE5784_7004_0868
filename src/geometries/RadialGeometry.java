package geometries;

public abstract class RadialGeometry extends Geometry {
    protected final double radius;

    /**
     * @param radius of the shape
     */
    public RadialGeometry(double radius) {
        this.radius = radius;
    }
}
