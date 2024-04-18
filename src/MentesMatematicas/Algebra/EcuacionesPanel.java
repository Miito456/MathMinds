package MentesMatematicas.Algebra;

import MentesMatematicas.CalculatorPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class EcuacionesPanel extends JPanel {

    private JTextField display;
    private JButton[] buttons;
    private String[] buttonLabels = {
            "x", "(", ")", "DEL", "C",
            "y", "7", "8", "9", "/",
            "sen()", "4", "5", "6", "*",
            "cos()", "1", "2", "3", "-",
            "log()", "0", ".", "=", "+"
    };
    private boolean startOfNumber = true;
    private boolean calculated = false; 
    private double previousResult = 0;

    public EcuacionesPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Calculadora Simple");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
        add(display, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 5, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        buttons = new JButton[buttonLabels.length];
        ButtonListener listener = new ButtonListener();

        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
            buttons[i].setPreferredSize(new Dimension(50, 50));
            buttons[i].setMargin(new Insets(0, 0, 0, 0));
            buttons[i].setBackground(Color.WHITE);
            buttons[i].setForeground(Color.BLACK);
            buttons[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            buttons[i].setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            buttons[i].addActionListener(listener);
            buttons[i].setFocusPainted(false);
            buttons[i].setOpaque(true);
            buttons[i].setBorderPainted(false);
            buttons[i].setFocusable(false);
            buttons[i].setContentAreaFilled(true);
            buttonPanel.add(buttons[i]);
        }

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            String input = event.getActionCommand();

            if (input.equals("=")) {
                calculate();
            } else if (input.equals("C")) {
                clear();
            } else if (input.equals("DEL")) {
                delete();
            } else {
                if (calculated && (Character.isDigit(input.charAt(0)) || input.equals("."))) {
                    display.setText("");
                    calculated = false;
                }
                display.setText(display.getText() + input);
            }
        }

        private void calculate() {
            String text = display.getText();
            String result;
            try {
                result = solveEquation(text);
                previousResult = Double.parseDouble(result);
                display.setText(result);
            } catch (ArithmeticException e) {
                display.setText("Error: División por cero");
            } catch (IllegalArgumentException e) {
                display.setText("Error: Operador inválido");
            } catch (Exception e) {
                display.setText("Error: Expresión inválida");
            }
            calculated = true; 
        }

        private void clear() {
            display.setText("");
            calculated = false; 
        }

        private void delete() {
            String text = display.getText();
            if (!text.isEmpty()) {
                display.setText(text.substring(0, text.length() - 1));
            }
        }

        private String solveEquation(String equation) {
            return "Error: Resolución de ecuaciones no implementada";
        }
    }

}
