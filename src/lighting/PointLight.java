package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Point light source
 */
public class PointLight extends Light implements LightSource {
    protected Point position;
    private double kC = 1;
    private double kL = 0;
    private double kQ = 0;

    /**
     * getter of kC
     *
     * @param kC to the field kC
     * @return The object that called the function
     */
    public PointLight setkC(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     * getter of kL
     *
     * @param kL to the field kL
     * @return The object that called the function
     */
    public PointLight setkL(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * getter of kQ
     *
     * @param kQ to the field kQ
     * @return The object that called the function
     */
    public PointLight setkQ(double kQ) {
        this.kQ = kQ;
        return this;
    }

    /**
     * constructor
     *
     * @param intensity ->to the filed intensity
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    @Override
    public Color getIntensity(Point p) {
        double distance = position.distance(p);
        double distanceSquared = position.distanceSquared(p);
        double denominator = kC + (kL * distance) + kQ * distanceSquared;
        return getIntensity().scale(1 / denominator);
    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }

    @Override
    public double getDistance(Point point) {
        return point.distance(position);
    }
}