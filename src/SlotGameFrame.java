import symbols.Symbol;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class SlotGameFrame extends JFrame {
  private final SymbolPanel symbolPanel;
  private final JLabel balanceLabel;
  private final JLabel nameLabel;
  private final JLabel betLabel;
  private final JTextField betField;
  private final JLabel wonLabel;
  private double currentBalance;

  Font font1;
  {
    String fName = "fonts/SAIBA-45.ttf";
    try (InputStream is = SlotGameFrame.class.getResourceAsStream(fName)) {
      if (is == null) {
        throw new IOException("Font file not found: " + fName);
      }
      font1 = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(60f);
    } catch (FontFormatException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  Font font1b;
  {
    String fName = "fonts/SAIBA-45-Outline.ttf";
    try (InputStream is = SlotGameFrame.class.getResourceAsStream(fName)) {
      if (is == null) {
        throw new IOException("Font file not found: " + fName);
      }
      font1b = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(59f);
    } catch (FontFormatException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  Font font2;
  {
    String fName = "fonts/SAIBA-45.ttf";
    try (InputStream is = SlotGameFrame.class.getResourceAsStream(fName)) {
      if (is == null) {
        throw new IOException("Font file not found: " + fName);
      }
      font2 = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(70f);
    } catch (FontFormatException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  Font font3;
  {
    String fName = "fonts/SAIBA-45.ttf";
    try (InputStream is = SlotGameFrame.class.getResourceAsStream(fName)) {
      if (is == null) {
        throw new IOException("Font file not found: " + fName);
      }
      font3 = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(30f);
    } catch (FontFormatException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  Font font3b;
  {
    String fName = "fonts/SAIBA-45.ttf";
    try (InputStream is = SlotGameFrame.class.getResourceAsStream(fName)) {
      if (is == null) {
        throw new IOException("Font file not found: " + fName);
      }
      font3b = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(30f);
    } catch (FontFormatException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  Font font4;
  {
    String fName = "fonts/SAIBA-45.ttf";
    try (InputStream is = SlotGameFrame.class.getResourceAsStream(fName)) {
      if (is == null) {
        throw new IOException("Font file not found: " + fName);
      }
      font4 = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(40f);
    } catch (FontFormatException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  public SlotGameFrame(double balance, String name) {
    currentBalance = balance;
    BackgroundMusic.getInstance().addReference();

    setSize(1200, 700);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getContentPane().setBackground(new Color(113, 28, 155));
    setLayout(null);

    Container con = getContentPane();

    symbolPanel = new SymbolPanel();
    symbolPanel.setBackground(new Color(113, 28, 155));
    symbolPanel.setBounds(290, 120, 600, 400);
    symbolPanel.setBorder(new LineBorder(Color.black, 5));
    con.add(symbolPanel);

    balanceLabel = new JLabel(String.format("$%.1f", currentBalance));
    balanceLabel.setFont(font1);
    balanceLabel.setForeground(Color.WHITE);
    balanceLabel.setBounds(890, 580, 350, 60);
    con.add(balanceLabel);

    nameLabel = new JLabel(name);
    nameLabel.setForeground(new Color(234, 0, 217));
    nameLabel.setBounds(20, 20, 400, 70);
    nameLabel.setFont(font2);
    con.add(nameLabel);

    betLabel = new JLabel("Place your bet:");
    betLabel.setForeground(Color.WHITE);
    betLabel.setFont(font3);
    betLabel.setBounds(20, 580, 300, 50);
    con.add(betLabel);

    betField = new JTextField();
    betField.setBounds(50, 625, 200, 30);
    betField.setBackground(new Color(211, 153, 240));
    betField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
    betField.setFont(font3b);
    betField.setForeground(Color.BLACK);
    con.add(betField);

    JButton shuffleButton = new JButton("Shuffle");
    shuffleButton.setFocusable(false);
    shuffleButton.setBorderPainted(false);
    shuffleButton.setOpaque(false);
    shuffleButton.setContentAreaFilled(false);
    shuffleButton.setBackground(new Color(113, 28, 155));
    shuffleButton.setForeground(new Color(234, 0, 217));
    shuffleButton.setFont(font1);
    shuffleButton.setBounds(400, 580, 400, 60);

    JButton shuffleButton2 = new JButton("Shuffle");
    shuffleButton2.setFocusable(false);
    shuffleButton2.setBorderPainted(false);
    shuffleButton2.setOpaque(false);
    shuffleButton2.setContentAreaFilled(false);
    shuffleButton2.setBackground(new Color(113, 28, 155));
    shuffleButton2.setForeground(new Color(211, 189, 222));
    shuffleButton2.setFont(font1b);
    shuffleButton2.setBounds(400, 580, 400, 60);
    con.add(shuffleButton2);
    shuffleButton2.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        handleBet();
      }
    });
    con.add(shuffleButton);

    wonLabel = new JLabel("Won: $0.0");
    wonLabel.setForeground(new Color(234, 0, 217));
    wonLabel.setFont(font4);
    wonLabel.setBounds(890, 530, 300, 50);
    con.add(wonLabel);

    setLocationRelativeTo(null);
    setVisible(true);
  }

  private void handleBet() {
    try {
      double betAmount = Double.parseDouble(betField.getText());
      if (betAmount >= 0 && betAmount <= currentBalance) {

        symbolPanel.setShuffleCompleteCallback(() -> checkForWin(betAmount));
        symbolPanel.startShuffle();
      } else {
        JOptionPane.showMessageDialog(this, "Invalid bet amount. Please enter a valid amount within your balance.");
      }
    } catch (NumberFormatException ex) {
      JOptionPane.showMessageDialog(this, "Invalid bet amount. Please enter a valid number.");
    }
  }

  private void checkForWin(double betAmount) {
    Symbol[][] spinResult = symbolPanel.getShuffledSymbols();
    double wonAmount = 0;
    if (SlotGameLogic.isWin(spinResult)) {
      wonAmount = SlotGameLogic.calculatePayout(spinResult, betAmount);
      currentBalance = SlotGameLogic.updateBalance(currentBalance, betAmount, wonAmount);
      showWinDialog(wonAmount);
    } else {
      currentBalance -= betAmount;
    }


    updateUI(wonAmount);
  }

  private void showWinDialog(double wonAmount) {
    InputStream inputStream = getClass().getResourceAsStream("/music/win_sound.wav");
    SoundEffect.play(inputStream);
    JLabel winLabel;

    if (wonAmount > 2 * Double.parseDouble(betField.getText())) {
      winLabel = new JLabel("BIG WIN!");
    } else {
      winLabel = new JLabel("You won!");
    }
    winLabel.setAlignmentX(CENTER_ALIGNMENT);
    winLabel.setForeground(Color.BLACK);

    JFrame winFrame = new JFrame();
    winFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    winFrame.setLayout(new BoxLayout(winFrame.getContentPane(), BoxLayout.Y_AXIS));
    winFrame.setUndecorated(true);
    winFrame.setBackground(new Color(0, 0, 0, 0));

    winFrame.add(winLabel);
    winFrame.setSize(400, 100);
    winFrame.setLocation(570, 400);
    winFrame.setVisible(true);

    final int[] fontSize = {0};
    Timer timer = new Timer(50, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        fontSize[0] += 5;
        winLabel.setFont(new Font("Arial Black", Font.BOLD, fontSize[0]));

        if (fontSize[0] >= 70) {
          ((Timer) e.getSource()).stop();
          Timer closeTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              winFrame.dispose();
            }
          });
          closeTimer.setRepeats(false);
          closeTimer.start();
        }
      }
    });
    timer.start();

  }


  private void updateUI(double wonAmount) {
    balanceLabel.setText("$" + currentBalance);
    wonLabel.setText("Won: $" + wonAmount);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new SlotGameFrame(1000.0, "Matyas"));
  }
  @Override
  public void dispose() {
    super.dispose();
    BackgroundMusic.getInstance().removeReference();
  }
}
