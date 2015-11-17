package snappi.image.difference;

import java.awt.image.BufferedImage;

/**
 * Operand for the {@link ImageDifferenceOperator} operator.
 */
public interface IOperand {
  /**
   * Retrieves the underlying AWT image.
   * @return The AWT image.
   */
  public BufferedImage getImage();

  /**
   * Ignore mask for this image.
   * Pixels _not_ under it will be ignored.
   * @return An int describing the ARGB value.
   * @see BufferedImage#getRGB(int, int)
   */
  public int getIgnoreMask();
}
