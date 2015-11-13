package snappi.image.difference;

public class Result {
  private final IOperand imageA;
  private final IOperand imageB;
  
  private long count;
  private double percentage;
  
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

  public double getPercentage() {
    return percentage;
  }

  void setPercentage(double percentage) {
    this.percentage = percentage;
  }

  public IOperand getImageA() {
    return imageA;
  }

  public IOperand getImageB() {
    return imageB;
  }
  
  @Override
  public String toString() {
    return this.count + " pixels differs (" + this.percentage + ")";
  }
  
}
