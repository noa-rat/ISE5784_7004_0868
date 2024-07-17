package renderer;

import primitives.*;
import scene.Scene;

/**
 * abstract class to track each ray and
 * determines the color of the pixel it hits
 */
public abstract class RayTracerBase {
    protected Scene scene;

    /**
     * constructor
     *
     * @param scene to initialize the field scene
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /**
     * Tracks each ray and determines the color of the pixel it hits
     *
     * @param ray - The ray after which the tracking is performed
     * @return the color of the pixel it hits
     */
    public abstract Color traceRay(Ray ray);
}
