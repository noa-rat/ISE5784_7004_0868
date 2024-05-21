package geometries;

import primitives.Point;
import primitives.Vector;

public interface Geometry extends Intersectable {
    /**
     *
     * @param point on the shape
     * @return vector normal to the shape
     */
    public Vector getNormal(Point point);
}
