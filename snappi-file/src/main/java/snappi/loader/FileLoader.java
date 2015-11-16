package snappi.loader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import snappi.image.Image;

/**
 * Collection of methods to load images from files.
 * @see ResourceLoader
 */
public class FileLoader 
{
  /**
   * Loads an image from its path.
   * @param path Path to the file.
   * @return The loaded image.
   * @throws IllegalArgumentException If an error occurred or if the file does not exist.
   */
  public static Image load(String path) throws IllegalArgumentException {
    File file = new File(path);
    return FileLoader.load(file);
  }
  
  /**
   * Loads an image from a file.
   * @param file The file.
   * @return The loaded image.
   * @throws IllegalArgumentException If an error occurred or if the file does not exist.
   */
  public static Image load(File file) throws IllegalArgumentException {
    try {
      BufferedImage bufferedImage = ImageIO.read(file); 
      return new Image(bufferedImage);
    }
    catch (IOException e) {
      throw new IllegalArgumentException("An error occured while reading the file", e);
    }
  }
}
