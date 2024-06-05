package renderer;

import primitives.*;
import scene.Scene;

import java.util.List;

/**
 * realizes the class RayTracerBase to track each ray
 * and determines the color of the pixel it hits
 */
public class SimpleRayTracer extends RayTracerBase{
    /**
     * constructor
     * @param scene to initialize the field scene
     */
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        List<Point> intersections = scene.geometries.findIntersections(ray);
        if (intersections == null) {
            return scene.background;
        }
        Point closestPoint = ray.findClosestPoint(intersections);
        return calcColor(closestPoint);
    }

    /**
     * Calculates the color of a pixel at a point
     * @param point - to calculate its color
     * @return the color of the point
     */
    private Color calcColor(Point point) {
        return scene.ambientLight.getIntensity();
    }
}
