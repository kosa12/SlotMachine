package symbols;

import javax.swing.ImageIcon;

public class BananaSymbol implements Symbol {

  @Override
  public String getSymbol() {
    return "banana";
  }

  private static final int WIDTH = 60;
  private static final int HEIGHT = 60;

  @Override
  public ImageIcon getSymbolImage() {
    ImageIcon originalIcon = new ImageIcon("img/banana.png");
    return resizeIcon(originalIcon, WIDTH, HEIGHT);
  }

}