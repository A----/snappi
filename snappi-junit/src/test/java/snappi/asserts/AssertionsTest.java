package snappi.asserts;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import org.junit.Test;

public class AssertionsTest {

  public BufferedImage getImageFromResource(String fileName) {
    ClassLoader classLoader = this.getClass().getClassLoader();
    URL resource = classLoader.getResource(fileName);
    if (resource == null) {
      throw new IllegalArgumentException("Image could not be found.");
    }
    
    try {
      InputStream stream = classLoader.getResourceAsStream(fileName);
      BufferedImage image = ImageIO.read(stream); 
      return image;
    }
    catch(Exception e) {
      throw new IllegalArgumentException("An error occured while reading the image.");
    }
  }

  
  @Test
  public void noThrowWithSameImage() {
    BufferedImage lennaA = this.getImageFromResource("lenna.png");
    BufferedImage lennaB = this.getImageFromResource("lenna.png");
    
    Assertions.assertSame(lennaA, lennaB);
  }

  
  @Test(expected = AssertionError.class)
  public void throwWithDifferentImages() {
    BufferedImage lennaA = this.getImageFromResource("lenna.png");
    BufferedImage lennaB = this.getImageFromResource("lenna-modified.png");
    
    Assertions.assertSame(lennaA, lennaB);
  }
}
