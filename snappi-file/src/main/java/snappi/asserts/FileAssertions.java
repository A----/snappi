package snappi.asserts;

import java.awt.image.BufferedImage;
import java.io.File;

import snappi.loader.FileLoader;
import snappi.loader.ResourceLoader;

public class FileAssertions {
  // ASSERT SAME
  
  // String vs String
  
  public static void assertSame(String expectedResourcePath, String actualResourcePath) {
    assertSame(Assertions.ASSERT_SAME_MESSAGE, expectedResourcePath, actualResourcePath);
  }
  
  public static void assertSame(String message, String expectedResourcePath, String actualResourcePath) {
    BufferedImage expectedImage = ResourceLoader.load(expectedResourcePath);
    BufferedImage actualImage = ResourceLoader.load(actualResourcePath);
    
    Assertions.assertSame(message, expectedImage, actualImage);
  }
  
  // String vs Image
  
  public static void assertSame(String expectedResourcePath, BufferedImage actualImage) {
    assertSame(Assertions.ASSERT_SAME_MESSAGE, expectedResourcePath, actualImage);
  }
  
  public static void assertSame(String message, String expectedResourcePath, BufferedImage actualImage) {
    BufferedImage expectedImage = ResourceLoader.load(expectedResourcePath);
    
    Assertions.assertSame(message, expectedImage, actualImage);
  }
  
  // File vs File
  
  public static void assertSame(File expectedFile, File actualFile) {
    assertSame(Assertions.ASSERT_SAME_MESSAGE, expectedFile, actualFile);
  }
  
  public static void assertSame(String message, File expectedFile, File actualFile) {
    BufferedImage expectedImage = FileLoader.load(expectedFile);
    BufferedImage actualImage = FileLoader.load(actualFile);
    
    Assertions.assertSame(message, expectedImage, actualImage);
  }
  
  // File vs Image
  
  public static void assertSame(File expectedFile, BufferedImage actualImage) {
    assertSame(Assertions.ASSERT_SAME_MESSAGE, expectedFile, actualImage);
  }
  
  public static void assertSame(String message, File expectedFile, BufferedImage actualImage) {
    BufferedImage expectedImage = FileLoader.load(expectedFile);
    
    Assertions.assertSame(message, expectedImage, actualImage);
  }
  
  // ASSERT SIMILAR // PIXELS AS INT
  
  // String vs String
  
  public static void assertSimilar(String expectedResourcePath, String actualResourcePath, int toleranceInPixels) {
    assertSimilar(Assertions.ASSERT_SIMILAR_MESSAGE, expectedResourcePath, actualResourcePath, toleranceInPixels);
  }
  
  public static void assertSimilar(String message, String expectedResourcePath, String actualResourcePath, int toleranceInPixels) {
    BufferedImage expectedImage = ResourceLoader.load(expectedResourcePath);
    BufferedImage actualImage = ResourceLoader.load(actualResourcePath);
    
    Assertions.assertSimilar(message, expectedImage, actualImage, (long) toleranceInPixels);
  }
  
  // String vs Image
  
  public static void assertSimilar(String expectedResourcePath, BufferedImage actualImage, int toleranceInPixels) {
    assertSimilar(Assertions.ASSERT_SIMILAR_MESSAGE, expectedResourcePath, actualImage, toleranceInPixels);
  }
  
  public static void assertSimilar(String message, String expectedResourcePath, BufferedImage actualImage, int toleranceInPixels) {
    BufferedImage expectedImage = ResourceLoader.load(expectedResourcePath);
    
    Assertions.assertSimilar(message, expectedImage, actualImage, (long) toleranceInPixels);
  }
  
  // File vs File
  
  public static void assertSimilar(File expectedFile, File actualFile, int toleranceInPixels) {
    assertSimilar(Assertions.ASSERT_SIMILAR_MESSAGE, expectedFile, actualFile, toleranceInPixels);
  }
  
  public static void assertSimilar(String message, File expectedFile, File actualFile, int toleranceInPixels) {
    BufferedImage expectedImage = FileLoader.load(expectedFile);
    BufferedImage actualImage = FileLoader.load(actualFile);
    
    Assertions.assertSimilar(message, expectedImage, actualImage, (long) toleranceInPixels);
  }
  
  // File vs Image
  
  public static void assertSimilar(File expectedFile, BufferedImage actualImage, int toleranceInPixels) {
    assertSimilar(Assertions.ASSERT_SIMILAR_MESSAGE, expectedFile, actualImage, toleranceInPixels);
  }
  
  public static void assertSimilar(String message, File expectedFile, BufferedImage actualImage, int toleranceInPixels) {
    BufferedImage expectedImage = FileLoader.load(expectedFile);
    
    Assertions.assertSimilar(message, expectedImage, actualImage, (long) toleranceInPixels);
  }
  
  // ASSERT SIMILAR // PIXELS AS LONG
  
  // String vs String
  
