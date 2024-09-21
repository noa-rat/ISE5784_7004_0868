package renderer;


import geometries.Intersectable;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.*;
import geometries.Intersectable.GeoPoint;
import scene.Scene;


import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


/**
 * Integration testing department between constructRay to findIntersections
 */
class IntegrationTest {
    /**
     * help function to find all the intersection points
     * @param geometry - the list of geometry
     * @param camera - to cast ray
     * @return the number of the intersection points of the rays with the geometries
     */
    private int AllIntersectionsGeometry(Intersectable geometry,Camera camera, ImageWriter imageWriter) {
    List<GeoPoint>result=new LinkedList<GeoPoint>();
    for (int i = 0; i < imageWriter.getNx() ;i++) {
        for (int j = 0; j < imageWriter.getNy() ; j++) {
            List<Ray> rays = camera.constructRay(imageWriter.getNx(), imageWriter.getNy(), j, i, 1);
            for (Ray ray : rays) {
                var list = geometry.findGeoIntersections(ray);
                if (list != null) {
                    result.addAll(list);
                }
            }
        }
    }
    if(result.isEmpty())
    {
        return 0;
    }
    return result.size();
}
    /**
     * The function casts rays through the 3x3 pixel wiew plane and calculates how many cuts
     * there are with the sphere
     */
    @Test
    public void testSphere(){
        ImageWriter imageWriter = new ImageWriter("My Image", 3, 3);
        Camera camera= Camera.getBuilder()
            .setDirection(new Vector(0,0,-1),new Vector(0,1,0))
            .setVpDistance(1)
            .setLocation(Point.ZERO)
            .setVpSize(3,3)
            .setImageWriter(imageWriter)
            .setRayTracer(new SimpleRayTracer(new Scene("scene")))
            .build();

    Sphere sphere=new Sphere(1,new Point(0,0,-3));

        assertEquals(2,AllIntersectionsGeometry(sphere,camera, imageWriter),
                "Error: There should be 2 intersection points");
    ///////////////////////////////////////////////////////////////////////////////////////

        camera= Camera.getBuilder()
                .setDirection(new Vector(0,0,-1),new Vector(0,1,0))
                .setVpDistance(1)
                .setLocation(new Point(0,0,0.5))
                .setVpSize(3,3)
                .setImageWriter(imageWriter)
                .setRayTracer(new SimpleRayTracer(new Scene("scene")))
                .build();

        sphere=new Sphere(2.5,new Point(0,0,-2.5));

        assertEquals(18,AllIntersectionsGeometry(sphere,camera, imageWriter),
                "Error: There should be 18 intersection points");
        ///////////////////////////////////////////////////////////////////////////////////
         camera= Camera.getBuilder()
                .setDirection(new Vector(0,0,-1),new Vector(0,1,0))
                .setVpDistance(1)
                .setLocation(new Point(0,0,0.5))
                .setVpSize(3,3)
                 .setImageWriter(new ImageWriter("image",3,3))
                 .setRayTracer(new SimpleRayTracer(new Scene("scene")))
                .build();
         sphere=new Sphere(2,new Point(0,0,-2));

        assertEquals(10,AllIntersectionsGeometry(sphere,camera, imageWriter),
                "Error: There should be 10 intersection points");
        ///////////////////////////////////////////////////////////////////////////////////
         camera= Camera.getBuilder()
                .setDirection(new Vector(0,0,-1),new Vector(0,1,0))
                .setVpDistance(1)
                .setLocation(Point.ZERO)
                .setVpSize(3,3)
                 .setImageWriter(imageWriter)
                 .setRayTracer(new SimpleRayTracer(new Scene("scene")))
                .build();

        sphere=new Sphere(4,new Point(0,0,-0.5));

        assertEquals(9,AllIntersectionsGeometry(sphere,camera, imageWriter),
                "Error: There should be 9 intersection points");

    ///////////////////////////////////////////////////////////////////////////////////////////
         sphere=new Sphere(0.5,new Point(0,0,1));

        assertEquals(0,AllIntersectionsGeometry(sphere,camera, imageWriter),
                "Error: There should be 0 intersection points");



}

    /**
     * The function casts rays through the 3x3 pixel view plane and calculates how many cuts
     * there are with the triangle
     */
    @Test
    public void testTriangle(){
        ImageWriter imageWriter = new ImageWriter("My Image", 3, 3);
         Camera camera= Camera.getBuilder()
                .setDirection(new Vector(0,0,-1),new Vector(0,1,0))
                .setVpDistance(1)
                .setLocation(Point.ZERO)
                .setVpSize(3,3)
                 .setImageWriter(imageWriter)
                 .setRayTracer(new SimpleRayTracer(new Scene("scene")))
                .build();

        Triangle triangle=new Triangle(new Point(0,1,-2),
                new Point(-1,-1,-2),
                new Point(1,-1,-2));


        assertEquals(1,AllIntersectionsGeometry(triangle,camera, imageWriter),
                "Error: There should be 1 intersection points");
        /////////////////////////////////////////////////////////////////////////////////
         triangle=new Triangle(new Point(0,20,-2),
                new Point(-1,-1,-2),
                new Point(1,-1,-2));

        assertEquals(2,AllIntersectionsGeometry(triangle,camera, imageWriter),
                "Error: There should be 2 intersection points");


}

    /**
     * The function casts rays through the 3x3 pixel wiew plane and calculates how many cuts
     * there are with the plane
     */
    @Test
    public void testPlane(){
        ImageWriter imageWriter = new ImageWriter("My Image", 3, 3);
        Camera camera= Camera.getBuilder()
                .setDirection(new Vector(0,0,-1),new Vector(0,1,0))
                .setVpDistance(1)
                .setLocation(Point.ZERO)
                .setVpSize(3,3)
                .setImageWriter(imageWriter)
                .setRayTracer(new SimpleRayTracer(new Scene("scene")))
                .build();

        Plane plane=new Plane(new Point(0,0,-2),new Vector(0,0,-1));

        assertEquals(9,AllIntersectionsGeometry(plane,camera, imageWriter),
                "Error: There should be 9 intersection points");
        /////////////////////////////////////////////////////////////////////////////////

         plane=new Plane(new Point(0,0,-2),new Vector(0,0.2,-1));

         assertEquals(9,AllIntersectionsGeometry(plane,camera, imageWriter),
                "Error: There should be 9 intersection points");
        ///////////////////////////////////////////////////////////////////////////////
        plane=new Plane(new Point(0,0,-2),new Vector(0,2,-1));

        assertEquals(6,AllIntersectionsGeometry(plane,camera, imageWriter),
                "Error: There should be 6 intersection points");
    }
}
