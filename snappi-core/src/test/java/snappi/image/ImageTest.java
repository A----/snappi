package snappi.image;


import static org.junit.Assert.assertEquals;

import java.awt.image.BufferedImage;

import org.junit.Test;

import snappi.image.difference.ImageDifferenceOperator;
import snappi.image.difference.Result;
import snappi.testutils.TestSet;
import snappi.testutils.TestUtils;

public class ImageTest {
  
  @Test
  public void defaultMaskIsAlpha() {
    Image lenna = TestUtils.getImage(TestSet.LENNA);
    
    assertEquals("Default mask should be alpha.", Image.IGNORE_ALPHA_MASK, lenna.getIgnoreMask());
  }
  
  @Test
  public void addAlphaChannel() {
    BufferedImage lenna = TestUtils.getBufferedImage(TestSet.LENNA);
    
    assertEquals("Lenna image should not have an alpha channel.", BufferedImage.TYPE_3BYTE_BGR, lenna.getType());
    
    Image image = new Image(lenna);
    
    assertEquals("Lenna should now have an alpha channel.", BufferedImage.TYPE_4BYTE_ABGR, image.getImage().getType());
  }
  
  @Test
  public void ignoreRegionOnePixel() {
    Image lenna = TestUtils.getImage(TestSet.LENNA);
    
    BufferedImage image = lenna.ignoreRegion(5, 5, 1, 1).getImage();
    int ignoredPixel = image.getRGB(5, 5);
    
    assertEquals("Pixel should be translucent", 0x7F, (ignoredPixel >> 24) & 0xFF);
  }
  
  @Test
  public void ignoreRegionAndCompare1() {
    Image lennaA = TestUtils.getImage(TestSet.LENNA_MODIFIED_ALPHA);
    Image lennaB = TestUtils.getImage(TestSet.LENNA);
    
    lennaB.ignoreRegion(347, 21, 90, 88);
    
    ImageDifferenceOperator operator = new ImageDifferenceOperator();
    Result result = operator.compute(lennaA, lennaB);
    
    assertEquals("After ignoring the same region, there should be no differences.", 0L, result.getCount());
  }
  
  @Test
  public void ignoreRegionAndCompare2() {
    Image lennaA = TestUtils.getImage(TestSet.LENNA);
    Image lennaB = TestUtils.getImage(TestSet.LENNA);
    
    lennaB.ignoreRegion(0, 0, 5, 5);
    
    ImageDifferenceOperator operator = new ImageDifferenceOperator();
    Result result = operator.compute(lennaA, lennaB);
    
    assertEquals("When ignoring a 5 by 5 region, total number of different pixels should be 25.", 25L, result.getCount());
  }
}
