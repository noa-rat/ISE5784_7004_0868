package lighting;

import primitives.*;

/**
 * Spot light source
 */
public class SpotLight extends PointLight {
    private Vector direction;

    @Override
    public PointLight setkC(double kC) {
        super.setkC(kC);
        return this;
    }

    @Override
    public PointLight setkL(double kL) {
        super.setkL(kL);
        return this;
    }

    @Override
    public PointLight setkQ(double kQ) {
        super.setkQ(kQ);
        return this;
    }

    /**
     * constructor
     *
     * @param intensity ->to the filed intensity
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point p) {
        double dirL = (direction.dotProduct(getL(p)));
        return super.getIntensity(p).scale(Math.max(0, dirL));
    }
}
