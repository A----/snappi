package snappi.loader;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import snappi.image.Image;

/**
 * Collection of methods to load images from resources.
 * @see FileLoader
 */
public class ResourceLoader 
{
  /**
   * Loads an image from its path.
   * @param path Path to the resource.
   * @return The loaded image.
   * @throws IllegalArgumentException If an error occurred or if the resource does not exist.
   */
  public static Image load(String path) throws IllegalArgumentException {
    ClassLoader classLoader = ResourceLoader.class.getClassLoader();
    URL resource = classLoader.getResource(path);
    if (resource == null) {
      throw new IllegalArgumentException("Image could not be found.");
    }
    
    try {
      InputStream stream = classLoader.getResourceAsStream(path);
      BufferedImage image = ImageIO.read(stream); 
      return new Image(image);
    }
    catch(IOException e) {
      throw new IllegalArgumentException("An error occured while reading the image.", e);
    }
  }
}
