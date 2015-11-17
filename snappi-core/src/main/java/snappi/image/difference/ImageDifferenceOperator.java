package snappi.image.difference;

import java.awt.image.BufferedImage;

/**
 * Computes the difference between two images
 */
public class ImageDifferenceOperator {

  /**
   * Does the math.
   * See there: http://stackoverflow.com/questions/25022578/highlight-differences-between-images/25151302#25151302
   * @param imageA Left operand.
   * @param imageB right operand.
   * @return An object containing the number of different pixels and difference ratio.
   * @throws IllegalArgumentException When images do not have the same size.
   */
  public Result compute(final IOperand imageA, final IOperand imageB) {
    long count = 0;

    // Each image…
    final BufferedImage bufferedImageA = imageA.getImage();
    final BufferedImage bufferedImageB = imageB.getImage();
    
    // …has a mask, pixels _not_ under it will be ignored
    final int ignoreMaskA = imageA.getIgnoreMask();
    final int ignoreMaskB = imageB.getIgnoreMask();
    
    final int width = bufferedImageA.getWidth();
    final int height = bufferedImageA.getHeight();

    if (width != bufferedImageB.getWidth() || height != bufferedImageB.getHeight()) {
      throw new IllegalArgumentException("Images do not have the same size.");
    }

    final int[] pixelsA = bufferedImageA.getRGB(0, 0, width, height, null, 0, width);
    final int[] pixelsB = bufferedImageB.getRGB(0, 0, width, height, null, 0, width);

    for (int i = 0; i < pixelsA.length; i++) {
      if (pixelsA[i] != pixelsB[i] && ((pixelsA[i] & ignoreMaskA) != pixelsA[i] || (pixelsB[i] & ignoreMaskB) != pixelsB[i])) {
        ++count;
      }
    }
    
    Result result = new Result(imageA, imageB);
    result.setCount(count);
    result.setRatio(((double) count) / (width * height));

    return result;
  }
}
