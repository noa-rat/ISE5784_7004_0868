package renderer;

import lighting.LightSource;
import lighting.PointLight;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

/**
 * realizes the class RayTracerBase to track each ray
 * and determines the color of the pixel it hits
 */

public class SimpleRayTracer extends RayTracerBase {
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;

    /**
     * constructor
     *
     * @param scene to initialize the field scene
     */
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        GeoPoint closestPoint = findClosestIntersection(ray);
        if (closestPoint == null) return scene.background;
        return calcColor(closestPoint, ray);
    }

    /**
     * Calculates the color of a pixel at a point
     *
     * @param gp  - to calculate its color
     * @param ray - to calculate its color
     * @return the color of the point
     */
    private Color calcColor(GeoPoint gp, Ray ray) {
        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, Double3.ONE)
                .add(scene.ambientLight.getIntensity());
    }

    /**
     * helper function to calcColor
     *
     * @param gp  - to calculate its color
     * @param ray - to calculate its color
     * @return Color
     */
    private Color calcColor(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(gp, ray, k); //.add(gp.geometry.getEmission());
        return 1 == level ? color : color.add(calcGlobalEffects(gp, ray, level, k));
    }

    /**
     * Calculate the cuts and also the cut closest to the beginning of the ray
     *
     * @param ray - The cutting ray
     * @return he cut closest to the beginning of the ray
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        return ray.findClosestGeoPoint(scene.geometries.findGeoIntersections(ray));
    }

    /**
     * Auxiliary function for the calculation of the local effects
     *
     * @param gp  The point for which the color is calculated
     * @param ray The ray that cuts the point
     * @return the color of the point
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray, Double3 k) {
        Vector n = gp.geometry.getNormal(gp.point);
        Vector v = ray.getDirection();
        double nv = Util.alignZero(n.dotProduct(v));
        if (nv == 0)
            return Color.BLACK;
        Color color = gp.geometry.getEmission();
        Material material = gp.geometry.getMaterial();

        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = Util.alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // check if it's with same sign
                Double3 ktr = transparency(gp, lightSource, l, n);
                if (!ktr.product(k).lowerThan(MIN_CALC_COLOR_K)) {
                    Color iL = lightSource.getIntensity(gp.point).scale(ktr);
                    color = color.add(iL.scale(calcDiffusive(material.kD, nl)
                            .add(calcSpecular(material, n, l, nl, v))));
                }
            }
        }
        return color;
    }

    /**
     * Helper function for  calculate Diffusive
     *
     * @param kD of the gp
     * @param nl dot product between the normal and the ray
     * @return Double3 to the level of influence of Diffusive
     */
    private Double3 calcDiffusive(Double3 kD, double nl) {
        return kD.scale(Math.abs(nl));
    }

    /**
     * Helper function for  calculate Specular
     *
     * @param material of the gp
     * @param n        normal
     * @param l        the ray from lightSource
     * @param nl       dot product between the normal and the ray
     * @param v        the ray from camera
     * @return Double3 to the level of influence of Specular
     */
    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.subtract(n.scale(2 * nl));
        double vr = -(Util.alignZero(v.dotProduct(r)));
        if (vr > 0)
            return material.kS.scale(Math.pow(vr, material.nShininess));
        else
            return Double3.ZERO;
    }

    /**
     * The function checks that there is no shading
     *
     * @param gp point of intersection with the body
     * @param l  The vector from the light orientation to the intersection point
     * @param n  normal to the body at a point
     * @return Is there a shadow
     */
    private Double3 transparency(GeoPoint gp, LightSource light, Vector l, Vector n) {
        Vector shadedDirection = l.scale(-1);
        Ray shadedRay = new Ray(gp.point, shadedDirection, n);

        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(shadedRay);
        if (intersections == null) return Double3.ONE;

        Double3 ktr = Double3.ONE;
        for (GeoPoint geoPoint : intersections) {
            if (Util.alignZero(geoPoint.point.distance(gp.point) - light.getDistance(gp.point)) <= 0) {
                ktr = ktr.product(geoPoint.geometry.getMaterial().kT);
                if (ktr.equals(Double3.ZERO))
                    break;
            }
        }
        return ktr;
    }

    /**
     * calculate the global effects
     *
     * @param gp  point to calculate
     * @param ray The ray that cuts the point
     * @return the color of global effects that affect the color of the point
     */
    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
        Vector n = gp.geometry.getNormal(gp.point);
        Vector v = ray.getDirection();
        Material material = gp.geometry.getMaterial();

        return calcGlobalEffect(constructRefractedRay(gp, v, n), material.kT, level, k) // שקיפות
                .add(calcGlobalEffect(constructReflectedRay(gp, n, v), material.kR, level, k)); // השתקפות
    }

    /**
     * Helper function for calcGlobalEffects
     *
     * @param ray The calculated ray
     * @param kx  attenuation coefficient
     * @return the color of transparency / reflection ray
     */
    private Color calcGlobalEffect(Ray ray, Double3 kx, int level, Double3 k) {
        Double3 kkx = kx.product(k);
        if (kkx.lowerThan(MIN_CALC_COLOR_K))
            return Color.BLACK;

        GeoPoint gp = findClosestIntersection(ray);
        return gp == null ? scene.background : calcColor(gp, ray, level - 1, kkx).scale(kx);
    }

    /**
     * Builds a transparency ray קרן שקיפות
     *
     * @param gp - The cutting geometry point
     * @param v  - The direction ot the cutting ray
     * @param n  - The normal to the cutting point
     * @return transparency ray
     */
    private Ray constructRefractedRay(GeoPoint gp, Vector v, Vector n) {
        return new Ray(gp.point, v, n);
    }

    /**
     * Builds a reflection ray קרן השתקפות
     *
     * @param gp - the cutting point
     * @param n  - the normal
     * @param v  - the direction
     * @return reflection ray
     */
    private Ray constructReflectedRay(GeoPoint gp, Vector n, Vector v) {
        double vn = Util.alignZero(v.dotProduct(n));
        if (vn == 0) return null;
        Vector r = v.subtract(n.scale(2 * vn));
        return new Ray(gp.point, r, n);
    }
}


