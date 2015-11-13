package snappi.asserts;

import snappi.image.Image;
import snappi.image.difference.ImageDifferenceOperator;
import snappi.image.difference.Result;

public class Assertions {
  static final String ASSERT_SAME_MESSAGE = "Actual image is different from the expected one.";
  static final String ASSERT_SIMILAR_MESSAGE = "Actual image differs too much from the expected one.";
  
  public static void assertSame(Image expected, Image actual) {
    assertSame(ASSERT_SAME_MESSAGE, expected, actual);
  }
  
  public static void assertSame(String message, Image expected, Image actual) {
    assertSimilar(message, expected, actual, 0);
  }

  public static void assertSimilar(Image expected, Image actual, int toleranceInPixels) {
    assertSimilar(ASSERT_SIMILAR_MESSAGE, expected, actual, toleranceInPixels);
  }
  
  public static void assertSimilar(String message, Image expected, Image actual, int toleranceInPixels) {
    assertSimilar(message, expected, actual, (long) toleranceInPixels);
  }

  public static void assertSimilar(Image expected, Image actual, long toleranceInPixels) {
    assertSimilar(ASSERT_SIMILAR_MESSAGE, expected, actual, toleranceInPixels);
  }
  
  public static void assertSimilar(String message, Image expected, Image actual, long toleranceInPixels) {
    ImageDifferenceOperator operator = new ImageDifferenceOperator();
    Result result = operator.compute(expected, actual);
    
    if(result.getCount() > toleranceInPixels) {
      throw new AssertionError(message + " [" + result + "]");
    }
  }
  
  public static void assertSimilar(Image expected, Image actual, double toleranceInPercentage) {
    assertSimilar(ASSERT_SIMILAR_MESSAGE, expected, actual, toleranceInPercentage);
  }
  
  public static void assertSimilar(String message, Image expected, Image actual, double toleranceInPercentage) {
    ImageDifferenceOperator operator = new ImageDifferenceOperator();
    Result result = operator.compute(expected, actual);
    
    if(result.getPercentage() > (toleranceInPercentage / 100.0)) {
      throw new AssertionError(message + " [" + result + "]");
    }
  }
}
