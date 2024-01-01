package symbols;

import javax.swing.ImageIcon;

public class GunSymbol implements Symbol{
  @Override
  public String getSymbol() {
    return "gun";
  }

  private static final int WIDTH = 60;
  private static final int HEIGHT = 60;

  @Override
  public ImageIcon getSymbolImage() {
    ImageIcon originalIcon = new ImageIcon("img/gun.png");
    return resizeIcon(originalIcon, WIDTH, HEIGHT);
  }
}
