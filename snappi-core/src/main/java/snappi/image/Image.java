package snappi.image;

import java.awt.image.BufferedImage;

import snappi.image.difference.IOperand;

/**
 * Wraps an {@link BufferedImage}, giving it quick and useful methods.
 */
public class Image implements IOperand {
  /**
   * Masking half-opaque (127/255) pixels.
   */
  public static final int IGNORE_ALPHA_MASK = 0x7FFFFFFF;

  /**
   * No mask at all.
   */
  public static final int NO_MASK = 0xFFFFFFFF;

  /**
   * Default mask is the half-opaque one.
   * @see #IGNORE_ALPHA_MASK
   */
  private int ignoreMask = IGNORE_ALPHA_MASK;
  
  /**
   * Underlying image.
   */
  private final BufferedImage image;
  
  /**
   * Creates a new instance of {@link Image}.
   * @param image A {@link BufferedImage}. It might be modified or a copy of it can also be made.
   *    See {@link #getImage()} if you want to get the processed image.
   */
  public Image(BufferedImage image) {
    if (image == null) {
      throw new IllegalArgumentException("Can't use a null argument for image.");
    }
    
    int type = image.getType();
    if (type == BufferedImage.TYPE_4BYTE_ABGR || type == BufferedImage.TYPE_INT_ARGB) {
      this.image = image;
    }
    else {
      this.image = addAlphaChannel(image);  
    }
  }
  
  /**
   * Adds an alpha channel to image by copying in into a new instance of the right type of
   * {@link BufferedImage}.
   * There might be a more optimized way of doing this. If so, please open an issue.
   * @param image The image to add an alpha channel to.
   * @return A copy of the provided image, with an alpha channel.
   */
  BufferedImage addAlphaChannel(BufferedImage image) {
    int width = image.getWidth();
    int height = image.getHeight();
    
    BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
    int[] pixels = image.getRGB(0, 0, width, height, null, 0, width);
    newImage.setRGB(0, 0, width, height, pixels, 0, width);
    
    return newImage;
  }
  
  /**
   * Ignores the specified region.
   * @param x Starting point of the rectangle on the X-axis.
   * @param y Starting point of the rectangle on the Y-axis.
   * @param w Width of the rectangle.
   * @param h Height of the rectange.
   * @return Itself.
   * @throws IllegalArgumentException If any of the arguments is negative.
   * @throws ArrayIndexOutOfBoundsException If the rectangle is not contained in the picture.
   */
  public Image ignoreRegion(int x, int y, int w, int h) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
    if (x < 0 || y < 0 || w < 0 || h < 0) {
      throw new IllegalArgumentException("Parameters can't be negatives");
    }
    
    if (this.ignoreMask == NO_MASK || w == 0 || h == 0) {
      return this;
    }
    
    final int imageWidth = this.image.getWidth();
    final int imageHeight = this.image.getHeight();
    if ((x + w) > imageWidth || (y + h) > imageHeight) {
      throw new ArrayIndexOutOfBoundsException("Coordinates are out of the image boundaries");
    }
    
    final int[] pixels = this.image.getRGB(x, y, w, h, null, 0, w);
    for (int i = 0; i < pixels.length; i++) {
      pixels[i] = pixels[i] & this.ignoreMask;
    }
    
    this.image.setRGB(x, y, w, h, pixels, 0, w);
    
    return this;
  }
  
  /**
   * Ignore mask for the current picture.
   * @return An integer, where bytes are respectively ARGB channels.
   * @see BufferedImage#getRGB(int, int)
   */
  public int getIgnoreMask() {
    return this.ignoreMask;
  }

  /**
   * Returns the underlying image.
   * @return The AWT object.
   */
  public BufferedImage getImage() {
    return this.image;
  }

}
