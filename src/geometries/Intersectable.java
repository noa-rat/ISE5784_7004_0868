package geometries;

import primitives.*;

import java.util.List;

/**
 * Implements the composite design pattern
 */
public interface Intersectable {
    public List<Point> findIntersections(Ray ray);
}
