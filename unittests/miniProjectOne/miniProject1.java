package miniProjectOne;

import geometries.*;
import lighting.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.*;
import scene.Scene;

import static java.awt.Color.*;

/**
 *
 */
class miniProjectOne {
    private final Scene scene  = new Scene("Test scene");
    private final Camera.Builder camera = Camera.getBuilder()
            .setRayTracer(new SimpleRayTracer(scene))
            .setLocation(Point.ZERO).setDirection(new Vector(0, 0, -1), new Vector(0,-1,0))
            .setVpDistance(100)
            .setVpSize(500, 500);

    @Test
    public void WallOfTriangles() {
        Triangle triangle1 = new Triangle(
                new Point(-100, 0, -100),
                new Point(0, 100, -100),
                new Point(-100, 100, -100));
        triangle1.setEmission(new Color(RED));

        Triangle triangle2 = new Triangle(
                new Point(-100, 0, -110),
                new Point(0, 100, -110),
                new Point(0, 0, -110));
        triangle2.setEmission(new Color(BLUE));

        Triangle triangle3 = new Triangle(
                new Point(100, 0, -105),
                new Point(0, 100, -105),
                new Point(0, 0, -105));
        triangle3.setEmission(new Color(YELLOW));

        Triangle triangle4 = new Triangle(
                new Point(100, 0, -110),
                new Point(0, 100, -110),
                new Point(100, 100, -110));
        triangle4.setEmission(new Color(BLACK));

//        Triangle triangle5 = new Triangle(
//                new Point(0,0,0),
//                new Point(0,1,0),
//                new Point(0,2,0));
//
//        Triangle triangle6 = new Triangle(
//                new Point(-2,0,0),
//                new Point(-2,1,0),
//                new Point(-2,2,0));
//
//        Triangle triangle7 = new Triangle(
//                new Point(2,0,0),
//                new Point(2,1,0),
//                new Point(2,2,0));
//
//        Triangle triangle8 = new Triangle(
//                new Point(0,0,0),
//                new Point(0,1,0),
//                new Point(0,2,0));
//
//        Triangle triangle9 = new Triangle(
//                new Point(-2,0,0),
//                new Point(-2,1,0),
//                new Point(-2,2,0));

        scene.geometries.add(triangle1, triangle2, triangle3, triangle4);
        scene.setAmbientLight(new AmbientLight(new Color(255, 191, 191), Double3.ONE))
                .setBackground(new Color(75, 127, 90));

        camera
                .setImageWriter(new ImageWriter("miniProjectOne", 1000, 1000))
                .build()
                .renderImage()
                .writeToImage();
    }
}
