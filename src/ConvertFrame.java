import javax.swing.*;
import java.awt.*;

public class ConvertFrame extends JFrame {
    private JTextField currencyInput;
    private JTextField currencyOutput;
    private JRadioButton usdFrom, euroFrom, pesoFrom;
    private JRadioButton usdTo, euroTo, pesoTo;

    public ConvertFrame() {
        setTitle("Currency Converter");
        setLayout(new GridLayout(9, 1));
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem convertItem = new JMenuItem("Convert");
        convertItem.addActionListener(e -> convertCurrency());

        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e -> showAboutDialog());

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(convertItem);
        fileMenu.add(aboutItem);
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        JPanel fromPanel = new JPanel(new GridLayout(1, 3));
        usdFrom = new JRadioButton("USD", true);
        euroFrom = new JRadioButton("Euro");
        pesoFrom = new JRadioButton("Peso");
        ButtonGroup fromGroup = new ButtonGroup();
        fromGroup.add(usdFrom);
        fromGroup.add(euroFrom);
        fromGroup.add(pesoFrom);

        fromPanel.add(createCurrencyPanel("/Lab_4_images/dollar.jpg", usdFrom));
        fromPanel.add(createCurrencyPanel("/Lab_4_images/euro.jpg", euroFrom));
        fromPanel.add(createCurrencyPanel("/Lab_4_images/peso.jpg", pesoFrom));

        JPanel toPanel = new JPanel(new GridLayout(1, 3));
        usdTo = new JRadioButton("USD", true);
        euroTo = new JRadioButton("Euro");
        pesoTo = new JRadioButton("Peso");
        ButtonGroup toGroup = new ButtonGroup();
        toGroup.add(usdTo);
        toGroup.add(euroTo);
        toGroup.add(pesoTo);

        toPanel.add(createCurrencyPanel("/Lab_4_images/dollar.jpg", usdTo));
        toPanel.add(createCurrencyPanel("/Lab_4_images/euro.jpg", euroTo));
        toPanel.add(createCurrencyPanel("/Lab_4_images/peso.jpg", pesoTo));

        JLabel inputLabel = new JLabel("Enter Amount:");
        currencyInput = new JTextField();

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(inputLabel, BorderLayout.NORTH);
        inputPanel.add(currencyInput, BorderLayout.CENTER);

        JLabel outputLabel = new JLabel("Converted Amount:");
        currencyOutput = new JTextField();
        currencyOutput.setEditable(false);

        JButton convertButton = new JButton("Convert");
        JButton clearButton = new JButton("Clear");
        JButton exitButton = new JButton("Exit");

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(convertButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(exitButton);
        add(new JLabel("From Currency:"));
        add(fromPanel);
        add(inputPanel);
        add(new JLabel("To Currency:"));
        add(toPanel);
        add(outputLabel);
        add(currencyOutput);
        add(buttonPanel);

        convertButton.addActionListener(e -> convertCurrency());
        clearButton.addActionListener(e -> clearFields());
        exitButton.addActionListener(e -> System.exit(0));
    }

    private JPanel createCurrencyPanel(String imagePath, JRadioButton radioButton) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel imageLabel = new JLabel(new ImageIcon(getClass().getResource(imagePath)));
        panel.add(imageLabel, BorderLayout.CENTER);
        panel.add(radioButton, BorderLayout.SOUTH);
        return panel;
    }

    private void convertCurrency() {
        String inputText = currencyInput.getText();
        try {
            double amount = Double.parseDouble(inputText);
            double conversionRate = 1.06;
            if (usdFrom.isSelected() && euroTo.isSelected()) conversionRate = 0.94;
            else if (usdFrom.isSelected() && pesoTo.isSelected()) conversionRate = 20.58;
            double convertedAmount = amount * conversionRate;
            currencyOutput.setText(String.format("%.2f", convertedAmount));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number");
        }
    }

    private void clearFields() {
        currencyInput.setText("");
        currencyOutput.setText("");
    }

    private void showAboutDialog() {
        JOptionPane.showMessageDialog(this, "Currency Converter App\nVersion 1.0\nBy Manuel Gomez",
                "About", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ConvertFrame frame = new ConvertFrame();
            frame.setVisible(true);
        });
    }
}
