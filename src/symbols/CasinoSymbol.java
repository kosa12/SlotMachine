package symbols;

import javax.swing.*;

public class CasinoSymbol implements Symbol {

  @Override
  public String getSymbol() {
    return "Casino";
  }

  private static final int WIDTH = 60;
  private static final int HEIGHT = 60;

  @Override
  public ImageIcon getSymbolImage() {
    ImageIcon originalIcon = new ImageIcon("src/img/casino.png");
    return resizeIcon(originalIcon, WIDTH, HEIGHT);
  }

}