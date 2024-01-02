import java.awt.Color;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;
import symbols.Symbol;

public class SlotGameFrame extends JFrame {

  private final SymbolPanel symbolPanel;
  private final JLabel balanceLabel;
  private final JLabel nameLabel;
  private final JTextField betField;
  private final JButton shuffleButton;
  private double currentBalance;

  public SlotGameFrame(double balance, String name) {
    currentBalance = balance;

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

    balanceLabel = new JLabel("$" + currentBalance);
    balanceLabel.setForeground(Color.WHITE);
    balanceLabel.setBounds(1000, 600, 200, 30);
    con.add(balanceLabel);

    nameLabel = new JLabel(name);
    nameLabel.setForeground(Color.WHITE);
    nameLabel.setBounds(50, 50, 200, 30);
    con.add(nameLabel);

    betField = new JTextField();
    betField.setBounds(50, 550, 200, 30);
    con.add(betField);

    shuffleButton = new JButton("Shuffle");
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

    if (SlotGameLogic.isWin(spinResult)) {
      double payout = SlotGameLogic.calculatePayout(spinResult, betAmount);
      currentBalance = SlotGameLogic.updateBalance(currentBalance, betAmount, payout);

      showWinDialog(payout, betAmount);
    } else {
      currentBalance -= betAmount;
    }

    updateUI();
  }

  private void showWinDialog(double payout, double betamount) {
    JDialog winDialog = new JDialog(this, "Win!!", Dialog.ModalityType.MODELESS);
    winDialog.setLayout(new BoxLayout(winDialog.getContentPane(), BoxLayout.Y_AXIS));

    JLabel winLabel;

    if(payout >= 2 * betamount){
      winLabel = new JLabel("Congratulations! BIG WIN!");
    }
    else{
      winLabel = new JLabel("Congratulations! You won!");
    }

    winLabel.setAlignmentX(CENTER_ALIGNMENT);

    winDialog.add(Box.createRigidArea(new Dimension(0, 20)));
    winDialog.add(winLabel);

    winDialog.setBounds(1100, 400, 200, 100);
    winDialog.setVisible(true);
  }

  private void updateUI() {
    balanceLabel.setText("Balance: $" + currentBalance);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new SlotGameFrame(1000.0, "Matyas"));
  }
}
