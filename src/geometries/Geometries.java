package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * a class for the collection of geometric bodies in the scene
 */
public class Geometries extends Intersectable{
    private final List<Intersectable> listGeometries =  new LinkedList<Intersectable>();

    public Geometries() { }

    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    /**
     *The add function adds a list of the geometric entities to the field listGeometries
     * @param geometries List of all geometric bodies
     */
    public void add(Intersectable... geometries) {
        listGeometries.addAll(Arrays.asList(geometries));
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray){
        List<GeoPoint> listOfPoints=new LinkedList<GeoPoint>();
        for(Intersectable geometry:listGeometries)
        {
            var result=geometry.findGeoIntersectionsHelper( ray);
            if(result!=null)
            {
                listOfPoints.addAll(result);
            }

        }
        if(listOfPoints.isEmpty())
        {
            return null;
        }
        return listOfPoints;
    }
}
