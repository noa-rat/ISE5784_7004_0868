package renderer;

import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

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
        List<GeoPoint> intersections = scene.geometries.findGeoIntersectionsHelper(ray);
        if (intersections == null) {
            return scene.background;
        }
        GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
        return calcColor(closestPoint);
    }

    /**
     * Calculates the color of a pixel at a point
     * @param point - to calculate its color
     * @return the color of the point
     */
    private Color calcColor(GeoPoint point) {
        return (scene.ambientLight.getIntensity()).add(point.geometry.getEmission());
    }
}
