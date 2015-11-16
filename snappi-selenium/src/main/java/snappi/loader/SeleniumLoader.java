package snappi.loader;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.TakesScreenshot;

import snappi.image.WebImage;


public class SeleniumLoader 
{
    public static WebImage load(TakesScreenshot driverOrElement) {
      byte[] bytes = driverOrElement.getScreenshotAs(OutputType.BYTES);
      
      try {
        BufferedImage image = ImageIO.read(new ByteArrayInputStream(bytes));
        
        WebImage webImage = new WebImage(image);
        if (driverOrElement instanceof SearchContext) {
          webImage.setSearchContext((SearchContext) driverOrElement);
        }
        
        return webImage;
      }
      catch(IOException e) {
        throw new IllegalArgumentException("An error occured while reading the image.");
      }
    }
}
