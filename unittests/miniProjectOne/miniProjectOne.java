package miniProjectOne;

import geometries.*;
import lighting.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.*;
import scene.Scene;

import java.util.List;

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
    private final Color color = new Color(20,20,20);
    private final Material material = new Material()
            .setkD(0.3) // כמה מתפזר
            .setkS(0.1) // כמה חלק/מחוספס
            .setnShininess(80); // כמה ברק
            //.setkR(0.2); // כמה משתקף

    @Test
    public void WallOfTriangles() {
        // geometies
        Plane plane = new Plane(
                new Point(-100, 0, -63),
                new Point(0, 100, -63),
                new Point(-100, 100, -63));
        plane.setEmission(color).setMaterial(material);

        Triangle triangle1 = new Triangle(
                new Point(-100, 0, -53),
                new Point(0, 100, -53),
                new Point(0, 0, -53));
        triangle1.setEmission(new Color(65,0,255)).setMaterial(material);

        Triangle triangle2 = new Triangle(
                new Point(100, 0, -53),
                new Point(0, 100, -53),
                new Point(0, 0, -53));
        triangle2.setEmission(new Color(BLUE)).setMaterial(material);

        Triangle triangle3 = new Triangle(
                new Point(100, -100, -53),
                new Point(0, 0, -53),
                new Point(100, 0, -53));
        triangle3.setEmission(new Color(0,40,220)).setMaterial(material);

        //Triangle triangle9 = new Triangle(
        //        new Point(-100, -100, -53),
        //        new Point(0, 0, -53),
        //        new Point(-100, 0, -53));
        //triangle9.setEmission(new Color(100,0,230)).setMaterial(material);

        Triangle triangle4_1 = new Triangle(
                new Point(0, 0, -53),
                new Point(-30, 0, -53),
                new Point(-30, -30, -53));
        triangle4_1.setEmission(new Color(100,0,230)).setMaterial(material);

        Triangle triangle4_2 = new Triangle(
                new Point(-35, 0, -53),
                new Point(-65, -30, -53),
                new Point(-35, -30, -53));
        triangle4_2.setEmission(new Color(100,0,230)).setMaterial(material);

        Triangle triangle4_3 = new Triangle(
                new Point(-35, 0, -53),
                new Point(-65, 0, -53),
                new Point(-65, -30, -53));
        triangle4_3.setEmission(new Color(100,0,230)).setMaterial(material);

        Triangle triangle4_4 = new Triangle(
                new Point(-70, 0, -53),
                new Point(-70, -30, -53),
                new Point(-100, -30, -53));
        triangle4_4.setEmission(new Color(100,0,230)).setMaterial(material);

        Triangle triangle4_5 = new Triangle(
                new Point(-70, 0, -53),
                new Point(-100, 0, -53),
                new Point(-100, -30, -53));
        triangle4_5.setEmission(new Color(100,0,230)).setMaterial(material);

        // lights
        SpotLight spotLight1 = (SpotLight) new SpotLight(
                new Color(255, 255, 255),
                new Point(150,0, -30),
                new Vector(0,-1,-1)
        )
                .setkL(0.001).setkQ(0.0002);

        SpotLight spotLight2 = (SpotLight) new SpotLight(
                new Color(255, 255, 255),
                new Point(-200,-100, -30),
                new Vector(0,1,-1)
        )
                .setkL(0.001).setkQ(0.0002);

        DirectionalLight directionalLight = new DirectionalLight(
                new Color(180, 180, 180),
                new Vector(0,-1,-1)
        );

        List<LightSource> lights = List.of(spotLight1, spotLight2, directionalLight);

        // build the scene
        scene.geometries.add(
                plane,
                triangle1, triangle2, triangle3,
                triangle4_1, triangle4_2, triangle4_3, triangle4_4, triangle4_5
        );

        scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.17))
                .setBackground(new Color(BLACK))
                .setLights(lights);

        camera
                .setImageWriter(new ImageWriter("miniProjectOne", 1000, 1000))
                .build()
                .renderImage()
                .writeToImage();
    }
}
