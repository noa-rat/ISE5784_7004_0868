package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;

public  abstract class  Geometry extends Intersectable {

    protected Color emission= Color.BLACK;
    private Material material=new Material();
    /** return normal to geometry
     * @param point on the shape
     * @return vector normal to the shape
     */

    public  abstract Vector getNormal(Point point);

    /**
     * return the field emmision
     * @return emission
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * return the field material
     * @return material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Enters a value in the field emmision
     * @param emission  color to put in emmision
     * @return Geometry
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;

        return this;
    }

    /**
     *
     * Enters a value in the field material
     * @param material to put in material
     * @return Geometry
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }
}

