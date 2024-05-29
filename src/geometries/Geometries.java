package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * a class for the collection of geometric bodies in the scene
 */
public class Geometries implements Intersectable{
    private final List<Intersectable> listGeometries =  new LinkedList<Intersectable>();

    public Geometries() { }

    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    /**
     *
     * @param geometries
     */
    public void add(Intersectable... geometries) {
        listGeometries.addAll(Arrays.asList(geometries));
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
