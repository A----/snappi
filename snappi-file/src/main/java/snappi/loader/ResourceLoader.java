package snappi.loader;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import snappi.image.Image;

public class ResourceLoader 
{
    public static Image load(String path) {
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
        throw new IllegalArgumentException("An error occured while reading the image.");
      }
    }
}
