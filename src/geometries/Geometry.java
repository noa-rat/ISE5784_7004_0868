package geometries;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public  abstract class  Geometry extends Intersectable {

    protected Color emission= Color.BLACK;
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
     * Enters a value in the field emmision
     * @param emission  color to put in emmision
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;

        return this;
    }

}

