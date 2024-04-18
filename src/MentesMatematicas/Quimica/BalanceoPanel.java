package MentesMatematicas.Quimica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BalanceoPanel extends JPanel {

    private JTextField txtEcuacion;
    private JLabel lblResultado;

    private List<Unit> reactants = new ArrayList<>();
    private List<Unit> products = new ArrayList<>();
    private Set<String> elementNames = new HashSet<>();
    private double[][] system;
    private double[] variables;

    public BalanceoPanel() {
        setLayout(null);
        setPreferredSize(new Dimension(980, 700));

        JLabel lblTitulo = new JLabel("Balanceo de Ecuaciones");
        lblTitulo.setFont(new Font("Yu Gothic UI", Font.BOLD, 40));
        lblTitulo.setBounds(240, 20, 500, 50);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo);

        JLabel lblEcuacion = new JLabel("Ecuación:");
        lblEcuacion.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        lblEcuacion.setBounds(240, 100, 120, 40);
        add(lblEcuacion);

        txtEcuacion = new JTextField();
        txtEcuacion.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        txtEcuacion.setBounds(370, 100, 360, 40);
        add(txtEcuacion);

        JButton btnBalancear = new JButton("Balancear");
        btnBalancear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                balancearEcuacion();
            }
        });
        btnBalancear.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        btnBalancear.setBackground(Color.decode("#2D142C"));
        btnBalancear.setForeground(Color.WHITE);
        btnBalancear.setFocusPainted(false);
        btnBalancear.setBounds(240, 160, 500, 40);
        add(btnBalancear);

        lblResultado = new JLabel("");
        lblResultado.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
        lblResultado.setBounds(240, 220, 500, 200);
        add(lblResultado);
    }

    private void balancearEcuacion() {
        String ecuacion = txtEcuacion.getText().replaceAll("\\s+", "");
        parseEquationString(ecuacion);
        createEquationsSystem();
        solveEquations();

        if (solveVariables()) {
            displayBalancedEquation();
        } else {
            lblResultado.setText("No se pudo balancear la ecuación con enteros positivos.");
        }
    }

    private void parseEquationString(String equationString) {
        reactants.clear();
        products.clear();

        String s = equationString.replaceAll("\\s+", "");

        String[] halves = s.split("=");

        for (int i = 0; i < halves.length; i++) {
            String[] smolecules = halves[i].split("\\+");

            for (String str : smolecules) {
                if (i == 0) {
                    reactants.add(parseCompoundString(str));
                } else {
                    products.add(parseCompoundString(str));
                }
            }
        }
    }

    private Compound parseCompoundString(String s) {
        Compound compound = new Compound();

        int i = 0;

        while (i < s.length()) {
            if (Character.isUpperCase(s.charAt(i))) {
                String elementName = s.charAt(i) + "";
                i++;
                while (i < s.length() && Character.isLowerCase(s.charAt(i))) {
                    elementName += s.charAt(i);
                    i++;
                }

                String stringCoefficient = "";

                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    stringCoefficient += s.charAt(i);
                    i++;
                }

                int coefficient = 1;

                if (stringCoefficient.length() > 0) {
                    coefficient = Integer.parseInt(stringCoefficient);
                }

                Element element = new Element(elementName, coefficient);
                compound.addSubUnit(element);
                elementNames.add(elementName);
            } else if (s.charAt(i) == '(') {
                int end = s.lastIndexOf(")");
                Compound subCompound = parseCompoundString(s.substring(i + 1, end));
                i = end + 1;

                String stringCoefficient = "";

                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    stringCoefficient += s.charAt(i);
                    i++;
                }

                int coefficient = 1;

                if (stringCoefficient.length() > 0) {
                    coefficient = Integer.parseInt(stringCoefficient);
                }

                subCompound.setCoefficient(coefficient);
                compound.addSubUnit(subCompound);
            }
        }
        return compound;
    }

    private void createEquationsSystem() {
        system = new double[elementNames.size()][reactants.size() + products.size() + 1];

        for (int i = 0; i < system.length; i++) {
            for (int j = 0; j < system[0].length - 1; j++) {
                if (j < reactants.size()) {
                    system[i][j] = reactants.get(j).getElementCount(getElementNameByIndex(i));
                } else {
                    system[i][j] = -products.get(j - reactants.size()).getElementCount(getElementNameByIndex(i));
                }
            }
            system[i][system[0].length - 1] = 0;
        }
    }

    private void solveEquations() {
        for (int i = 0; i < system.length && i < system[0].length - 1; i++) {
            if (system[i][i] == 0) {
                boolean found = false;
                for (int j = i + 1; j < system.length; j++) {
                    if (system[j][i] != 0) {
                        for (int k = 0; k < system[0].length; k++) {
                            system[i][k] += system[j][k];
                        }
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    continue;
                }
            }
            for (int i2 = i + 1; i2 < system.length; i2++) {
                double n = system[i2][i];
                if (n == 0) {
                    continue;
                }
                for (int j = 0; j < system[i2].length; j++) {
                    system[i2][j] *= system[i][i];
                }
                for (int j = 0; j < system[0].length; j++) {
                    system[i2][j] -= system[i][j] * n;
                }
            }
        }
    }

    private boolean solveVariables() {
        variables = new double[system[0].length - 1];

        int defaultValue = 1;

        valueLoop:
        while (true) {
            for (int i = variables.length - 1; i > -1; i--) {
                if (i > system.length - 1 || system[i][i] == 0) {
                    variables[i] = defaultValue;
                } else {
                    variables[i] = system[i][variables.length];
                    for (int j = i + 1; j < variables.length; j++) {
                        variables[i] -= variables[j] * system[i][j];
                    }
                    variables[i] /= system[i][i];
                    if (variables[i] < 0) {
                        return false;
                    }
                    if (variables[i] % 1 != 0) {
                        defaultValue++;
                        continue valueLoop;
                    }
                }
            }
            break;
        }

        return true;
    }

    private void displayBalancedEquation() {
        int variableNumber = 0;
        DecimalFormat df = new DecimalFormat("#.######");
        StringBuilder equation = new StringBuilder();
        for (int i = 0; i < reactants.size(); i++) {
            if (variables[variableNumber] != 1) {
                equation.append(df.format(variables[variableNumber]));
            }
            variableNumber++;
            equation.append(reactants.get(i).getHtml());
            if (i < reactants.size() - 1) {
                equation.append(" + ");
            }
        }

        equation.append(" ⇌ ");

        for (int i = 0; i < products.size(); i++) {
            if (variables[variableNumber] != 1) {
                equation.append(df.format(variables[variableNumber]));
            }
            variableNumber++;
            equation.append(products.get(i).getHtml());
            if (i < products.size() - 1) {
                equation.append(" + ");
            }
        }

        lblResultado.setText(equation.toString());
    }

    private String getElementNameByIndex(int index) {
        return elementNames.toArray(new String[0])[index];
    }
}
