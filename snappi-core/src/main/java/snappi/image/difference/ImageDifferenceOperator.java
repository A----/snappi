package snappi.image.difference;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Computes the difference between two images
 */
public class ImageDifferenceOperator {
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

  /**
   * Does the math.
   * See there: http://stackoverflow.com/questions/25022578/highlight-differences-between-images/25151302#25151302
   * @param imageA Left operand.
   * @param imageB right operand.
   */
  public Result compute(IOperand imageA, IOperand imageB) {
    long count = 0;

    final BufferedImage bufferedImageA = imageA.getImage();
    final BufferedImage bufferedImageB = imageB.getImage();
    
    final int width = bufferedImageA.getWidth();
    final int height = bufferedImageB.getHeight();

    if (width != bufferedImageB.getWidth() || height != bufferedImageB.getHeight()) {
      throw new IllegalArgumentException("Images does not have the same size.");
    }

    final int[] pixelsA = bufferedImageA.getRGB(0, 0, width, height, null, 0, width);
    final int[] pixelsB = bufferedImageB.getRGB(0, 0, width, height, null, 0, width);

    final int BLACK_RGB = Color.BLACK.getRGB();

    for (int i = 0; i < pixelsA.length; i++) {
      if (pixelsA[i] != pixelsB[i] && (pixelsA[i] & this.ignoreMask & pixelsB[i]) == BLACK_RGB) {
        ++count;
      }
    }
    
    Result result = new Result(imageA, imageB);
    result.setCount(count);
    result.setPercentage(((double) count) / (width * height));

    return result;
  }
}
