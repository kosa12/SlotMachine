import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class SlotMenuFrame extends JFrame {
  JPanel titlePanel;
  JLabel titleNameLabel;
  JLabel titleNameLabel2;
  Font titleFont1;

  {
    String fName = "fonts/SAIBA-45-Outline.ttf";
    try (InputStream is = SlotGameFrame.class.getResourceAsStream(fName)) {
      if (is == null) {
        throw new IOException("Font file not found: " + fName);
      }
      titleFont1 = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(138f);
    } catch (FontFormatException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  Font titleFont2;

  {
    String fName = "fonts/SAIBA-45.ttf";
    try (InputStream is = SlotGameFrame.class.getResourceAsStream(fName)) {
      if (is == null) {
        throw new IOException("Font file not found: " + fName);
      }
      titleFont2 = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(140f);
    } catch (FontFormatException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  JPanel buttonPanel;
  JButton startNewGameButton;
  JButton loadButton;
  JButton optionButton;
  JButton exitButton;
  Font buttonFont;

  {
    String fName = "fonts/SAIBA-45.ttf";
    try (InputStream is = SlotGameFrame.class.getResourceAsStream(fName)) {
      if (is == null) {
        throw new IOException("Font file not found: " + fName);
      }
      buttonFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(55f);
    } catch (FontFormatException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  Container con;

  public SlotMenuFrame() {
    BackgroundMusic.getInstance().addReference();
    setLayout(null);
    setSize(1200, 700);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getContentPane().setBackground(new Color(113, 28, 155));

    JLabel globeGif = new JLabel("", new ImageIcon(
        Objects.requireNonNull(getClass().getResource("src/gifs/XD.gif"))),  JLabel.CENTER);
    globeGif.setBounds(30,270,400,400);

    JLabel globeGif2 = new JLabel("", new ImageIcon(
        Objects.requireNonNull(getClass().getResource("src/gifs/XD2.gif"))),  JLabel.CENTER);
    globeGif2.setBounds(750,270,400,400);

    con = getContentPane();
    con.add(globeGif);
    con.add(globeGif2);

    titlePanel = new JPanel();
    titlePanel.setBounds(150, 20, 1200, 200);
    titlePanel.setBackground(new Color(113, 28, 155));

    titleNameLabel = new JLabel("CYBER SLOT");
    titleNameLabel.setForeground(new Color(234, 0, 217));
    titleNameLabel.setFont(titleFont1);

    titleNameLabel2 = new JLabel("CYBER SLOT");
    titleNameLabel2.setForeground(new Color(9, 24, 51));
    titleNameLabel2.setFont(titleFont2);

    titleNameLabel.setBounds(0, 0, 1100, 100);
    titleNameLabel2.setBounds(0, 0, 1100, 100);
    titlePanel.setLayout(null);

    buttonPanel = new JPanel();
    buttonPanel.setBounds(400, 220, 350, 400);
    buttonPanel.setBackground(new Color(113, 28, 155));

    startNewGameButton = new JButton("START");
    startNewGameButton.setFocusable(false);
    startNewGameButton.setBorderPainted(false);
    startNewGameButton.setOpaque(false);
    startNewGameButton.setContentAreaFilled(false);
    startNewGameButton.setBackground(new Color(113, 28, 155));
    startNewGameButton.setForeground(new Color(211, 189, 222));
    startNewGameButton.setFont(buttonFont);

    startNewGameButton.addActionListener(e -> {
      SwingUtilities.invokeLater(StartFrame::new);
      dispose();
    });

    loadButton = new JButton("LOAD");
    loadButton.setFocusable(false);
    loadButton.setBorderPainted(false);
    loadButton.setOpaque(false);
    loadButton.setContentAreaFilled(false);
    loadButton.setBackground(new Color(113, 28, 155));
    loadButton.setForeground(new Color(211, 189, 222));
    loadButton.setFont(buttonFont);

    loadButton.addActionListener(e -> {
      SwingUtilities.invokeLater(LoadFrame::new);
      dispose();
    });

    optionButton = new JButton("OPTIONS");
    optionButton.setFocusable(false);
    optionButton.setBorderPainted(false);
    optionButton.setOpaque(false);
    optionButton.setContentAreaFilled(false);
    optionButton.setBackground(new Color(113, 28, 155));
    optionButton.setForeground(new Color(211, 189, 222));
    optionButton.setFont(buttonFont);

    optionButton.addActionListener(e -> {
      SwingUtilities.invokeLater(OptionFrame::new);
      dispose();
    });

    exitButton = new JButton("EXIT");
    exitButton.setFocusable(false);
    exitButton.setBorderPainted(false);
    exitButton.setOpaque(false);
    exitButton.setContentAreaFilled(false);
    exitButton.setBackground(new Color(113, 28, 155));
    exitButton.setForeground(new Color(211, 189, 222));
    exitButton.setFont(buttonFont);

    exitButton.addActionListener(e -> {
      dispose();
      System.exit(0);
    });

    buttonPanel.setLayout(new GridLayout(4, 1, 0, 10));
    buttonPanel.add(startNewGameButton);
    buttonPanel.add(loadButton);
    buttonPanel.add(optionButton);
    buttonPanel.add(exitButton);

    titlePanel.add(titleNameLabel);
    titlePanel.add(titleNameLabel2);

    con.add(titlePanel);
    con.add(buttonPanel);
    setLocationRelativeTo(null);
    setVisible(true);
  }

  public static void main(String[] args) {
    new SlotMenuFrame();
  }

}
