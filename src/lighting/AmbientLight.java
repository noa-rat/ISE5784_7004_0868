package lighting;

import primitives.*;

public class AmbientLight extends  Light {
    public static final AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);

    /**
     * constructor
     * @param iA to the Original fill light
     * @param kA to the attenuation coefficient of the fill light
     */
    public AmbientLight(Color iA, Double3 kA) {
        super(iA.scale(kA));
    }

    /**
     * default constructor
     */
    public AmbientLight() {
        super(Color.BLACK);
    }


}
