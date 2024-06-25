package renderer;

import lighting.LightSource;
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
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        if (intersections == null) {
            return scene.background;
        }
        GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
        return calcColor(closestPoint,ray);
    }

    /**
     * Calculates the color of a pixel at a point
     * @param intersection - to calculate its color
     * @param ray - to calculate its color
     * @return the color of the point
     */
    private Color calcColor(GeoPoint  intersection,Ray ray) {
        return (scene.ambientLight.getIntensity())
                .add(intersection.geometry.getEmission())
                .add(calcLocalEffects(intersection,ray));
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

            if(nl*nv>0) {
                Color iL=lightSource.getIntensity(gp.point);
                 color =color.add(calcDiffusive(material,nl,iL))
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
}


