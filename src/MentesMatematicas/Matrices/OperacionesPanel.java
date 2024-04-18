package MentesMatematicas.Matrices;

import Components.NumericTextField;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OperacionesPanel extends JPanel {

    private JComboBox<Integer> comboBoxFilasA;
    private JComboBox<Integer> comboBoxColumnasA;
    private JComboBox<Integer> comboBoxFilasB;
    private JComboBox<Integer> comboBoxColumnasB;
    private JTextField[][] matrizAFields;
    private JTextField[][] matrizBFields;
    private JButton btnSuma;
    private JButton btnResta;
    private JButton btnMultiplicacion;
    private JTextArea txtResultado;

    public OperacionesPanel() {
        setLayout(null);

        setPreferredSize(new Dimension(980, 700));

        JLabel lblTitulo = new JLabel("Operaciones entre matrices");
        lblTitulo.setFont(new Font("Yu Gothic UI", Font.BOLD, 24));
        lblTitulo.setBounds(0, 20, 980, 30);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo);

        JLabel lblMatrizA = new JLabel("Matriz A:");
        lblMatrizA.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
        lblMatrizA.setBounds(50, 70, 400, 30);
        add(lblMatrizA);

        JLabel lblFilasA = new JLabel("Filas:");
        lblFilasA.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        lblFilasA.setBounds(50, 110, 60, 30);
        add(lblFilasA);

        JLabel lblColumnasA = new JLabel("Columnas:");
        lblColumnasA.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        lblColumnasA.setBounds(200, 110, 80, 30);
        add(lblColumnasA);

        comboBoxFilasA = new JComboBox<>();
        comboBoxFilasA.setBackground(Color.decode("#ececec"));
        comboBoxFilasA.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        comboBoxFilasA.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        comboBoxFilasA.setBounds(110, 110, 50, 30);
        for (int i = 2; i <= 5; i++) {
            comboBoxFilasA.addItem(i);
        }
        add(comboBoxFilasA);

        comboBoxColumnasA = new JComboBox<>();
        comboBoxColumnasA.setBackground(Color.decode("#ececec"));
        comboBoxColumnasA.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        comboBoxColumnasA.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        comboBoxColumnasA.setBounds(280, 110, 50, 30);
        for (int i = 2; i <= 5; i++) {
            comboBoxColumnasA.addItem(i);
        }
        add(comboBoxColumnasA);

        JLabel lblMatrizB = new JLabel("Matriz B:");
        lblMatrizB.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
        lblMatrizB.setBounds(530, 70, 400, 30);
        add(lblMatrizB);

        JLabel lblFilasB = new JLabel("Filas:");
        lblFilasB.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        lblFilasB.setBounds(530, 110, 60, 30);
        add(lblFilasB);

        JLabel lblColumnasB = new JLabel("Columnas:");
        lblColumnasB.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        lblColumnasB.setBounds(680, 110, 80, 30);
        add(lblColumnasB);

        comboBoxFilasB = new JComboBox<>();
        comboBoxFilasB.setBackground(Color.decode("#ececec"));
        comboBoxFilasB.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        comboBoxFilasB.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        comboBoxFilasB.setBounds(590, 110, 50, 30);
        for (int i = 2; i <= 5; i++) {
            comboBoxFilasB.addItem(i);
        }
        add(comboBoxFilasB);

        comboBoxColumnasB = new JComboBox<>();
        comboBoxColumnasB.setBackground(Color.decode("#ececec"));
        comboBoxColumnasB.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        comboBoxColumnasB.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        comboBoxColumnasB.setBounds(760, 110, 50, 30);
        for (int i = 2; i <= 5; i++) {
            comboBoxColumnasB.addItem(i);
        }
        add(comboBoxColumnasB);

        JPanel buttonsPanel = new JPanel(new GridLayout(1, 3, 10, 10)); 
        buttonsPanel.setBounds(50, 400, 880, 50);
        add(buttonsPanel);

        btnSuma = new JButton("Suma");
        btnSuma.setBackground(Color.decode("#2D142C"));
        btnSuma.setForeground(Color.WHITE);
        btnSuma.setFocusPainted(false);
        btnSuma.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        buttonsPanel.add(btnSuma);

        btnResta = new JButton("Resta");
        btnResta.setBackground(Color.decode("#2D142C"));
        btnResta.setForeground(Color.WHITE);
        btnResta.setFocusPainted(false);
        btnResta.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        buttonsPanel.add(btnResta);

        btnMultiplicacion = new JButton("Multiplicación");
        btnMultiplicacion.setBackground(Color.decode("#2D142C"));
        btnMultiplicacion.setForeground(Color.WHITE);
        btnMultiplicacion.setFocusPainted(false);
        btnMultiplicacion.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        buttonsPanel.add(btnMultiplicacion);

        txtResultado = new JTextArea();
        txtResultado.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(txtResultado);
        scrollPane.setBounds(50, 470, 880, 170);
        txtResultado.setBackground(Color.decode("#ececec"));
        add(scrollPane);

        comboBoxFilasA.setSelectedIndex(0);
        comboBoxColumnasA.setSelectedIndex(0);
        comboBoxFilasB.setSelectedIndex(0);
        comboBoxColumnasB.setSelectedIndex(0);

        crearCamposMatrizA();
        crearCamposMatrizB();

        btnSuma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarOperacion("+");
            }
        });

        btnResta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarOperacion("-");
            }
        });

        btnMultiplicacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarOperacion("*");
            }
        });
        comboBoxFilasA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearCamposMatrizA();
            }
        });

        comboBoxColumnasA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearCamposMatrizA();
            }
        });

        comboBoxFilasB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearCamposMatrizB();
            }
        });

        comboBoxColumnasB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearCamposMatrizB();
            }
        });
    }

    private void crearCamposMatrizA() {
        if (matrizAFields != null) {
            for (int i = 0; i < matrizAFields.length; i++) {
                for (int j = 0; j < matrizAFields[i].length; j++) {
                    remove(matrizAFields[i][j]);
                }
            }
        }

        int filasA = (int) comboBoxFilasA.getSelectedItem();
        int columnasA = (int) comboBoxColumnasA.getSelectedItem();
        matrizAFields = createMatrixFields(filasA, columnasA, 50, 160);
        repaint();
    }

    private void crearCamposMatrizB() {
        if (matrizBFields != null) {
            for (int i = 0; i < matrizBFields.length; i++) {
                for (int j = 0; j < matrizBFields[i].length; j++) {
                    remove(matrizBFields[i][j]);
                }
            }
        }

        int filasB = (int) comboBoxFilasB.getSelectedItem();
        int columnasB = (int) comboBoxColumnasB.getSelectedItem();
        matrizBFields = createMatrixFields(filasB, columnasB, 530, 160);
        repaint();
    }

    private NumericTextField[][] createMatrixFields(int rows, int cols, int x, int y) {
    NumericTextField[][] fields = new NumericTextField[rows][cols];
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            NumericTextField field = new NumericTextField();
            field.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
            field.setBounds(x + j * 60, y + i * 40, 50, 30);
            fields[i][j] = field;
            add(field);
        }
    }
    return fields;
}


    private double[][] getMatrixFromFields(JTextField[][] matrixFields) {
        int rows = matrixFields.length;
        int cols = matrixFields[0].length;
        double[][] matrix = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                try {
                    matrix[i][j] = Double.parseDouble(matrixFields[i][j].getText());
                } catch (NumberFormatException e) {
                    matrix[i][j] = 0;
                }
            }
        }
        return matrix;
    }

    private void realizarOperacion(String operacion) {
        int rowsA = (int) comboBoxFilasA.getSelectedItem();
        int colsA = (int) comboBoxColumnasA.getSelectedItem();
        int rowsB = (int) comboBoxFilasB.getSelectedItem();
        int colsB = (int) comboBoxColumnasB.getSelectedItem();

        if (operacion.equals("+") || operacion.equals("-")) {
            if (rowsA != rowsB || colsA != colsB) {
                txtResultado.setText("Las matrices deben tener la misma dimensión para esta operación.");
                return;
            }
        } else if (operacion.equals("*")) {
            if (colsA != rowsB) {
                txtResultado.setText("El número de columnas de la matriz A debe ser igual al número de filas de la matriz B para la multiplicación.");
                return;
            }
        }

        double[][] matrizA = getMatrixFromFields(matrizAFields);
        double[][] matrizB = getMatrixFromFields(matrizBFields);

        double[][] resultado;
        if (operacion.equals("+")) {
            resultado = sumarMatrices(matrizA, matrizB);
        } else if (operacion.equals("-")) {
            resultado = restarMatrices(matrizA, matrizB);
        } else { 
            resultado = multiplicarMatrices(matrizA, matrizB);
        }

        mostrarResultado(resultado);
    }

    private double[][] sumarMatrices(double[][] matrizA, double[][] matrizB) {
        int rows = matrizA.length;
        int cols = matrizA[0].length;
        double[][] resultado = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                resultado[i][j] = matrizA[i][j] + matrizB[i][j];
            }
        }
        return resultado;
    }

    private double[][] restarMatrices(double[][] matrizA, double[][] matrizB) {
        int rows = matrizA.length;
        int cols = matrizA[0].length;
        double[][] resultado = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                resultado[i][j] = matrizA[i][j] - matrizB[i][j];
            }
        }
        return resultado;
    }

    private double[][] multiplicarMatrices(double[][] matrizA, double[][] matrizB) {
        int rowsA = matrizA.length;
        int colsA = matrizA[0].length;
        int rowsB = matrizB.length;
        int colsB = matrizB[0].length;
        double[][] resultado = new double[rowsA][colsB];
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    resultado[i][j] += matrizA[i][k] * matrizB[k][j];
                }
            }
        }
        return resultado;
    }

    private void mostrarResultado(double[][] resultado) {
        txtResultado.setText("Resultado:\n");
        for (int i = 0; i < resultado.length; i++) {
            for (int j = 0; j < resultado[0].length; j++) {
                txtResultado.append(resultado[i][j] + "\t");
            }
            txtResultado.append("\n");
        }
    }

  
}
