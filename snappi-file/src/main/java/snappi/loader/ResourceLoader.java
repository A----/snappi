package snappi.loader;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

public class ResourceLoader 
{
    public static BufferedImage load(String path) {
      ClassLoader classLoader = ResourceLoader.class.getClassLoader();
      URL resource = classLoader.getResource(path);
      if (resource == null) {
        throw new IllegalArgumentException("Image could not be found.");
      }
      
      try {
        InputStream stream = classLoader.getResourceAsStream(path);
        BufferedImage image = ImageIO.read(stream); 
        return image;
      }
      catch(Exception e) {
        throw new IllegalArgumentException("An error occured while reading the image.");
      }
    }
}
