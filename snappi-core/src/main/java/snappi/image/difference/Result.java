package snappi.image.difference;

/**
 * Result of the {@link ImageDifferenceOperator} operator.
 */
public class Result {
  /**
   * Left operand.
   */
  private final IOperand imageA;
  /**
   * Right operand.
   */
  private final IOperand imageB;
  
  /**
   * Number of pixels different for the same coordinates in
   * the two operands.
   */
  private long count;
  /**
   * Ratio of different pixels. If all pixels are different, this
   * is going to be {@literal 1}.
   * This number is usually extremely small.
   */
  private double ratio;
  
  /**
   * Constructor.
   * @param imageA Left operand.
   * @param imageB Right operand.
   */
  Result(final IOperand imageA, final IOperand imageB) {
    this.imageA = imageA;
    this.imageB = imageB;
  }

  /**
   * Retrieves the number of pixels different for the same coordinates in
   * provided images.
   * @return The number of said pixels.
   */
  public long getCount() {
    return count;
  }

  /**
   * Sets the number of pixels different for the same coordinates in
   * provided images.
   * Ratio is to be updated separately!
   * @param count The number of said pixels.
   */
  void setCount(long count) {
    this.count = count;
  }

  /**
   * Retrieves the ratio of different pixels. If all pixels are different, this
   * is going to be {@literal 1}.
   * @return Said ratio.
   */
  public double getRatio() {
    return ratio;
  }

  /**
   * Sets the ratio of different pixels. {@literal 1} means they are all different.
   * Count is to be updated separately!
   * @param ratio
   */
  void setRatio(double ratio) {
    this.ratio = ratio;
  }

  /**
   * Retrieves left operand.
   * @return The left operand.
   */
  public IOperand getImageA() {
    return imageA;
  }

  /**
   * Retrieves right operand.
   * @return The right operand.
   */
  public IOperand getImageB() {
    return imageB;
  }
  
  /**
   * Serializes the result in a human-readable form.
   */
  @Override
  public String toString() {
    return this.count + " pixels differs (" + (this.ratio * 100.0) + "%)";
  }
  
}
