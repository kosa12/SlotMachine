package symbols;

import javax.swing.ImageIcon;

public class DiamondSymbol implements Symbol {
  @Override
  public String getSymbol() {
    return "Diamond";
  }

  private static final int WIDTH = 60;
  private static final int HEIGHT = 60;

  @Override
  public ImageIcon getSymbolImage() {
    ImageIcon originalIcon = new ImageIcon("img/diamond.png");
    return resizeIcon(originalIcon, WIDTH, HEIGHT);
  }
}
