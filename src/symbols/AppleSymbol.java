package symbols;

import javax.swing.ImageIcon;

public class AppleSymbol implements Symbol{

  @Override
  public String getSymbol() {
    return "apple";
  }

  private static final int WIDTH = 60;
  private static final int HEIGHT = 60;

  @Override
  public ImageIcon getSymbolImage() {
    ImageIcon originalIcon = new ImageIcon("img/apple.png");
    return resizeIcon(originalIcon, WIDTH, HEIGHT);
  }


}