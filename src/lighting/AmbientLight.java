package lighting;

import primitives.*;

public class AmbientLight {
    // Fill light intensity
    private final Color intensity;

    public static final AmbientLight NONE = new AmbientLight(Color.BLACK, 0);

    /**
     * constructor
     * @param iA to the Original fill light
     * @param kA to the attenuation coefficient of the fill light
     */
    public AmbientLight(Color iA, Double3 kA) {
        intensity = iA.scale(kA);
    }

    /**
     * constructor
     * @param iA to the Original fill light
     * @param kA to the attenuation coefficient of the fill light
     */
    public AmbientLight(Color iA, double kA) {
        intensity = iA.scale(kA);
    }

    /**
     *
     * @return the intensity
     */
    public Color getIntensity() {
        return intensity;
    }
}
