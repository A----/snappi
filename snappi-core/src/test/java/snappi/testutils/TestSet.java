package snappi.testutils;

public enum TestSet {
  LENNA("lenna.png"),
  LENNA_MODIFIED("lenna-modified.png"),
  LENNA_ALPHA("lenna-alpha.png"),
  LENNA_MODIFIED_ALPHA("lenna-modified-alpha.png"),
  RED_PIXEL("red-pixel.png");
  
  private final String filename;
  
  TestSet(String filename) {
    this.filename = filename;
  }
  
  public String getFilename() {
    return this.filename;
  }
}
