package snappi.asserts;

import org.junit.Test;

import snappi.image.Image;
import snappi.testutils.TestSet;
import snappi.testutils.TestUtils;

public class AssertionsTest {
 
  @Test
  public void assertSameNoThrowWithSameImage() {
    Image lennaA = TestUtils.getImageFromResource(TestSet.LENNA);
    Image lennaB = TestUtils.getImageFromResource(TestSet.LENNA);
    
    Assertions.assertSame(lennaA, lennaB);
    Assertions.assertSame("reason", lennaA, lennaB);
  }

  
  @Test(expected = AssertionError.class)
  public void assertSameThrowWithDifferentImages() {
    Image lennaA = TestUtils.getImageFromResource(TestSet.LENNA);
    Image lennaB = TestUtils.getImageFromResource(TestSet.LENNA_MODIFIED);
    
    Assertions.assertSame(lennaA, lennaB);
  }
  
  @Test
  public void assertSameThrowWithDifferentAlphaImages() {
    Image lennaA = TestUtils.getImageFromResource(TestSet.LENNA_ALPHA);
    Image lennaB = TestUtils.getImageFromResource(TestSet.LENNA_MODIFIED_ALPHA);
    
    Assertions.assertSame(lennaA, lennaB);
  }
  
  @Test
  public void assertSimilarNoThrowHighEnoughTolerance() {
    Image lennaA = TestUtils.getImageFromResource(TestSet.LENNA);
    Image lennaB = TestUtils.getImageFromResource(TestSet.LENNA_MODIFIED);
    
    Assertions.assertSimilar(lennaA, lennaB, 498);
    Assertions.assertSimilar(lennaA, lennaB, 498L);
    Assertions.assertSimilar(lennaA, lennaB, 0.19);
  }

  
  @Test(expected = AssertionError.class)
  public void assertSimilarThrowLowToleranceInt() {
    Image lennaA = TestUtils.getImageFromResource(TestSet.LENNA);
    Image lennaB = TestUtils.getImageFromResource(TestSet.LENNA_MODIFIED);

    Assertions.assertSimilar(lennaA, lennaB, 497);
  }

  
  @Test(expected = AssertionError.class)
  public void assertSimilarThrowLowToleranceLong() {
    Image lennaA = TestUtils.getImageFromResource(TestSet.LENNA);
    Image lennaB = TestUtils.getImageFromResource(TestSet.LENNA_MODIFIED);

    Assertions.assertSimilar(lennaA, lennaB, 497L);
  }

  
  @Test(expected = AssertionError.class)
  public void assertSimilarThrowLowToleranceDouble() {
    Image lennaA = TestUtils.getImageFromResource(TestSet.LENNA);
    Image lennaB = TestUtils.getImageFromResource(TestSet.LENNA_MODIFIED);

    Assertions.assertSimilar(lennaA, lennaB, 0.18);
  }
}
