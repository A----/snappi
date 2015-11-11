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
  public void assertSameNoThrowWithSameImage() {
    BufferedImage lennaA = this.getImageFromResource("lenna.png");
    BufferedImage lennaB = this.getImageFromResource("lenna.png");
    
    Assertions.assertSame(lennaA, lennaB);
    Assertions.assertSame("reason", lennaA, lennaB);
  }

  
  @Test(expected = AssertionError.class)
  public void assertSameThrowWithDifferentImages() {
    BufferedImage lennaA = this.getImageFromResource("lenna.png");
    BufferedImage lennaB = this.getImageFromResource("lenna-modified.png");
    
    Assertions.assertSame(lennaA, lennaB);
  }

  
  @Test
  public void assertSimilarNoThrowHighEnoughTolerance() {
    BufferedImage lennaA = this.getImageFromResource("lenna.png");
    BufferedImage lennaB = this.getImageFromResource("lenna-modified.png");
    
    Assertions.assertSimilar(lennaA, lennaB, 498);
    Assertions.assertSimilar(lennaA, lennaB, 498L);
    Assertions.assertSimilar(lennaA, lennaB, 0.19);
  }

  
  @Test(expected = AssertionError.class)
  public void assertSimilarThrowLowToleranceInt() {
    BufferedImage lennaA = this.getImageFromResource("lenna.png");
    BufferedImage lennaB = this.getImageFromResource("lenna-modified.png");

    Assertions.assertSimilar(lennaA, lennaB, 497);
  }

  
  @Test(expected = AssertionError.class)
  public void assertSimilarThrowLowToleranceLong() {
    BufferedImage lennaA = this.getImageFromResource("lenna.png");
    BufferedImage lennaB = this.getImageFromResource("lenna-modified.png");

    Assertions.assertSimilar(lennaA, lennaB, 497L);
  }

  
  @Test(expected = AssertionError.class)
  public void assertSimilarThrowLowToleranceDouble() {
    BufferedImage lennaA = this.getImageFromResource("lenna.png");
    BufferedImage lennaB = this.getImageFromResource("lenna-modified.png");

    Assertions.assertSimilar(lennaA, lennaB, 0.18);
  }
}
