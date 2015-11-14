package snappi.image.difference;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import snappi.image.Image;
import snappi.testutils.TestSet;
import snappi.testutils.TestUtils;

public class ImageDifferenceOperatorTest {

  
  
  @Test
  public void noDifferenceWithItself() {
    Image lennaA = TestUtils.getImage(TestSet.LENNA);
    Image lennaB = TestUtils.getImage(TestSet.LENNA);

    ImageDifferenceOperator operator = new ImageDifferenceOperator();
    Result result = operator.compute(lennaA, lennaB);

    assertEquals("There should not be any differences between an image and itself.", 0L, result.getCount());
  }

  @Test
  public void someDifferencesExpected() {
    Image lennaA = TestUtils.getImage(TestSet.LENNA);
    Image lennaB = TestUtils.getImage(TestSet.LENNA_MODIFIED);

    ImageDifferenceOperator operator = new ImageDifferenceOperator();
    Result result = operator.compute(lennaA, lennaB);

    assertEquals("There are differences between those images.", 498L, result.getCount());
  }

  @Test
  public void noDifferenceWithModifiedAlpha() {
    Image lennaA = TestUtils.getImage(TestSet.LENNA_ALPHA);
    Image lennaB = TestUtils.getImage(TestSet.LENNA_MODIFIED_ALPHA);

    ImageDifferenceOperator operator = new ImageDifferenceOperator();
    Result result = operator.compute(lennaA, lennaB);

    assertEquals("There should not be any differences when modifications are not fully opaque.", 0L, result.getCount());
  }

  @Test(expected = IllegalArgumentException.class)
  public void imagesOfDifferentSizes() {
    Image lenna = TestUtils.getImage(TestSet.LENNA);
    Image redPixel = TestUtils.getImage(TestSet.RED_PIXEL);

    ImageDifferenceOperator operator = new ImageDifferenceOperator();
    operator.compute(lenna, redPixel);
  }
}
