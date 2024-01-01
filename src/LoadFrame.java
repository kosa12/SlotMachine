import java.io.File;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadFrame extends JFrame {
  Container con;
  Font buttonFont;
  Font textFontOutLine;
  Font textFont;

  {
    try {
      buttonFont = Font.createFont(Font.TRUETYPE_FONT, new File("font/SAIBA-45.ttf")).deriveFont(60f);
      textFont = Font.createFont(Font.TRUETYPE_FONT, new File("font/SAIBA-45.ttf")).deriveFont(50f);
      textFontOutLine = Font.createFont(Font.TRUETYPE_FONT, new File("font/SAIBA-45-Outline.ttf")).deriveFont(49f);
    } catch (FontFormatException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  public LoadFrame() {
    setSize(1200, 700);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    getContentPane().setBackground(new Color(113, 28, 155));
    setLayout(null);
    con = getContentPane();

    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Name");
    model.addColumn("Balance");

    List<String[]> saveData = readSaveData();
    for (String[] data : saveData) {
      model.addRow(data);
    }

    JTable table = new JTable(model);
    table.setDefaultEditor(Object.class, null);

    JButton backButton = new JButton("Back");
    backButton.setFont(buttonFont);
    backButton.setBorderPainted(false);
    backButton.setForeground(new Color(9, 24, 51));
    backButton.setBackground(new Color(113, 28, 155));
    backButton.setFocusable(false);
    backButton.setBounds(750, 575, 300, 70);

    JTextField saveNameField = new JTextField(10);
    saveNameField.setBackground(new Color(211, 153, 240));
    saveNameField.setBounds(620, 110, 500, 50);
    saveNameField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
    saveNameField.setFont(textFont);

    JButton loadButton = new JButton("Load");
    loadButton.setFont(buttonFont);
    loadButton.setBorderPainted(false);
    loadButton.setForeground(new Color(9, 24, 51));
    loadButton.setBackground(new Color(113, 28, 155));
    loadButton.setFocusable(false);
    loadButton.setBounds(150, 575, 300, 70);

    JTextArea namesTextArea = new JTextArea();
    namesTextArea.setFont(textFontOutLine);
    namesTextArea.setForeground(Color.WHITE);
    namesTextArea.setEditable(false);
    namesTextArea.setBackground(new Color(113, 28, 155));
    namesTextArea.setBorder(null);
    JScrollPane scrollPane = new JScrollPane(namesTextArea);
    scrollPane.setBorder(null);
    scrollPane.setBounds(100, 250, 500, 250);


    loadButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String inputName = saveNameField.getText();
        boolean isValidSave = saveData.stream().anyMatch(data -> data[0].equals(inputName));

        if (isValidSave) {
          double loadedBalance = getBalanceForName(inputName);
          System.out.println("Loading game with name: " + inputName + " and balance: " + loadedBalance);

          dispose();
          SwingUtilities.invokeLater(() -> new SlotGameFrame(loadedBalance, inputName));
        } else {
          JOptionPane.showMessageDialog(LoadFrame.this, "Invalid name. Please enter your valid name.");
        }
      }
    });

    backButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        dispose();
        SwingUtilities.invokeLater(SlotMenuFrame::new);
      }
    });

    JLabel nameLabel = new JLabel("Enter your name: ");
    nameLabel.setFont(textFont);
    nameLabel.setBounds(50, 100, 900, 80);
    nameLabel.setForeground(new Color(234, 0, 217));

    JLabel nameLabel2 = new JLabel("Enter your name: ");
    nameLabel2.setFont(textFontOutLine);
    nameLabel2.setBounds(50, 100, 900, 80);
    nameLabel2.setForeground(Color.WHITE);

    JLabel savedNames = new JLabel("Saved Names:");
    savedNames.setFont(textFont);
    savedNames.setBounds(50, 170, 900, 80);
    savedNames.setForeground(new Color(211, 153, 240));


    StringBuilder namesList = new StringBuilder();
    for (String[] data : saveData) {
      namesList.append("-").append(data[0]).append("\n");
    }
    namesTextArea.setText(namesList.toString());

    setLocationRelativeTo(null);
    con.add(nameLabel2);
    con.add(nameLabel);
    con.add(savedNames);
    con.add(saveNameField);
    con.add(backButton);
    con.add(loadButton);
    con.add(scrollPane);
    setVisible(true);
  }

  private double getBalanceForName(String playerName) {
    for (String[] data : readSaveData()) {
      if (data[0].equals(playerName)) {
        return Double.parseDouble(data[1]);
      }
    }
    return 0.0;
  }

  private List<String[]> readSaveData() {
    List<String[]> saveData = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader("log/player.txt"))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] parts = line.split(", ");
        if (parts.length >= 2) {
          String name = parts[0];
          String balance = parts[1];
          saveData.add(new String[]{name, balance});
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return saveData;
  }

  public static void main(String[] args) {
    new LoadFrame();
  }
}
