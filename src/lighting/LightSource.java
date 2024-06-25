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
     *
     * @param p
     * @return
     */
    public Vector getL(Point p);
}
