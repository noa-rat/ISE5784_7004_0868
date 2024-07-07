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
     * @param scene to initialize the field scene
     */
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        GeoPoint closestPoint = findClosestIntersection(ray);
        if (closestPoint == null) return scene.background;
        return calcColor(closestPoint,ray);
    }

    /**
     * Calculates the color of a pixel at a point
     * @param gp - to calculate its color
     * @param ray - to calculate its color
     * @return the color of the point
     */
    private Color calcColor(GeoPoint gp, Ray ray) {
        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, Double3.ONE)
                .add(scene.ambientLight.getIntensity());
    }

    /**
     * helper function to calcColor
     * @param gp - to calculate its color
     * @param ray - to calculate its color
     * @return Color
     */
    private Color calcColor(GeoPoint gp,Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(gp,ray, k); //.add(gp.geometry.getEmission());
        return 1 == level ? color : color.add(calcGlobalEffects(gp, ray, level, k));}

    /**
     * Calculate the cuts and also the cut closest to the beginning of the ray
     * @param ray - The cutting ray
     * @return he cut closest to the beginning of the ray
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        if (intersections == null) return null;
        return ray.findClosestGeoPoint(intersections);
    }

    /**
     * Auxiliary function for the calculation of the local effects
     * @param gp The point for which the color is calculated
     * @param ray The ray that cuts the point
     * @return the color of the point
     */
    private Color calcLocalEffects(GeoPoint gp,Ray ray, Double3 k){
        Vector n = gp.geometry.getNormal(gp.point);
        Vector v = ray.getDirection();
        double nv = Util.alignZero(n.dotProduct(v));
        Color color = gp.geometry.getEmission();
        if(nv == 0)
            return color;
        Material material = gp.geometry.getMaterial();
        for(LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = Util.alignZero(n.dotProduct(l));
            if(nl * nv > 0) { // && unshaded(gp,lightSource,l,n,nl)) {
                Double3 ktr = transparency(gp, lightSource, l, n);
                if (!(ktr.product(k).lowerThan(MIN_CALC_COLOR_K))) {
                    Color iL = lightSource.getIntensity(gp.point).scale(ktr);
                    color = color.add(calcDiffusive(material,nl,iL))
                            .add(calcSpecular(material,n,l,nl,v,iL));
                }
            }
        }
        return color;
    }

    /**
     * Helper function for  calculate Diffusive
     * @param material of the gp
     * @param nl dot product between the normal and the ray
     * @return Double3 to the level of influence of Diffusive
     */
    private Color calcDiffusive(Material material,double nl,Color iL)
    {
            return iL.scale(material.kD.scale(Math.abs(nl)));
    }

    /**
     * Helper function for  calculate Specular
     * @param material of the gp
     * @param n normal
     * @param l the ray from lightsource
     * @param nl dot product between the normal and the ray
     * @param v the ray from camera
     * @return Double3 to the level of influence of Specular
     */
    private Color calcSpecular (Material material, Vector n, Vector l, double nl, Vector v, Color iL){
        Vector r = l.subtract(n.scale(2 * nl));
        double vr = -(Util.alignZero(v.dotProduct(r)));
        if (vr > 0)
            return iL.scale(material.kS.scale(Math.pow(vr,material.nShininess)));
        else
            return Color.BLACK;
    }

    /**
     * The function checks that there is no shading
     * @param gp point of intersection with the body
     * @param l The vector from the light orientation to the intersection point
     * @param n normal to the body at a point
     * @return Is there a shadow
     */
    private Double3 transparency(GeoPoint gp, LightSource light, Vector l, Vector n) {
        // if (gp.geometry.getMaterial().kT != Double3.ZERO)
          //  return true;
        Vector shadedDirection = l.scale(-1);
        Ray shadedRay = new Ray(gp.point, shadedDirection);
        Ray moveShadedRay = shadedRay.movingRayHead(n);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(moveShadedRay);
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
     * @param gp point to calculate
     * @param ray The ray that cuts the point
     * @return the color of global effects that affect the color of the point
     */
    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
        Vector n = gp.geometry.getNormal(gp.point);
        Vector v = ray.getDirection();
        double vn = Util.alignZero(v.dotProduct(n));
        Material material = gp.geometry.getMaterial();
        return calcGlobalEffect(constructRefractedRay(ray, n), material.kT, level, k)//שקיפות
                .add(calcGlobalEffect(constructReflectedRay(gp, n, v, vn), material.kR, level, k));//השתקפות
    }

    /**
     * Helper function for calcGlobalEffects
     * @param ray The calculated ray
     * @param kx attenuation coefficient
     * @return the color of transparency/ reflection ray
     */
    private Color calcGlobalEffect(Ray ray, Double3 kx, int level, Double3 k) {
        Double3 kkx = kx.product(k);
        if (kkx.lowerThan(MIN_CALC_COLOR_K))
            return Color.BLACK;
        GeoPoint gp = findClosestIntersection(ray);
        return gp == null ? scene.background : calcColor(gp,ray, level - 1, kkx).scale(kx);
    }

    /**
     * Builds a transparency ray קרן שקיפות
     * @param ray - The cutting ray
     * @param n - The normal to the cutting point
     * @return transparency ray
     */
    private Ray constructRefractedRay(Ray ray, Vector n) {
        return ray.movingRayHead(n);
    }

    /**
     * Builds a reflection ray קרן השתקפות
     * @param gp - the cutting point
     * @param n - the normal
     * @param v - the direction
     * @param vn - the dot product
     * @return reflection ray
     */
    private Ray constructReflectedRay(GeoPoint gp, Vector n, Vector v, double vn) {
        if (vn == 0) return null;
        Vector r = v.subtract(n.scale(2 * vn));
        Ray ray = new Ray(gp.point, r);
        return ray.movingRayHead(n);
    }
}


