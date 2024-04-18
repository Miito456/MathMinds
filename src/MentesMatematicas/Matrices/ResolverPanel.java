package MentesMatematicas.Matrices;

import Components.NumericTextField;
import LibMate.Mate;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResolverPanel extends JPanel {

    private JComboBox<Integer> comboBoxFilas;
    private JComboBox<Integer> comboBoxColumnas;
    private JTextField[][] matrizFields;
    private JButton btnGauss;
    private JButton btnTraspuesta;
    private JButton btnMultiplicacionPorNumero;
    private JButton btnPotencia;
    private JButton btnDeterminante;
    private JButton btnRango;
    private JTextArea txtResultado;

    public ResolverPanel() {
        setLayout(null);

        setPreferredSize(new Dimension(980, 700));

        JLabel lblTitulo = new JLabel("Solución de matrices");
        lblTitulo.setFont(new Font("Yu Gothic UI", Font.BOLD, 24));
        lblTitulo.setBounds(0, 20, 980, 30);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo);

        JLabel lblTamañoMatriz = new JLabel("Elija el tamaño de la matriz:");
        lblTamañoMatriz.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
        lblTamañoMatriz.setBounds(0, 70, 980, 30);
        lblTamañoMatriz.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTamañoMatriz);

        comboBoxFilas = new JComboBox<>();
        comboBoxFilas.setBackground(Color.decode("#ececec"));
        comboBoxFilas.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        comboBoxFilas.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        comboBoxFilas.setBounds(420, 120, 50, 30);
        for (int i = 2; i <= 4; i++) {
            comboBoxFilas.addItem(i);
        }
        add(comboBoxFilas);

        comboBoxColumnas = new JComboBox<>();
        comboBoxColumnas.setBackground(Color.decode("#ececec"));
        comboBoxColumnas.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        comboBoxColumnas.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        comboBoxColumnas.setBounds(510, 120, 50, 30);
        for (int i = 3; i <= 5; i++) {
            comboBoxColumnas.addItem(i);
        }
        add(comboBoxColumnas);

        JLabel lblMatriz = new JLabel("Matriz:");
        lblMatriz.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
        lblMatriz.setBounds(0, 170, 980, 30);
        lblMatriz.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblMatriz);

        int filas = (int) comboBoxFilas.getSelectedItem();
        int columnas = (int) comboBoxColumnas.getSelectedItem();
        matrizFields = new JTextField[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                JTextField field = new JTextField();
                field.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
                field.setBounds(340 + j * 60, 220 + i * 40, 50, 30);
                matrizFields[i][j] = field;
                add(field);
            }
        }

        JPanel buttonsPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        buttonsPanel.setBounds(50, 300 + filas * 40, 880, 100);
        add(buttonsPanel);

        btnGauss = new JButton("Gauss-Jordan");
        btnGauss.setBackground(Color.decode("#2D142C"));
        btnGauss.setForeground(Color.WHITE);
        btnGauss.setFocusPainted(false);
        btnGauss.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        buttonsPanel.add(btnGauss);

        btnTraspuesta = new JButton("Traspuesta");
        btnTraspuesta.setBackground(Color.decode("#2D142C"));
        btnTraspuesta.setForeground(Color.WHITE);
        btnTraspuesta.setFocusPainted(false);
        btnTraspuesta.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        buttonsPanel.add(btnTraspuesta);

        btnMultiplicacionPorNumero = new JButton("Multiplicación por número");
        btnMultiplicacionPorNumero.setBackground(Color.decode("#2D142C"));
        btnMultiplicacionPorNumero.setForeground(Color.WHITE);
        btnMultiplicacionPorNumero.setFocusPainted(false);
        btnMultiplicacionPorNumero.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
        buttonsPanel.add(btnMultiplicacionPorNumero);

        btnPotencia = new JButton("Potencia");
        btnPotencia.setBackground(Color.decode("#2D142C"));
        btnPotencia.setForeground(Color.WHITE);
        btnPotencia.setFocusPainted(false);
        btnPotencia.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        buttonsPanel.add(btnPotencia);

        btnDeterminante = new JButton("Determinante");
        btnDeterminante.setBackground(Color.decode("#2D142C"));
        btnDeterminante.setForeground(Color.WHITE);
        btnDeterminante.setFocusPainted(false);
        btnDeterminante.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        buttonsPanel.add(btnDeterminante);

        btnRango = new JButton("Rango");
        btnRango.setBackground(Color.decode("#2D142C"));
        btnRango.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        btnRango.setForeground(Color.WHITE);
        btnRango.setFocusPainted(false);
        buttonsPanel.add(btnRango);

        txtResultado = new JTextArea();
        txtResultado.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(txtResultado);
        scrollPane.setBounds(50, 420 + filas * 40, 880, 160);
        txtResultado.setBackground(Color.decode("#ececec"));
        add(scrollPane);

        ActionListener comboBoxListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filas = (int) comboBoxFilas.getSelectedItem();
                int columnas = (int) comboBoxColumnas.getSelectedItem();
                removeAllFields();
                createFields(filas, columnas);
                revalidate();
                repaint();
            }
        };
        btnGauss.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applyGaussMethod();
            }
        });

        btnTraspuesta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double[][] matriz = getMatrixFromFields();
                int filas = matriz.length;
                int columnas = matriz[0].length;

                double[][] matrizTranspuesta = new double[columnas][filas];

                for (int i = 0; i < filas; i++) {
                    for (int j = 0; j < columnas; j++) {
                        matrizTranspuesta[j][i] = matriz[i][j];
                    }
                }

                txtResultado.setText("Matriz Transpuesta:\n");
                for (int i = 0; i < columnas; i++) {
                    for (int j = 0; j < filas; j++) {
                        txtResultado.append(matrizTranspuesta[i][j] + "\t");
                    }
                    txtResultado.append("\n");
                }
            }
        });
        btnMultiplicacionPorNumero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Ingrese el número por el cual desea multiplicar la matriz:");
                double numero;
                try {
                    numero = Double.parseDouble(input);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double[][] matriz = getMatrixFromFields();
                int filas = matriz.length;
                int columnas = matriz[0].length;

                for (int i = 0; i < filas; i++) {
                    for (int j = 0; j < columnas; j++) {
                        matriz[i][j] *= numero;
                    }
                }

                txtResultado.setText("Matriz multiplicada por " + numero + ":\n");
                for (int i = 0; i < filas; i++) {
                    for (int j = 0; j < columnas; j++) {
                        txtResultado.append(matriz[i][j] + "\t");
                    }
                    txtResultado.append("\n");
                }
            }
        });
        btnPotencia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Ingrese el exponente al cual desea elevar la matriz:");
                int exponente;
                try {
                    exponente = Integer.parseInt(input);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un exponente válido (número entero).", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double[][] matriz = getMatrixFromFields();
                int filas = matriz.length;
                int columnas = matriz[0].length;

                if (filas != columnas) {
                    JOptionPane.showMessageDialog(null, "La matriz debe ser cuadrada para calcular la potencia.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double[][] matrizResultado = Mate.potenciaMatriz(matriz, exponente);

                txtResultado.setText("Matriz elevada a la potencia " + exponente + ":\n");
                for (int i = 0; i < filas; i++) {
                    for (int j = 0; j < columnas; j++) {
                        txtResultado.append(matrizResultado[i][j] + "\t");
                    }
                    txtResultado.append("\n");
                }
            }
        });
        btnDeterminante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double[][] matriz = getMatrixFromFields();
                int filas = matriz.length;
                int columnas = matriz[0].length;

                if (filas != columnas) {
                    JOptionPane.showMessageDialog(null, "La matriz debe ser cuadrada para calcular el determinante.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double determinante = 1;

                for (int i = 0; i < filas - 1; i++) {
                    if (matriz[i][i] == 0) {
                        for (int k = i + 1; k < filas; k++) {
                            if (matriz[k][i] != 0) {
                                double[] temp = matriz[i];
                                matriz[i] = matriz[k];
                                matriz[k] = temp;
                                determinante = -determinante;
                                break;
                            }
                        }
                    }
                    if (matriz[i][i] == 0) {
                        determinante = 0;
                        break;
                    }
                    for (int j = i + 1; j < filas; j++) {
                        double factor = matriz[j][i] / matriz[i][i];
                        for (int k = i; k < columnas; k++) {
                            matriz[j][k] -= factor * matriz[i][k];
                        }
                    }
                }

                for (int i = 0; i < filas; i++) {
                    determinante *= matriz[i][i];
                }

                txtResultado.setText("Determinante de la matriz:\n" + determinante);
            }
        });
        btnRango.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double[][] matriz = getMatrixFromFields();
                int filas = matriz.length;
                int columnas = matriz[0].length;

                for (int i = 0; i < filas; i++) {
                    int filaPivote = -1;
                    for (int j = i; j < filas; j++) {
                        if (matriz[j][i] != 0) {
                            filaPivote = j;
                            break;
                        }
                    }
                    if (filaPivote == -1) {
                        continue;
                    }
                    if (filaPivote != i) {
                        double[] temp = matriz[i];
                        matriz[i] = matriz[filaPivote];
                        matriz[filaPivote] = temp;
                    }
                    for (int j = i + 1; j < filas; j++) {
                        double factor = matriz[j][i] / matriz[i][i];
                        for (int k = i; k < columnas; k++) {
                            matriz[j][k] -= factor * matriz[i][k];
                        }
                    }
                }

                int rango = 0;
                for (int i = 0; i < filas; i++) {
                    boolean filaNoNula = false;
                    for (int j = 0; j < columnas; j++) {
                        if (matriz[i][j] != 0) {
                            filaNoNula = true;
                            break;
                        }
                    }
                    if (filaNoNula) {
                        rango++;
                    }
                }

                txtResultado.setText("Rango de la matriz:\n" + rango);
            }
        });

        comboBoxFilas.addActionListener(comboBoxListener);
        comboBoxColumnas.addActionListener(comboBoxListener);

        comboBoxListener.actionPerformed(null);

    }

    private void removeAllFields() {
        for (int i = 0; i < matrizFields.length; i++) {
            for (int j = 0; j < matrizFields[0].length; j++) {
                remove(matrizFields[i][j]);
            }
        }
    }

    private void createFields(int filas, int columnas) {
    matrizFields = new NumericTextField[filas][columnas];
    for (int i = 0; i < filas; i++) {
        for (int j = 0; j < columnas; j++) {
            NumericTextField field = new NumericTextField();
            field.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
            field.setBounds(340 + j * 60, 220 + i * 40, 50, 30);
            matrizFields[i][j] = field;
            add(field);
        }
    }
}


    private void applyGaussMethod() {
        double[][] matriz = getMatrixFromFields();
        int filas = matriz.length;
        int columnas = matriz[0].length;

        for (int i = 0; i < filas; i++) {
            double max = Mate.valorAbsoluto(matriz[i][i]);
            int maxIndex = i;
            for (int k = i + 1; k < filas; k++) {
                if (Mate.valorAbsoluto(matriz[k][i]) > max) {
                    max = Mate.valorAbsoluto(matriz[k][i]);
                    maxIndex = k;
                }
            }

            if (maxIndex != i) {
                double[] temp = matriz[i];
                matriz[i] = matriz[maxIndex];
                matriz[maxIndex] = temp;
            }

            double pivot = matriz[i][i];
            if (!esCero(pivot)) {
                for (int j = i; j < columnas; j++) {
                    matriz[i][j] /= pivot;
                }
            }

            for (int k = i + 1; k < filas; k++) {
                double factor = matriz[k][i];
                for (int j = i; j < columnas; j++) {
                    matriz[k][j] -= factor * matriz[i][j];
                }
            }
        }

        for (int i = filas - 1; i > 0; i--) {
            for (int k = i - 1; k >= 0; k--) {
                double factor = matriz[k][i];
                for (int j = columnas - 1; j >= i; j--) {
                    matriz[k][j] -= factor * matriz[i][j];
                }
            }
        }

        txtResultado.setText("Matriz escalonada:\n");
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                txtResultado.append(matriz[i][j] + "\t");
            }
            txtResultado.append("\n");
        }
    }

    private boolean esCero(double valor) {
        return Mate.valorAbsoluto(valor) < 1e-10;
    }

    private double[][] getMatrixFromFields() {
        int filas = matrizFields.length;
        int columnas = matrizFields[0].length;
        double[][] matriz = new double[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                try {
                    matriz[i][j] = Double.parseDouble(matrizFields[i][j].getText());
                } catch (NumberFormatException e) {
                    matriz[i][j] = 0;
                } catch (ArrayIndexOutOfBoundsException ex) {
                    matriz[i][j] = 0;
                }
            }
        }
        return matriz;
    }

  
}
