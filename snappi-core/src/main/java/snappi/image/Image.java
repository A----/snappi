package snappi.image;

import java.awt.image.BufferedImage;

import snappi.image.difference.IOperand;

public class Image implements IOperand {
  
  private final BufferedImage image;
  
  public Image(BufferedImage image) {
    if (image == null) {
      throw new IllegalArgumentException("Can't use a null argument for image.");
    }
    this.image = image;
  }
  
  

  public BufferedImage getImage() {
    return this.image;
  }

}
