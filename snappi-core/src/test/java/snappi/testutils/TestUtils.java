package snappi.testutils;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;

import snappi.image.Image;

public class TestUtils {
  /**
   * Does pretty much what {@link ResourceLoader#load(String)} does, but can't
   * do because of Maven circular dependencies.
   * @param fileName Path to the file as a resource.
   * @return The associated image.
   * @throws IllegalArgumentException If the image is not found or could not be read.
   */
  public static Image getImageFromResource(String filename) {
    ClassLoader classLoader = TestUtils.class.getClassLoader();
    InputStream stream = classLoader.getResourceAsStream(filename);
    if (stream == null) {
      throw new IllegalArgumentException("Image could not be found.");
    }
    
    try {
      BufferedImage image = ImageIO.read(stream); 
      return new Image(image);
    }
    catch(Exception e) {
      throw new IllegalArgumentException("An error occured while reading the image.");
    }
  }
  
  /**
   * Does pretty much what {@link ResourceLoader#load(String)} does, but can't
   * do because of Maven circular dependencies.
   * @param testSetElement An element from the test set.
   * @return The associated image.
   * @throws IllegalArgumentException If the image is not found or could not be read.
   */
  public static Image getImageFromResource(TestSet testSetElement) {
    return getImageFromResource(testSetElement.getFilename());
  }
}
