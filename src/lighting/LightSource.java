package lighting;
import primitives.*;

/**
 * Adds light sources
 */
public interface LightSource {
    public Color getIntensity(Point p);
    public Vector getL(Point p);
}
