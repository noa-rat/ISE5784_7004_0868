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

    private Color calcColor(GeoPoint gp, Ray ray) {
        Double3 initialK = new Double3(MIN_CALC_COLOR_K);
        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, initialK)
                .add(scene.ambientLight.getIntensity());}


    /**
     * Calculates the color of a pixel at a point
     * @param gp - to calculate its color
     * @param ray - to calculate its color
     * @return the color of the point
     */
    private Color calcColor(GeoPoint gp,Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(gp,ray).add(gp.geometry.getEmission());
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
    private Color calcLocalEffects(GeoPoint gp,Ray ray){
        Vector n=gp.geometry.getNormal(gp.point);
        Vector v=ray.getDirection();
        double nv=Util.alignZero(n.dotProduct(v));
        Color color=Color.BLACK;
        if(nv==0)
            return color;
        Material material=gp.geometry.getMaterial();
        for(LightSource lightSource :scene.lights)
        {
            Vector l=lightSource.getL(gp.point);
            double nl=Util.alignZero(n.dotProduct(l));

            if(nl*nv>0 && unshaded(gp,lightSource,l,n,nl)){
                Color iL = lightSource.getIntensity(gp.point);
                 color = color.add(calcDiffusive(material,nl,iL))
                                .add(calcSpecular(material,n,l,nl,v,iL));
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
     *Helper function for  calculate Specular
     * @param material of the gp
     * @param n normal
     * @param l the ray from lightsource
     * @param nl dot product between the normal and the ray
     * @param v the ray from camera
     * @return Double3 to the level of influence of Specular
     */
    private Color calcSpecular (Material material, Vector n, Vector l, double nl,Vector v,Color iL){
        Vector r=l.add(n.scale(-2*nl));
        double vr=-(Util.alignZero(v.dotProduct(r)));
        if(vr>0)
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
    private boolean unshaded(GeoPoint gp,LightSource light, Vector l, Vector n, double nl) {
        //if (gp.geometry.getMaterial().kT == Double3.ZERO)
           // return true;
        Vector lightDirection = l.scale(-1);
        Vector deltaVector = n.scale(nl<0 ? 0.1 : -0.1);
        Point p = gp.point.add(deltaVector);
        Ray ray = new Ray(p,lightDirection);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        if(intersections == null) return true;
        for (GeoPoint geoPoint : intersections) {
            if (light.getDistance(p) > p.distance(geoPoint.point))
                    return false;
            }
        return true;
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
        return calcGlobalEffect(constructRefractedRay(gp, ray, n, v, vn), material.kT, level, k)//שקיפות
                .add(calcGlobalEffect(constructReflectedRay(gp, ray, n, v, vn), material.kR, level, k));//השתקפות

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
        return (gp == null ? scene.background : calcColor(gp,ray, level - 1, kkx).scale(kx));
    }

    /**
     * Builds a transparency ray קרן שקיפות
     * @param gp - the cutting point
     * @param ray - The cutting ray
     * @return transparency ray
     */
    private Ray constructRefractedRay(GeoPoint gp, Ray ray, Vector n, Vector v, double vn) {
        return  ray.movingRayHead(n);
    }

    /**
     * Builds a reflection ray קרן השתקפות
     * @param gp - the cutting point
     * @param ray - The cutting ray
     * @return reflection ray
     */
    private Ray constructReflectedRay(GeoPoint gp, Ray ray, Vector n, Vector v, double vn) {
        Vector r = v.add(n.scale(-2 * vn));
        Vector deltaVector = n.scale(vn < 0 ? 0.1 : -0.1);
        Point p = gp.point.add(deltaVector);
        return new Ray(p, r);
    }

}


