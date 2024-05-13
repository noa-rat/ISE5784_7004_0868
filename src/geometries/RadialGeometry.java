package geometries;

public abstract class RadialGeometry implements Geometry{
    protected final double radius;

    /**
     *
     * @param radius of the shape
     */
    public RadialGeometry(double radius) {
        this.radius = radius;
    }
}
