package snappi.asserts;

import java.awt.image.BufferedImage;

import snappi.image.difference.ImageDifferenceOperator;

public class Assertions {
  public static void assertSame(BufferedImage expected, BufferedImage actual) {
    assertSame("Actual image is different from the expected one.", expected, actual);
  }
  
  public static void assertSame(String message, BufferedImage expected, BufferedImage actual) {
    ImageDifferenceOperator operator = new ImageDifferenceOperator();
    long result = operator.compute(expected, actual);
    
    if(result > 0) {
      throw new AssertionError(message);
    }
  }
}
