package symbols;

import javax.swing.ImageIcon;

public class CherrySymbol implements Symbol{

  @Override
  public String getSymbol() {
    return "cherry";
  }

  private static final int WIDTH = 60;
  private static final int HEIGHT = 60;

  @Override
  public ImageIcon getSymbolImage() {
    ImageIcon originalIcon = new ImageIcon("img/cherry.png");
    return resizeIcon(originalIcon, WIDTH, HEIGHT);
  }


}