package snappi.asserts;

import static snappi.asserts.Assertions.assertSame;
import static snappi.asserts.Assertions.assertSimilar;

import org.junit.Test;

import snappi.image.Image;
import snappi.testutils.TestSet;
import snappi.testutils.TestUtils;

public class AssertionsTest {
 
  @Test
  public void assertSameNoThrowWithSameImage() {
    Image lennaA = TestUtils.getImage(TestSet.LENNA);
    Image lennaB = TestUtils.getImage(TestSet.LENNA);
    
    assertSame(lennaA, lennaB);
    assertSame("reason", lennaA, lennaB);
  }

  
  @Test(expected = AssertionError.class)
  public void assertSameThrowWithDifferentImages() {
    Image lennaA = TestUtils.getImage(TestSet.LENNA);
    Image lennaB = TestUtils.getImage(TestSet.LENNA_MODIFIED);
    
    assertSame(lennaA, lennaB);
  }
  
  @Test
  public void assertSameThrowWithDifferentAlphaImages() {
    Image lennaA = TestUtils.getImage(TestSet.LENNA_ALPHA);
    Image lennaB = TestUtils.getImage(TestSet.LENNA_MODIFIED_ALPHA);
    
    assertSame(lennaA, lennaB);
  }
  
  @Test
  public void assertSimilarNoThrowHighEnoughTolerance() {
    Image lennaA = TestUtils.getImage(TestSet.LENNA);
    Image lennaB = TestUtils.getImage(TestSet.LENNA_MODIFIED);
    
    assertSimilar(lennaA, lennaB, 498);
    assertSimilar(lennaA, lennaB, 498L);
    assertSimilar(lennaA, lennaB, 0.19);
  }

  
  @Test(expected = AssertionError.class)
  public void assertSimilarThrowLowToleranceInt() {
    Image lennaA = TestUtils.getImage(TestSet.LENNA);
    Image lennaB = TestUtils.getImage(TestSet.LENNA_MODIFIED);

    assertSimilar(lennaA, lennaB, 497);
  }

  
  @Test(expected = AssertionError.class)
  public void assertSimilarThrowLowToleranceLong() {
    Image lennaA = TestUtils.getImage(TestSet.LENNA);
    Image lennaB = TestUtils.getImage(TestSet.LENNA_MODIFIED);

    Assertions.assertSimilar(lennaA, lennaB, 497L);
  }

  
  @Test(expected = AssertionError.class)
  public void assertSimilarThrowLowToleranceDouble() {
    Image lennaA = TestUtils.getImage(TestSet.LENNA);
    Image lennaB = TestUtils.getImage(TestSet.LENNA_MODIFIED);

    Assertions.assertSimilar(lennaA, lennaB, 0.18);
  }
}
