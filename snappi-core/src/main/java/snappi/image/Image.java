package snappi.image;

import java.awt.image.BufferedImage;

import snappi.image.difference.IOperand;

public class Image implements IOperand {

  private static int IGNORE_ALPHA_MASK = 0x7FFFFFFF;

  private static int NO_MASK = 0xFFFFFF;

  private int ignoreMask = IGNORE_ALPHA_MASK;
  
  private final BufferedImage image;
  
  public Image(BufferedImage image) {
    if (image == null) {
      throw new IllegalArgumentException("Can't use a null argument for image.");
    }
    this.image = image;
  }
  
  public Image ignore(int x, int y, int w, int h) {
    return this;
  }
  
  public int getIgnoreMask() {
    return this.ignoreMask;
  }

  public BufferedImage getImage() {
    return this.image;
  }

}
