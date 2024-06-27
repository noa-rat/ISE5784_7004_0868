package lighting;
import primitives.*;

/**
 * Adds light sources
 */
public interface LightSource {
    /**
     * Returns the field intensity
     * @param p The point that is painted
     * @return color of the point
     */
    public Color getIntensity(Point p);

    /**
     *The vector from the light towards the point of intersection with the body
     * @param p  intersection point
     * @return vector
     */
    public Vector getL(Point p);

    /**
     * The function calculates the distance between the light source and the point
     * @param point on the geometry
     * @return the distance
     */
    public double getDistance(Point point);
}
