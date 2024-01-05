import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class OptionFrame extends JFrame {
  private final JSlider volumeSlider;

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

  public OptionFrame() {
    getContentPane().setBackground(new Color(113, 28, 155));
    setTitle("Options");
    setSize(1200, 700);
    setLayout(null);

    volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, (int) (BackgroundMusic.getInstance().getVolume() * 100));
    volumeSlider.setBounds(300, 350, 600, 50);
    volumeSlider.setMajorTickSpacing(10);
    volumeSlider.setPaintTicks(true);
    volumeSlider.setPaintLabels(true);
    volumeSlider.setBackground(new Color(113, 28, 155));

    JButton applyButton = new JButton("Apply Changes");
    applyButton.setBounds(0, 600, 550, 50);
    applyButton.setFont(buttonFont);
    applyButton.setBackground(new Color(113, 28, 155));
    applyButton.setForeground(new Color(211, 189, 222));
    applyButton.setFocusable(false);
    applyButton.setBorderPainted(false);
    applyButton.setOpaque(false);
    applyButton.setContentAreaFilled(false);

    applyButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        applyOptions();
      }
    });

    JButton backButton = new JButton("Back");
    backButton.setBounds(950, 600, 200, 50);
    backButton.setFont(buttonFont);
    backButton.setBackground(new Color(113, 28, 155));
    backButton.setForeground(new Color(211, 189, 222));
    backButton.setFocusable(false);
    backButton.setBorderPainted(false);
    backButton.setOpaque(false);
    backButton.setContentAreaFilled(false);

    backButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        dispose();
        SwingUtilities.invokeLater(SlotMenuFrame::new);
      }
    });

    JLabel volume = new JLabel("Volume:");
    volume.setBounds(400, 100, 400, 50);
    volume.setHorizontalAlignment(SwingConstants.CENTER);
    volume.setFont(buttonFont);
    volume.setBackground(new Color(113, 28, 155));
    volume.setForeground(new Color(211, 189, 222));


    add(volume);
    add(volumeSlider);
    add(applyButton);
    add(backButton);

    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    setVisible(true);
  }

  private void applyOptions() {
    int volumeValue = volumeSlider.getValue();
    float normalizedVolume = volumeValue / 100f;
    BackgroundMusic.getInstance().setVolume(normalizedVolume);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(OptionFrame::new);
  }
}
