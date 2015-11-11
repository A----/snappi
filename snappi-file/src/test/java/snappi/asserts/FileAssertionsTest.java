package snappi.asserts;

import java.awt.image.BufferedImage;

import org.junit.Test;

import snappi.loader.ResourceLoader;

public class FileAssertionsTest {
  
  @Test
  public void assertSameNoThrowWithSameImage() {
    BufferedImage lennaImage = ResourceLoader.load("lenna.png");
    
    FileAssertions.assertSame("lenna.png", "lenna.png");
    FileAssertions.assertSame("reason", "lenna.png", "lenna.png");
    FileAssertions.assertSame("lenna.png", lennaImage);
    FileAssertions.assertSame("reason", "lenna.png", lennaImage);
  }


  @Test(expected = AssertionError.class)
  public void assertSameThrowWithDifferentImagesStringString() {
    FileAssertions.assertSame("lenna.png", "lenna-modified.png");
  }


  @Test(expected = AssertionError.class)
  public void assertSameThrowWithDifferentImagesStringImage() {
    BufferedImage lennaImage = ResourceLoader.load("lenna-modified.png");
    FileAssertions.assertSame("lenna.png", lennaImage);
  }
}
