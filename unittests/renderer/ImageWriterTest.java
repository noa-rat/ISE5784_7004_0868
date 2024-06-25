package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

import javax.swing.*;

import static java.awt.Color.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for renderer.ImageWriter class
 */
class ImageWriterTest {

    @Test
    void testWritePixel() {
        ImageWriter imageWriter = new ImageWriter("My Image", 800, 500);
        for (int x = 0; x < imageWriter.getNx(); x++) {
            for (int y = 0; y < imageWriter.getNy(); y++) {
                if(x%50==0||y%50==0) {
                    imageWriter.writePixel(x, y, new Color(BLACK));
                }
                else
                    imageWriter.writePixel(x, y, new Color(YELLOW));
            }
        }


        imageWriter.writeToImage();
    }
}