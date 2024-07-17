package miniProjectOne;

import geometries.*;
import lighting.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.*;
import scene.Scene;

import java.util.LinkedList;
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
        // line 3
        Triangle triangle1 = new Triangle(
                new Point(-100, 0, -60),
                new Point(0, 100, -60),
                new Point(-100, 100, -60));
        triangle1.setEmission(color).setMaterial(material);

        Triangle triangle2 = new Triangle(
                new Point(-100, 0, -63),
                new Point(0, 100, -63),
                new Point(0, 0, -63));
        triangle2.setEmission(color).setMaterial(material);

        Triangle triangle3 = new Triangle(
                new Point(100, 0, -60),
                new Point(0, 100, -60),
                new Point(0, 0, -60));
        triangle3.setEmission(color).setMaterial(material);

        Triangle triangle4 = new Triangle(
                new Point(100, 0, -63),
                new Point(0, 100, -63),
                new Point(100, 100, -63));
        triangle4.setEmission(color).setMaterial(material);

        Triangle triangle5 = new Triangle(
                new Point(100,0,-60),
                new Point(100,100,-60),
                new Point(200,100,-60));
        triangle5.setEmission(color).setMaterial(material);

        Triangle triangle6 = new Triangle(
                new Point(100,0,-63),
                new Point(200,0,-63),
                new Point(200,100,-63));
        triangle6.setEmission(color).setMaterial(material);

        Triangle triangle7 = new Triangle(
                new Point(-200,100,-60),
                new Point(-100,100,-60),
                new Point(-100,0,-60));
        triangle7.setEmission(color).setMaterial(material);

        Triangle triangle8 = new Triangle(
                new Point(-100,0,-63),
                new Point(-200,100,-63),
                new Point(-200,0,-63));
        triangle8.setEmission(color).setMaterial(material);

        // line 2
        Triangle triangle9 = new Triangle(
                new Point(-100, -100, -63),
                new Point(0, 0, -63),
                new Point(-100, 0, -63));
        triangle9.setEmission(color).setMaterial(material);

        Triangle triangle10 = new Triangle(
                new Point(-100, -100, -60),
                new Point(0, 0, -60),
                new Point(0, -100, -60));
        triangle10.setEmission(color).setMaterial(material);

        Triangle triangle11 = new Triangle(
                new Point(100, -100, -63),
                new Point(0, 0, -63),
                new Point(0, -100, -63));
        triangle11.setEmission(color).setMaterial(material);

        Triangle triangle12 = new Triangle(
                new Point(100, -100, -60),
                new Point(0, 0, -60),
                new Point(100, 0, -60));
        triangle12.setEmission(color).setMaterial(material);

        Triangle triangle13 = new Triangle(
                new Point(100,-100,-63),
                new Point(100,0,-63),
                new Point(200,0,-63));
        triangle13.setEmission(color).setMaterial(material);

        Triangle triangle14 = new Triangle(
                new Point(100,-100,-60),
                new Point(200,-100,-60),
                new Point(200,0,-60));
        triangle14.setEmission(color).setMaterial(material);

        Triangle triangle15 = new Triangle(
                new Point(-200,0,-63),
                new Point(-100,0,-63),
                new Point(-100,-100,-63));
        triangle15.setEmission(color).setMaterial(material);

        Triangle triangle16 = new Triangle(
                new Point(-100,-100,-60),
                new Point(-200,0,-60),
                new Point(-200,-100,-60));
        triangle16.setEmission(color).setMaterial(material);

        // line 1
        Triangle triangle17 = new Triangle(
                new Point(-100, -200, -60),
                new Point(0, -100, -60),
                new Point(-100, -100, -60));
        triangle17.setEmission(color).setMaterial(material);

        Triangle triangle18 = new Triangle(
                new Point(-100, -200, -63),
                new Point(0, -100, -63),
                new Point(0, -200, -63));
        triangle18.setEmission(color).setMaterial(material);

        Triangle triangle19 = new Triangle(
                new Point(100, -200, -60),
                new Point(0, -100, -60),
                new Point(0, -200, -60));
        triangle19.setEmission(color).setMaterial(material);

        Triangle triangle20 = new Triangle(
                new Point(100, -200, -63),
                new Point(0, -100, -63),
                new Point(100, -100, -63));
        triangle20.setEmission(color).setMaterial(material);

        Triangle triangle21 = new Triangle(
                new Point(100,-200,-60),
                new Point(100,-100,-60),
                new Point(200,-100,-60));
        triangle21.setEmission(color).setMaterial(material);

        Triangle triangle22 = new Triangle(
                new Point(100,-200,-63),
                new Point(200,-200,-63),
                new Point(200,-100,-63));
        triangle22.setEmission(color).setMaterial(material);

        Triangle triangle23 = new Triangle(
                new Point(-200,-100,-60),
                new Point(-100,-100,-60),
                new Point(-100,-200,-60));
        triangle23.setEmission(color).setMaterial(material);

        Triangle triangle24 = new Triangle(
                new Point(-100,-200,-63),
                new Point(-200,-100,-63),
                new Point(-200,-200,-63));
        triangle24.setEmission(color).setMaterial(material);

        // line 4
        Triangle triangle25 = new Triangle(
                new Point(-100, 100, -63),
                new Point(0, 200, -63),
                new Point(-100, 200, -63));
        triangle25.setEmission(color).setMaterial(material);

        Triangle triangle26 = new Triangle(
                new Point(-100, 100, -60),
                new Point(0, 200, -60),
                new Point(0, 100, -60));
        triangle26.setEmission(color).setMaterial(material);

        Triangle triangle27 = new Triangle(
                new Point(100, 100, -63),
                new Point(0, 200, -63),
                new Point(0, 100, -63));
        triangle27.setEmission(color).setMaterial(material);

        Triangle triangle28 = new Triangle(
                new Point(100, 100, -60),
                new Point(0, 200, -60),
                new Point(100, 200, -60));
        triangle28.setEmission(color).setMaterial(material);

        Triangle triangle29 = new Triangle(
                new Point(100,100,-63),
                new Point(100,200,-63),
                new Point(200,200,-63));
        triangle29.setEmission(color).setMaterial(material);

        Triangle triangle30 = new Triangle(
                new Point(100,100,-60),
                new Point(200,100,-60),
                new Point(200,200,-60));
        triangle30.setEmission(color).setMaterial(material);

        Triangle triangle31 = new Triangle(
                new Point(-200,200,-63),
                new Point(-100,200,-63),
                new Point(-100,100,-63));
        triangle31.setEmission(color).setMaterial(material);

        Triangle triangle32 = new Triangle(
                new Point(-100,100,-60),
                new Point(-200,200,-60),
                new Point(-200,100,-60));
        triangle32.setEmission(color).setMaterial(material);

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
                // line 3
                triangle1, triangle2, triangle3, triangle4, triangle5, triangle6, triangle7, triangle8,
                // line 2
                triangle9, triangle10, triangle11, triangle12, triangle13, triangle14, triangle15, triangle16,
                // line 1
                triangle17, triangle18, triangle19, triangle20, triangle21, triangle22, triangle23, triangle24,
                // line 4
                triangle25, triangle26, triangle27, triangle28, triangle29, triangle30, triangle31, triangle32
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
