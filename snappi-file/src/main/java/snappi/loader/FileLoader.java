package snappi.loader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import snappi.image.Image;

public class FileLoader 
{
    public static Image load(String path) {
      File file = new File(path);
      return FileLoader.load(file);
    }
    
    public static Image load(File file) {
      try {
        BufferedImage bufferedImage = ImageIO.read(file); 
        return new Image(bufferedImage);
      }
      catch (IOException e) {
        throw new IllegalArgumentException("An error occured while reading the file", e);
      }
    }
}
