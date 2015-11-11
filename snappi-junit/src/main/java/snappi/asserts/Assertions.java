package snappi.asserts;

import java.awt.image.BufferedImage;

import snappi.image.difference.ImageDifferenceOperator;
import snappi.image.difference.Result;

public class Assertions {
  public static void assertSame(BufferedImage expected, BufferedImage actual) {
    assertSame("Actual image is different from the expected one.", expected, actual);
  }
  
  public static void assertSame(String message, BufferedImage expected, BufferedImage actual) {
    assertSimilar(message, expected, actual, 0);
  }

  public static void assertSimilar(BufferedImage expected, BufferedImage actual, int toleranceInPixels) {
    assertSimilar("Actual image differs to much from the expected one", expected, actual, toleranceInPixels);
  }
  
  public static void assertSimilar(String message, BufferedImage expected, BufferedImage actual, int toleranceInPixels) {
    assertSimilar(message, expected, actual, (long) toleranceInPixels);
  }

  public static void assertSimilar(BufferedImage expected, BufferedImage actual, long toleranceInPixels) {
    assertSimilar("Actual image differs to much from the expected one", expected, actual, toleranceInPixels);
  }
  
  public static void assertSimilar(String message, BufferedImage expected, BufferedImage actual, long toleranceInPixels) {
    ImageDifferenceOperator operator = new ImageDifferenceOperator();
    Result result = operator.compute(expected, actual);
    
    if(result.getCount() > toleranceInPixels) {
      throw new AssertionError(message + " [" + result + "]");
    }
  }
  
  public static void assertSimilar(BufferedImage expected, BufferedImage actual, double toleranceInPercentage) {
    assertSimilar("Actual image differs to much from the expected one", expected, actual, toleranceInPercentage);
  }
  
  public static void assertSimilar(String message, BufferedImage expected, BufferedImage actual, double toleranceInPercentage) {
    ImageDifferenceOperator operator = new ImageDifferenceOperator();
    Result result = operator.compute(expected, actual);
    
    if(result.getPercentage() > (toleranceInPercentage / 100.0)) {
      throw new AssertionError(message + " [" + result + "]");
    }
  }
}
