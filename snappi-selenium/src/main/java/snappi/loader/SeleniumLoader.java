package snappi.loader;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import snappi.image.Image;

/**
 * Hello world!
 *
 */
public class SeleniumLoader 
{
    public static Image load(TakesScreenshot webDriver) {
      byte[] bytes = webDriver.getScreenshotAs(OutputType.BYTES);
      
      try {
        BufferedImage image = ImageIO.read(new ByteArrayInputStream(bytes));
        return new Image(image);
      }
      catch(IOException e) {
        throw new IllegalArgumentException("An error occured while reading the image.");
      }
    }
}
