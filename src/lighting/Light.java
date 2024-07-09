package lighting;

import primitives.Color;

/**
 * Adds light sources to the scene
 */
abstract class Light {
    protected Color intensity;

    /**
     * constructor
     *
     * @param intensity->to the filed intensity
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * getter function
     *
     * @return intensity
     */
    public Color getIntensity() {
        return intensity;
    }

}
