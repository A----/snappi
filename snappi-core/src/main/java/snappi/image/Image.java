package snappi.image;

import java.awt.image.BufferedImage;

import snappi.image.difference.IOperand;

public class Image implements IOperand {
  /**
   * Setting this for the current mask will ignore differences in pixels that are,
   * in both images, not fully opaque.
   */
  private static int IGNORE_ALPHA_MASK = 0x00FF000000;
  /**
   * No pixel will be ignored.
   */
  private static int NO_MASK = 0x00000000;

  /**
   * Current mask.
   * If, in both images, a pixel under that mask is not 0, it will be ignored.
   * If only one of them is 0, it will count as different.
   */
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
  
  @Override
  public int getIgnoreMask() {
    return this.ignoreMask;
  }

  @Override
  public BufferedImage getImage() {
    return this.image;
  }

}
