import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class StartFrame extends JFrame {
  JTextField nameField;
  JTextField balanceField;

  Font textFont;
  Font textFontOutLine;
  Font buttonFont;

  Container con;

  {
    try {
      textFont = Font.createFont(Font.TRUETYPE_FONT, new File("font/SAIBA-45.ttf")).deriveFont(40f);
      textFontOutLine = Font.createFont(Font.TRUETYPE_FONT, new File("font/SAIBA-45-Outline.ttf")).deriveFont(39f);
      buttonFont = Font.createFont(Font.TRUETYPE_FONT, new File("font/SAIBA-45.ttf")).deriveFont(60f);
    } catch (FontFormatException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  public StartFrame() {
    setSize(1200, 700);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getContentPane().setBackground(new Color(113, 28, 155));
    setLayout(null);
    con = getContentPane();

    JLabel nameLabel = new JLabel("Enter Player Name:");
    nameLabel.setBounds(50, 150, 500, 50);
    nameLabel.setForeground(new Color(234, 0, 217));
    nameLabel.setFont(textFont);

    JLabel nameLabel2 = new JLabel("Enter Player Name:");
    nameLabel2.setBounds(50, 150, 900, 50);
    nameLabel2.setForeground(Color.WHITE);
    nameLabel2.setFont(textFontOutLine);

    nameField = new JTextField();
    nameField.setBackground(new Color(211, 153, 240));
    nameField.setBounds(650, 150, 500, 50);
    nameField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
    nameField.setFont(textFont);

    JLabel balanceLabel = new JLabel("Enter Starting Balance:");
    balanceLabel.setBounds(20, 250, 600, 50);
    balanceLabel.setForeground(new Color(234, 0, 217));
    balanceLabel.setFont(textFont);

    JLabel balanceLabel2 = new JLabel("Enter Starting Balance:");
    balanceLabel2.setBounds(20, 250, 600, 50);
    balanceLabel2.setForeground(Color.WHITE);
    balanceLabel2.setFont(textFontOutLine);

    balanceField = new JTextField();
    balanceField.setBackground(new Color(211, 153, 240));
    balanceField.setBounds(650, 250, 500, 50);
    balanceField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
    balanceField.setFont(textFont);

    JButton enterButton = new JButton("Enter");
    enterButton.setFont(buttonFont);
    enterButton.setBorderPainted(false);
    enterButton.setForeground(new Color(9, 24, 51));
    enterButton.setBackground(new Color(113, 28, 155));
    enterButton.setFocusable(false);
    enterButton.setBounds(150, 550, 300, 70);

    enterButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        LogInfo();
      }
    });

    JButton backButton = new JButton("Back");
    backButton.setFont(buttonFont);
    backButton.setBorderPainted(false);
    backButton.setForeground(new Color(9, 24, 51));
    backButton.setBackground(new Color(113, 28, 155));
    backButton.setFocusable(false);
    backButton.setBounds(750, 550, 300, 70);

    backButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        dispose();
        SwingUtilities.invokeLater(SlotMenuFrame::new);
      }
    });

    con.add(nameLabel2);
    con.add(nameLabel);
    con.add(nameField);
    con.add(balanceLabel2);
    con.add(balanceLabel);
    con.add(balanceField);
    con.add(enterButton);
    con.add(backButton);

    setLocationRelativeTo(null);
    setVisible(true);
  }

  private void LogInfo() {
    String name = nameField.getText();
    String balance = balanceField.getText();

    if (name.isEmpty() || balance.isEmpty()) {
      JOptionPane.showMessageDialog(this, "All fields must be completed.");
      return;
    }

    try {
      double balanceDouble = Double.parseDouble(balance);

      if (balanceDouble <= 0) {
        JOptionPane.showMessageDialog(this, "The balance should be greater than 0!");
        return;
      }

      try (PrintWriter writer = new PrintWriter(new FileWriter("player.txt", true))) {
        writer.println(name + ", " + String.format("%.3f", balanceDouble));
      } catch (IOException ex) {
        JOptionPane.showMessageDialog(this, "Error saving player information.");
      }

      JOptionPane.showMessageDialog(this, "Player information saved successfully.");
      new SlotGameFrame(balanceDouble, name);
      dispose();

    } catch (NumberFormatException ex) {
      JOptionPane.showMessageDialog(this, "Please enter a valid numeric value for the balance.");
    }


  }
}
