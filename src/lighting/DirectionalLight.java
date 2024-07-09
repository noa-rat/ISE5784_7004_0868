package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * directional light source
 */
public class DirectionalLight extends Light implements LightSource {
    private Vector direction;

    /**
     * constructor
     *
     * @param intensity ->to the filed intensity
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point p) {
        return getIntensity();
    }

    @Override
    public Vector getL(Point p) {
        return direction;
    }

    @Override
    public double getDistance(Point point) {
        return Double.POSITIVE_INFINITY;
    }
}
