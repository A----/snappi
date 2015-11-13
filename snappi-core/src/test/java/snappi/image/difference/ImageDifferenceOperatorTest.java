package snappi.image.difference;

import static org.junit.Assert.assertEquals;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import org.junit.Test;

import snappi.image.Image;

public class ImageDifferenceOperatorTest {

  public Image getImageFromResource(String fileName) {
    ClassLoader classLoader = this.getClass().getClassLoader();
    URL resource = classLoader.getResource(fileName);
    if (resource == null) {
      throw new IllegalArgumentException("Image could not be found.");
    }
    
    try {
      InputStream stream = classLoader.getResourceAsStream(fileName);
      BufferedImage image = ImageIO.read(stream); 
      return new Image(image);
    }
    catch(Exception e) {
      throw new IllegalArgumentException("An error occured while reading the image.");
    }
  }
  
  @Test
  public void noDifferenceWithItself() {
    Image lennaA = this.getImageFromResource("lenna.png");
    Image lennaB = this.getImageFromResource("lenna.png");

    ImageDifferenceOperator operator = new ImageDifferenceOperator();
    Result result = operator.compute(lennaA, lennaB);

    assertEquals("There should not be any differences between an image and itself.", 0L, result.getCount());
  }

  @Test
  public void someDifferencesExpected() {
    Image lennaA = this.getImageFromResource("lenna.png");
    Image lennaB = this.getImageFromResource("lenna-modified.png");

    ImageDifferenceOperator operator = new ImageDifferenceOperator();
    Result result = operator.compute(lennaA, lennaB);

    assertEquals("There are differences between those images.", 498L, result.getCount());
  }

  @Test
  public void noDifferenceWithModifiedAlpha() {
    Image lennaA = this.getImageFromResource("lenna-alpha.png");
    Image lennaB = this.getImageFromResource("lenna-modified-alpha.png");

    ImageDifferenceOperator operator = new ImageDifferenceOperator();
    Result result = operator.compute(lennaA, lennaB);

    assertEquals("There should not be any differences when modifications are not fully opaque.", 0L, result.getCount());
  }

  @Test(expected = IllegalArgumentException.class)
  public void imagesOfDifferentSizes() {
    Image lenna = this.getImageFromResource("lenna.png");
    Image redPixel = this.getImageFromResource("red-pixel.png");

    ImageDifferenceOperator operator = new ImageDifferenceOperator();
    operator.compute(lenna, redPixel);
  }
}
