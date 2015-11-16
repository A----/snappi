package snappi.loader;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import snappi.testutils.TestSet;

public class FileLoaderTest {
  
  private File copyToTmp(TestSet testSetElement) throws IOException {
    File tmp = File.createTempFile(testSetElement.name(), ".png");
    return tmp;
  }
  
  public void loadExistingFile() throws IOException {
    File tmp = copyToTmp(TestSet.LENNA);
    assertNotNull(FileLoader.load(tmp));
    assertNotNull(FileLoader.load(tmp.getAbsolutePath()));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void throwExceptionWithNonExistantPath() {
    FileLoader.load("non-existant.png");
  }

  @Test(expected = IllegalArgumentException.class)
  public void throwExceptionWithNonExistantFile() {
    FileLoader.load(new File("non-existant.png"));
  }
}
