package snappi.asserts;

import snappi.image.Image;
import snappi.image.difference.ImageDifferenceOperator;
import snappi.image.difference.Result;

/**
 * Assertions on image similarity.
 */
public class Assertions {
  /**
   * Default message when images should have been identical.
   */
  private static final String ASSERT_SAME_MESSAGE = "Actual image is different from the expected one.";
  /**
   * Default message when images are too different from one another.
   */
  private static final String ASSERT_SIMILAR_MESSAGE = "Actual image differs too much from the expected one.";
  
  /**
   * Image should be identical.
   * @param expected Expected image.
   * @param actual Actual image.
   * @throws AssertionError When images are different.
   */
  public static void assertSame(Image expected, Image actual) throws AssertionError {
    assertSame(ASSERT_SAME_MESSAGE, expected, actual);
  }
  
  /**
   * Image should be identical.
   * @param message Description of the test.
   * @param expected Expected image.
   * @param actual Actual image.
   * @throws AssertionError When images are different.
   */
  public static void assertSame(String message, Image expected, Image actual) throws AssertionError {
    assertSimilar(message, expected, actual, 0);
  }

  /**
   * Image should be similar
   * @param expected Expected image.
   * @param actual Actual image.
   * @param toleranceInPixels Number of pixels of difference.
   * @throws AssertionError When images are too different.
   */
  public static void assertSimilar(Image expected, Image actual, int toleranceInPixels) throws AssertionError {
    assertSimilar(ASSERT_SIMILAR_MESSAGE, expected, actual, toleranceInPixels);
  }

  /**
   * Image should be similar
   * @param message Description of the test.
   * @param expected Expected image.
   * @param actual Actual image.
   * @param toleranceInPixels Number of pixels of difference.
   * @throws AssertionError When images are too different.
   */
  public static void assertSimilar(String message, Image expected, Image actual, int toleranceInPixels) throws AssertionError {
    assertSimilar(message, expected, actual, (long) toleranceInPixels);
  }

  /**
   * Image should be similar
   * @param expected Expected image.
   * @param actual Actual image.
   * @param toleranceInPixels Number of pixels of difference.
   * @throws AssertionError When images are too different.
   */
  public static void assertSimilar(Image expected, Image actual, long toleranceInPixels) throws AssertionError {
    assertSimilar(ASSERT_SIMILAR_MESSAGE, expected, actual, toleranceInPixels);
  }

  /**
   * Image should be similar
   * @param message Description of the test.
   * @param expected Expected image.
   * @param actual Actual image.
   * @param toleranceInPixels Number of pixels of difference.
   * @throws AssertionError When images are too different.
   */
  public static void assertSimilar(String message, Image expected, Image actual, long toleranceInPixels) throws AssertionError {
    ImageDifferenceOperator operator = new ImageDifferenceOperator();
    Result result = operator.compute(expected, actual);
    
    if(result.getCount() > toleranceInPixels) {
      throw new AssertionError(message + " [" + result + "]");
    }
  }

  /**
   * Image should be similar
   * @param expected Expected image.
   * @param actual Actual image.
   * @param toleranceInPercentage Percentage of difference. {@code 1.0} is 1%. 
   * @throws AssertionError When images are too different.
   */
  public static void assertSimilar(Image expected, Image actual, double toleranceInPercentage) throws AssertionError {
    assertSimilar(ASSERT_SIMILAR_MESSAGE, expected, actual, toleranceInPercentage);
  }

  /**
   * Image should be similar
   * @param message Description of the test.
   * @param expected Expected image.
   * @param actual Actual image.
   * @param toleranceInPercentage Percentage of difference. {@code 1.0} is 1%. 
   * @throws AssertionError When images are too different.
   */
  public static void assertSimilar(String message, Image expected, Image actual, double toleranceInPercentage) throws AssertionError {
    ImageDifferenceOperator operator = new ImageDifferenceOperator();
    Result result = operator.compute(expected, actual);
    
    if(result.getRatio() > (toleranceInPercentage / 100.0)) {
      throw new AssertionError(message + " [" + result + "]");
    }
  }
}
