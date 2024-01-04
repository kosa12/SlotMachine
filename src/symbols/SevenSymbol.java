package symbols;

import javax.swing.ImageIcon;

public class SevenSymbol implements Symbol{

  @Override
  public String getSymbol() {
    return "Seven";
  }

  private static final int WIDTH = 60;
  private static final int HEIGHT = 60;

  @Override
  public ImageIcon getSymbolImage() {
    ImageIcon originalIcon = new ImageIcon("src/img/seven.png");
    return resizeIcon(originalIcon, WIDTH, HEIGHT);
  }


}
