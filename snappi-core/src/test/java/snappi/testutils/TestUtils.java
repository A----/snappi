package snappi.testutils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import snappi.image.Image;

public class TestUtils {
  /**
   * Return a {@link BufferedImage} from the resource bundled.
   * @param fileName Path to the file as a resource.
   * @return The associated image.
   * @throws IllegalArgumentException If the image is not found or could not be read.
   */
  public static BufferedImage getBufferedImage(String filename) {
    ClassLoader classLoader = TestUtils.class.getClassLoader();
    InputStream stream = classLoader.getResourceAsStream(filename);
    if (stream == null) {
      throw new IllegalArgumentException("Image could not be found.");
    }
    
    try {
      BufferedImage image = ImageIO.read(stream);
      return image;
    }
    catch(Exception e) {
      throw new IllegalArgumentException("An error occured while reading the image.");
    }
  }
  
  /**
   * Return a {@link BufferedImage} from the resource bundled.
   * @param testSetElement An element from the test set.
   * @return The associated image.
   * @throws IllegalArgumentException If the image is not found or could not be read.
   */
  public static BufferedImage getBufferedImage(TestSet testSetElement) {
    return getBufferedImage(testSetElement.getFilename());
  }
  
  /**
   * Does pretty much what {@link ResourceLoader#load(String)} does, but can't
   * do because of Maven circular dependencies.
   * @param fileName Path to the file as a resource.
   * @return The associated image.
   * @throws IllegalArgumentException If the image is not found or could not be read.
   */
  public static Image getImage(String filename) {
    BufferedImage image = getBufferedImage(filename);
    return new Image(image);
  }
  
  /**
   * Does pretty much what {@link ResourceLoader#load(String)} does, but can't
   * do because of Maven circular dependencies.
   * @param testSetElement An element from the test set.
   * @return The associated image.
   * @throws IllegalArgumentException If the image is not found or could not be read.
   */
  public static Image getImage(TestSet testSetElement) {
    return getImage(testSetElement.getFilename());
  }
  
  public static void write(Image image, String filename) {
    try {
      ImageIO.write(image.getImage(), "png", new File(filename));
    }
    catch (IOException e) {
      throw new IllegalArgumentException("Cannot write into " + filename, e);
    }
  }
}
