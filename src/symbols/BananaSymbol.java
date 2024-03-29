package symbols;

import javax.swing.*;

public class BananaSymbol implements Symbol {

  @Override
  public String getSymbol() {
    return "Banana";
  }

  private static final int WIDTH = 60;
  private static final int HEIGHT = 60;

  @Override
  public ImageIcon getSymbolImage() {
    ImageIcon originalIcon = new ImageIcon("src/img/banana.png");
    return resizeIcon(originalIcon, WIDTH, HEIGHT);
  }

}