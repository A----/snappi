package snappi.image.difference;

import static org.junit.Assert.assertEquals;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import javax.imageio.ImageIO;
import org.junit.Test;

import snappi.image.difference.ImageDifferenceOperator;

public class ImageDifferenceOperatorTest {

  public BufferedImage getImageFromResource(String fileName) {
    ClassLoader classLoader = this.getClass().getClassLoader();
    URL resource = classLoader.getResource(fileName);
    if (resource == null) {
      throw new IllegalArgumentException("Image could not be found.");
    }

    try {
      File file = new File(classLoader.getResource(fileName).getFile());
      return ImageIO.read(file);
    }
    catch(Exception e) {
      throw new IllegalArgumentException("An error occured while reading the image.");
    }
  }

  @Test
  public void noDifferenceWithItself() {
    BufferedImage lennaA = this.getImageFromResource("lenna.png");
    BufferedImage lennaB = this.getImageFromResource("lenna.png");

    ImageDifferenceOperator operator = new ImageDifferenceOperator();
    long result = operator.compute(lennaA, lennaB);

    assertEquals("There should not be any differences between an image and itself.", 0L, result);
  }

  @Test
  public void someDifferencesExpected() {
    BufferedImage lennaA = this.getImageFromResource("lenna.png");
    BufferedImage lennaB = this.getImageFromResource("lenna-modified.png");

    ImageDifferenceOperator operator = new ImageDifferenceOperator();
    long result = operator.compute(lennaA, lennaB);

    assertEquals("There are differences between those images.", 498L, result);
  }

  @Test
  public void noDifferenceWithModifiedAlpha() {
    BufferedImage lennaA = this.getImageFromResource("lenna-alpha.png");
    BufferedImage lennaB = this.getImageFromResource("lenna-modified-alpha.png");

    ImageDifferenceOperator operator = new ImageDifferenceOperator();
    long result = operator.compute(lennaA, lennaB);

    assertEquals("There should not be any differences when modifications are not fully opaque.", 0L, result);
  }

  @Test(expected = IllegalArgumentException.class)
  public void imagesOfDifferentSizes() {
    BufferedImage lenna = this.getImageFromResource("lenna.png");
    BufferedImage redPixel = this.getImageFromResource("red-pixel.png");

    ImageDifferenceOperator operator = new ImageDifferenceOperator();
    operator.compute(lenna, redPixel);
  }
}
