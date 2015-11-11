package snappi.loader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FileLoader 
{
    public static BufferedImage load(String path) {
      File file = new File(path);
      return FileLoader.load(file);
    }
    
    public static BufferedImage load(File file) {
      try {
        return ImageIO.read(file);
      }
      catch (IOException e) {
        throw new IllegalArgumentException("An error occured while reading the file", e);
      }
    }
}
