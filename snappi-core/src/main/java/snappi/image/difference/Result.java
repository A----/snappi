package snappi.image.difference;

import java.awt.image.BufferedImage;

public class Result {
  private final BufferedImage imageA;
  private final BufferedImage imageB;
  
  private long count;
  private long percentage;
  
  Result(BufferedImage imageA, BufferedImage imageB) {
    this.imageA = imageA;
    this.imageB = imageB;
  }

  public long getCount() {
    return count;
  }

  void setCount(long count) {
    this.count = count;
  }

  public long getPercentage() {
    return percentage;
  }

  void setPercentage(long percentage) {
    this.percentage = percentage;
  }

  public BufferedImage getImageA() {
    return imageA;
  }

  public BufferedImage getImageB() {
    return imageB;
  }
  
  @Override
  public String toString() {
    return this.count + " pixels differs (" + this.percentage + ")";
  }
  
}
