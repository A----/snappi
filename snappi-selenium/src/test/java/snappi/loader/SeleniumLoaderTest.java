package snappi.loader;

import static snappi.asserts.Assertions.assertSame;

import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxDriver;

import snappi.image.Image;

@RunWith(Parameterized.class)
public class SeleniumLoaderTest {
  
  private static FirefoxDriver driver;
  
  @Parameters(name = "{0} (viewport size: {1}x{2})")
  public static Iterable<Object[]> responsiveResolutions() {
    return Arrays.asList(new Object[][] {
          { "mobile-landscape", 480, 320 },
          { "tablet-portrait", 768, 1024 },
          { "desktop", 1680, 1050 }
        });
  }

  @BeforeClass
  public static void setUp() {
    driver = new FirefoxDriver();
    driver.navigate().to("http://a----.github.io/snappi/test-website/");
  }
  
  @AfterClass
  public static void tearDown() {
    driver.close();
  }
  
  private String friendlyName;
  private int viewPortWidth;
  private int viewPortHeight;
  
  
  public SeleniumLoaderTest(String friendlyName, int viewPortWidth, int viewPortHeight) throws InterruptedException {
    this.friendlyName = friendlyName;
    this.viewPortWidth = viewPortWidth;
    this.viewPortHeight = viewPortHeight;

    driver
      .manage()
      .window()
      .setSize(new Dimension(this.viewPortWidth, this.viewPortHeight));
    
    // Wait a bit for elements to reflow.
    Thread.sleep(500);
  }
  
  @Test
  public void load() {
    Image expected = ResourceLoader.load("website-" + friendlyName + ".png");
    Image actual = SeleniumLoader
        .load(driver)
        .ignoreElement(By.id("current-date"));
    
    //TestUtils.write(actual, "website-" + friendlyName + ".png");
    
    assertSame("Test website should look the same (" + friendlyName + ")", expected, actual);
  }
}
