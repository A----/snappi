package snappi.image.difference;

import java.awt.image.BufferedImage;

public interface IOperand {
  public BufferedImage getImage();

  public int getIgnoreMask();
}
