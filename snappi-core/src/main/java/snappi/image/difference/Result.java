package snappi.image.difference;

public class Result {
  private final IOperand imageA;
  private final IOperand imageB;
  
  private long count;
  private double ratio;
  
  Result(final IOperand imageA, final IOperand imageB) {
    this.imageA = imageA;
    this.imageB = imageB;
  }

  public long getCount() {
    return count;
  }

  void setCount(long count) {
    this.count = count;
  }

  public double getRatio() {
    return ratio;
  }

  void setRatio(double ratio) {
    this.ratio = ratio;
  }

  public IOperand getImageA() {
    return imageA;
  }

  public IOperand getImageB() {
    return imageB;
  }
  
  @Override
  public String toString() {
    return this.count + " pixels differs (" + (this.ratio * 100.0) + "%)";
  }
  
}
