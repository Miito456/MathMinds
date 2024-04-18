package MentesMatematicas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class CalculatorPanel extends JPanel {

    private JTextField display;
    private JButton[] buttons;
    private String[] buttonLabels = {
        "(", ")", "DEL", "C",
        "7", "8", "9", "/",
        "4", "5", "6", "*",
        "1", "2", "3", "-",
        "0", ".", "=", "+"
    };
    private boolean startOfNumber = true;
    private boolean calculated = false;
    private double previousResult = 0;
    private char lastOperator = ' ';

    public CalculatorPanel() {
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

        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 10, 10));
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
                    display.setText(previousResult + input);
                    calculated = false;
                    startOfNumber = false;
                } else {
                    String currentText = display.getText();
                    if (startOfNumber) {
                        if (!currentText.isEmpty() && Character.isDigit(currentText.charAt(currentText.length() - 1))) {
                        } else {
                            display.setText(input);
                        }
                        startOfNumber = false;
                    } else {
                        display.setText(display.getText() + input);
                    }
                }
            }
        }

        private void calculate() {
            String text = display.getText();
            String result;
            try {
                result = evaluateExpression(text);
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
            startOfNumber = true;
        }

        private void clear() {
            display.setText("");
            calculated = false;
            startOfNumber = true;
        }

        private void delete() {
            String text = display.getText();
            if (!text.isEmpty()) {
                display.setText(text.substring(0, text.length() - 1));
            }
        }

        private int precedence(char op) {
            switch (op) {
                case '+':
                case '-':
                    return 1;
                case '*':
                case '/':
                    return 2;
                default:
                    return -1;
            }
        }

        private String evaluateExpression(String expression) {
            StringBuilder result = new StringBuilder();
            Stack<Character> operatorStack = new Stack<>();
            Stack<Double> operandStack = new Stack<>();
            for (int i = 0; i < expression.length(); i++) {
                char c = expression.charAt(i);
                if (Character.isDigit(c) || c == '.') {
                    StringBuilder operand = new StringBuilder();
                    while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                        operand.append(expression.charAt(i++));
                    }
                    i--;
                    operandStack.push(Double.parseDouble(operand.toString()));
                } else if (c == '(') {
                    operatorStack.push(c);
                } else if (c == ')') {
                    while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                        char operator = operatorStack.pop();
                        double operand2 = operandStack.pop();
                        double operand1 = operandStack.pop();
                        operandStack.push(applyOperation(operator, operand1, operand2));
                    }
                    operatorStack.pop();
                } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                    while (!operatorStack.isEmpty() && precedence(c) <= precedence(operatorStack.peek())) {
                        char operator = operatorStack.pop();
                        double operand2 = operandStack.pop();
                        double operand1 = operandStack.pop();
                        operandStack.push(applyOperation(operator, operand1, operand2));
                    }
                    operatorStack.push(c);
                }
            }
            while (!operatorStack.isEmpty()) {
                char operator = operatorStack.pop();
                double operand2 = operandStack.pop();
                double operand1 = operandStack.pop();
                operandStack.push(applyOperation(operator, operand1, operand2));
            }
            return String.valueOf(operandStack.pop());
        }

        private double applyOperation(char operator, double operand1, double operand2) {
            switch (operator) {
                case '+':
                    return operand1 + operand2;
                case '-':
                    return operand1 - operand2;
                case '*':
                    return operand1 * operand2;
                case '/':
                    if (operand2 == 0) {
                        throw new ArithmeticException("División por cero");
                    }
                    return operand1 / operand2;
                default:
                    throw new IllegalArgumentException("Invalid operator: " + operator);
            }
        }
    }

}
