package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 *  Unit tests for renderer.ImageWriter class
 */
class ImageWriterTest {

    @Test
    void testWritePixel() {
        ImageWriter imageWriter = new ImageWriter("My Image", 800, 500);
        for (int x = 0; x < 800; x++)
        {
            for(int y = 0; y < 500; y++)
            {
                if (x % 50 == 0 || y % 50 == 0)
                    imageWriter.writePixel(x, y, new Color(java.awt.Color.BLACK));
                else
                    imageWriter.writePixel(x, y, new Color(java.awt.Color.yellow));
            }
        }
        imageWriter.writeToImage();
    }
}