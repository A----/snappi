package snappi.loader;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import snappi.image.WebImage;

/**
 * Collection of methods to load an image from a {@link WebDriver} or {@link WebElement}.
 */
public class SeleniumLoader 
{
  /**
   * Load an image from a {@link WebDriver} or {@link WebElement}.
   * @param driverOrElement The {@link WebDriver} or {@link WebElement}.
   * @return The loaded image.
   * @throws IllegalArgumentException If an error occured while reading.
   */
  public static WebImage load(TakesScreenshot driverOrElement) throws IllegalArgumentException {
    byte[] bytes = driverOrElement.getScreenshotAs(OutputType.BYTES);
    
    try {
      BufferedImage image = ImageIO.read(new ByteArrayInputStream(bytes));
      
      WebImage webImage;
      if(driverOrElement instanceof WebElement) {
        webImage = new WebImage(image, (WebElement) driverOrElement);
      }
      else {
        webImage = new WebImage(image);
      }
      
      if (driverOrElement instanceof SearchContext) {
        webImage.setSearchContext((SearchContext) driverOrElement);
      }
      
      return webImage;
    }
    catch(IOException e) {
      throw new IllegalArgumentException("An error occured while reading the image.", e);
    }
  }
}
