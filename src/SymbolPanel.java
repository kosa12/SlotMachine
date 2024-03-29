import symbols.*;

import javax.swing.Timer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.List;
import java.util.*;

public class SymbolPanel extends JPanel {
  private final Symbol[][] symbols;
  private Symbol[][] shuffledSymbols;
  private final Timer shuffleTimer;
  private final int shuffleDuration = 600;
  private final int symbolSize = 100;
  private final int rows = 3;
  private final int columns = 3;

  boolean isShuffling = false;

  public SymbolPanel() {
    symbols = new Symbol[rows][columns];
    initializeSymbols();
    shuffledSymbols = symbols.clone(); // Initial shuffle
    setPreferredSize(new Dimension(symbolSize * columns + (columns - 1) * 10, symbolSize * rows + (rows - 1) * 10));
    setBackground(Color.WHITE);

    shuffleTimer = new Timer(shuffleDuration, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        shuffleSymbols();
        repaint();
      }
    });
  }

  private void initializeSymbols() {
    List<Symbol> symbolTypes = new ArrayList<>();
    symbolTypes.add(new AppleSymbol());
    symbolTypes.add(new BananaSymbol());
    symbolTypes.add(new CasinoSymbol());
    symbolTypes.add(new DiamondSymbol());
    symbolTypes.add(new GunSymbol());
    symbolTypes.add(new SevenSymbol());
    symbolTypes.add(new CherrySymbol());

    Collections.shuffle(symbolTypes);

    Random rand = new Random();

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        int randomIndex = rand.nextInt(symbolTypes.size());
        symbols[i][j] = symbolTypes.get(randomIndex);
      }
    }
  }

  private void shuffleSymbols() {
    initializeSymbols();
    shuffledSymbols = symbols.clone();

    List<Symbol> flatSymbols = new ArrayList<>();
    for (int i = 0; i < rows; i++) {
      flatSymbols.addAll(Arrays.asList(shuffledSymbols[i]).subList(0, columns));
    }

    Collections.shuffle(flatSymbols);

    int count = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        shuffledSymbols[i][j] = flatSymbols.get(count);
        count++;
      }
    }
  }

  public void startShuffle() {
    if (!isShuffling) {
      InputStream inputStream = getClass().getResourceAsStream("/music/play_sound.wav");
      SoundEffect.play(inputStream);
      isShuffling = true;

      Thread shuffleThread = new Thread(new Runnable() {
        @Override
        public void run() {
          int steps = 15;

          for (int i = 0; i < steps; i++) {
            try {
              Thread.sleep((shuffleDuration) / steps);
            } catch (InterruptedException ex) {
              ex.printStackTrace();
            }

            shuffleSymbols();
            repaint();
          }

          shuffleSymbols();
          repaint();

          isShuffling = false;

          if (shuffleCompleteCallback != null) {
            shuffleCompleteCallback.run();
          }
        }
      });

      shuffleThread.start();
    }
  }

  private Runnable shuffleCompleteCallback;

  public void setShuffleCompleteCallback(Runnable callback) {
    this.shuffleCompleteCallback = callback;
  }

  public Symbol[][] getShuffledSymbols() {
    return shuffledSymbols;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    int x = 30;
    int y = 30;

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        Symbol symbol = symbols[i][j];
        ImageIcon icon = symbol.getSymbolImage();
        if (icon != null) {
          icon = new ImageIcon(icon.getImage().getScaledInstance(symbolSize, symbolSize, Image.SCALE_DEFAULT));
          icon.paintIcon(this, g, x, y);
        }

        x += symbolSize + 120;
      }

      x = 30;
      y += symbolSize + 20;
    }
  }
}