  public static void assertSimilar(String expectedResourcePath, String actualResourcePath, long toleranceInPixels) {
    assertSimilar(Assertions.ASSERT_SIMILAR_MESSAGE, expectedResourcePath, actualResourcePath, toleranceInPixels);
  }
  
  public static void assertSimilar(String message, String expectedResourcePath, String actualResourcePath, long toleranceInPixels) {
    BufferedImage expectedImage = ResourceLoader.load(expectedResourcePath);
    BufferedImage actualImage = ResourceLoader.load(actualResourcePath);
    
    Assertions.assertSimilar(message, expectedImage, actualImage, toleranceInPixels);
  }
  
  // String vs Image
  
  public static void assertSimilar(String expectedResourcePath, BufferedImage actualImage, long toleranceInPixels) {
    assertSimilar(Assertions.ASSERT_SIMILAR_MESSAGE, expectedResourcePath, actualImage, toleranceInPixels);
  }
  
  public static void assertSimilar(String message, String expectedResourcePath, BufferedImage actualImage, long toleranceInPixels) {
    BufferedImage expectedImage = ResourceLoader.load(expectedResourcePath);
    
    Assertions.assertSimilar(message, expectedImage, actualImage, toleranceInPixels);
  }
  
  // File vs File
  
  public static void assertSimilar(File expectedFile, File actualFile, long toleranceInPixels) {
    assertSimilar(Assertions.ASSERT_SIMILAR_MESSAGE, expectedFile, actualFile, toleranceInPixels);
  }
  
  public static void assertSimilar(String message, File expectedFile, File actualFile, long toleranceInPixels) {
    BufferedImage expectedImage = FileLoader.load(expectedFile);
    BufferedImage actualImage = FileLoader.load(actualFile);
    
    Assertions.assertSimilar(message, expectedImage, actualImage, toleranceInPixels);
  }
  
  // File vs Image
  
  public static void assertSimilar(File expectedFile, BufferedImage actualImage, long toleranceInPixels) {
    assertSimilar(Assertions.ASSERT_SIMILAR_MESSAGE, expectedFile, actualImage, toleranceInPixels);
  }
  
  public static void assertSimilar(String message, File expectedFile, BufferedImage actualImage, long toleranceInPixels) {
    BufferedImage expectedImage = FileLoader.load(expectedFile);
    
    Assertions.assertSimilar(message, expectedImage, actualImage, toleranceInPixels);
  }
  
  // ASSERT SIMILAR // PERCENTAGE
  
  // String vs String
  
  public static void assertSimilar(String expectedResourcePath, String actualResourcePath, double toleranceInPercentage) {
    assertSimilar(Assertions.ASSERT_SIMILAR_MESSAGE, expectedResourcePath, actualResourcePath, toleranceInPercentage);
  }
  
  public static void assertSimilar(String message, String expectedResourcePath, String actualResourcePath, double toleranceInPercentage) {
    BufferedImage expectedImage = ResourceLoader.load(expectedResourcePath);
    BufferedImage actualImage = ResourceLoader.load(actualResourcePath);
    
    Assertions.assertSimilar(message, expectedImage, actualImage, toleranceInPercentage);
  }
  
  // String vs Image
  
  public static void assertSimilar(String expectedResourcePath, BufferedImage actualImage, double toleranceInPercentage) {
    assertSimilar(Assertions.ASSERT_SIMILAR_MESSAGE, expectedResourcePath, actualImage, toleranceInPercentage);
  }
  
  public static void assertSimilar(String message, String expectedResourcePath, BufferedImage actualImage, double toleranceInPercentage) {
    BufferedImage expectedImage = ResourceLoader.load(expectedResourcePath);
    
    Assertions.assertSimilar(message, expectedImage, actualImage, toleranceInPercentage);
  }
  
  // File vs File
  
  public static void assertSimilar(File expectedFile, File actualFile, double toleranceInPercentage) {
    assertSimilar(Assertions.ASSERT_SIMILAR_MESSAGE, expectedFile, actualFile, toleranceInPercentage);
  }
  
  public static void assertSimilar(String message, File expectedFile, File actualFile, double toleranceInPercentage) {
    BufferedImage expectedImage = FileLoader.load(expectedFile);
    BufferedImage actualImage = FileLoader.load(actualFile);
    
    Assertions.assertSimilar(message, expectedImage, actualImage, toleranceInPercentage);
  }
  
  // File vs Image
  
  public static void assertSimilar(File expectedFile, BufferedImage actualImage, double toleranceInPercentage) {
    assertSimilar(Assertions.ASSERT_SIMILAR_MESSAGE, expectedFile, actualImage, toleranceInPercentage);
  }
  
  public static void assertSimilar(String message, File expectedFile, BufferedImage actualImage, double toleranceInPercentage) {
    BufferedImage expectedImage = FileLoader.load(expectedFile);
    
    Assertions.assertSimilar(message, expectedImage, actualImage, toleranceInPercentage);
  }
}
