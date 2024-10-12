import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {

    private JTextField display;
    private String operator;
    private double firstNumber, secondNumber, result;

    // Constructor
    public Calculator() {
        // Create window
        setTitle("Windows-like Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Display field
        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        add(display, BorderLayout.NORTH);

        // Button panel with GridLayout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 5, 5));

        // Button labels
        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "C", // Clear button
        };

        // Add buttons to panel
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.PLAIN, 18));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        // Add the panel to the frame
        add(buttonPanel, BorderLayout.CENTER);

        // Set the frame to be visible
        setVisible(true);
    }

    // Handle button events
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9' || command.equals(".")) {
            display.setText(display.getText() + command);
        } else if (command.equals("=")) {
            secondNumber = Double.parseDouble(display.getText());
            switch (operator) {
                case "+" -> result = firstNumber + secondNumber;
                case "-" -> result = firstNumber - secondNumber;
                case "*" -> result = firstNumber * secondNumber;
                case "/" -> result = firstNumber / secondNumber;
            }
            display.setText(String.valueOf(result));
        } else if (command.equals("C")) {  // Clear the display
            display.setText("");
        } else {
            firstNumber = Double.parseDouble(display.getText());
            operator = command;
            display.setText("");
        }
    }
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Calculator();
    }
}