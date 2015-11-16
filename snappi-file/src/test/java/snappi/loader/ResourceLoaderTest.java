package snappi.loader;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ResourceLoaderTest {
  
  @Test
  public void loadExistingImage() {
    assertNotNull(ResourceLoader.load("lenna.png"));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void throwExceptionWithNonExistantFile() {
    ResourceLoader.load("non-existant.png");
  }
}
