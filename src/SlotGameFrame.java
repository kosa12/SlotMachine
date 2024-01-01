import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class SlotGameFrame extends JFrame {
  private final SymbolPanel symbolPanel;
  private final JLabel balanceLabel;
  private final JLabel nameLabel;
  private final JTextField betField;

  public SlotGameFrame(double balance, String name) {
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

    balanceLabel = new JLabel("$" + balance);
    balanceLabel.setForeground(Color.WHITE);
    balanceLabel.setBounds(1000, 600, 200, 30);
    con.add(balanceLabel);

    nameLabel = new JLabel(name);
    nameLabel.setForeground(Color.WHITE);
    nameLabel.setBounds(50, 50, 200, 30);
    con.add(nameLabel);

    betField = new JTextField();
    betField.setBounds(50, 550, 200, 30);
    betField.setBackground(new Color(211, 153, 240));
    betField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
    con.add(betField);

    JButton shuffleButton = new JButton("Shuffle");
    shuffleButton.setBounds(50, 600, 200, 30);
    shuffleButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        handleBet();
      }
    });
    con.add(shuffleButton);

    setLocationRelativeTo(null);
    setVisible(true);
  }

  public void updateBalance(double newBalance) {
    balanceLabel.setText("Balance: $" + newBalance);
  }

  private void handleBet() {
    try {
      double betAmount = Double.parseDouble(betField.getText());
      if (betAmount >= 0) {
        //TODO :: game logic
        symbolPanel.startShuffle();
      } else {
        JOptionPane.showMessageDialog(this, "Invalid bet amount. Please enter a non-negative number.");
      }
    } catch (NumberFormatException ex) {
      JOptionPane.showMessageDialog(this, "Invalid bet amount. Please enter a valid number.");
    }
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new SlotGameFrame(1000.0, "Matyas"));
  }
}
