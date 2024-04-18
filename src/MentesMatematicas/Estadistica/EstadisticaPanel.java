package MentesMatematicas.Estadistica;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.text.AbstractDocument;

public class EstadisticaPanel extends JPanel {

    private JTextArea txtDatos;
    private JLabel lblResultados;

    public EstadisticaPanel() {
        setLayout(null);
        setPreferredSize(new Dimension(980, 700));

        JLabel lblTitulo = new JLabel("Cálculos Estadísticos");
        lblTitulo.setFont(new Font("Yu Gothic UI", Font.BOLD, 40));
        lblTitulo.setBounds(240, 20, 500, 50);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo);

        JLabel lblIngresarDatos = new JLabel("Ingrese los datos (separados por espacios o comas):");
        lblIngresarDatos.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
        lblIngresarDatos.setBounds(240, 80, 500, 30);
        lblIngresarDatos.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblIngresarDatos);

        txtDatos = new JTextArea();
        txtDatos.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
        txtDatos.setLineWrap(true); 
        txtDatos.setWrapStyleWord(true); 
        JScrollPane scrollPane = new JScrollPane(txtDatos);
        scrollPane.setBounds(240, 120, 500, 200);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane);

        // Botón Calcular
        JButton btnCalcular = new JButton("Calcular");
        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularEstadisticas();
            }
        });
        btnCalcular.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        btnCalcular.setBackground(Color.decode("#2D142C"));
        btnCalcular.setForeground(Color.WHITE);
        btnCalcular.setFocusPainted(false);
        btnCalcular.setBounds(240, 340, 500, 40);
        add(btnCalcular);

        lblResultados = new JLabel("");
        lblResultados.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
        lblResultados.setHorizontalAlignment(SwingConstants.CENTER);
        lblResultados.setBounds(240, 390, 500, 250);
        add(lblResultados);

        ((AbstractDocument) txtDatos.getDocument()).setDocumentFilter(new DocumentFilter() {
            private boolean containsOnlyNumbers(String text) {
                return text.matches("^[\\-\\d., ]+$");
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
                String newText = currentText.substring(0, offset) + text + currentText.substring(offset + length);
                if (containsOnlyNumbers(newText)) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }

            @Override
            public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
                String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
                String newText = currentText.substring(0, offset) + text + currentText.substring(offset);
                if (containsOnlyNumbers(newText)) {
                    super.insertString(fb, offset, text, attr);
                }
            }
        });
    }

    private void calcularEstadisticas() {
        String[] datos = txtDatos.getText().split("[,\\s]+");
        double[] valores = new double[datos.length];
        for (int i = 0; i < datos.length; i++) {
            try {
                valores[i] = Double.parseDouble(datos[i]);
            } catch (NumberFormatException e) {
                lblResultados.setText("Ingrese valores numéricos separados por comas.");
                return;
            }
        }

        Arrays.sort(valores);

        double moda = calcularModa(valores);
        double media = calcularMedia(valores);
        double mediana = calcularMediana(valores);
        double minimo = valores[0];
        double maximo = valores[valores.length - 1];
        double desviacionTipicaMuestral = calcularDesviacionTipicaMuestral(valores, media);
        double desviacionTipicaPoblacional = calcularDesviacionTipicaPoblacional(valores, media);
        double varianzaMuestral = calcularVarianzaMuestral(valores, media);
        double varianzaPoblacional = calcularVarianzaPoblacional(valores, media);
        double q1 = calcularPercentil(valores, 25);
        double q3 = calcularPercentil(valores, 75);

        lblResultados.setText("<html>Moda: " + String.format("%.4f", moda) + "<br>"
                + "Media: " + String.format("%.4f", media) + "<br>"
                + "Mediana: " + String.format("%.4f", mediana) + "<br>"
                + "Mínimo: " + String.format("%.4f", minimo) + "<br>"
                + "Máximo: " + String.format("%.4f", maximo) + "<br>"
                + "Desviación típica muestral: " + String.format("%.4f", desviacionTipicaMuestral) + "<br>"
                + "Desviación típica poblacional: " + String.format("%.4f", desviacionTipicaPoblacional) + "<br>"
                + "Varianza muestral: " + String.format("%.4f", varianzaMuestral) + "<br>"
                + "Varianza poblacional: " + String.format("%.4f", varianzaPoblacional) + "<br>"
        );
    }

    private double calcularModa(double[] valores) {
        if (valores.length == 0) {
            return Double.NaN;
        }

        int[] frecuencia = new int[valores.length];

        for (int i = 0; i < valores.length; i++) {
            for (int j = i + 1; j < valores.length; j++) {
                if (valores[i] == valores[j]) {
                    frecuencia[i]++;
                }
            }
        }

        int maxFrecuencia = 0;
        double moda = valores[0]; 
        for (int i = 1; i < frecuencia.length; i++) {
            if (frecuencia[i] > maxFrecuencia) {
                maxFrecuencia = frecuencia[i];
                moda = valores[i];
            }
        }

        return moda;
    }

    private double calcularMedia(double[] valores) {
        if (valores.length == 0) {
            return Double.NaN; 
        }

        double suma = 0.0;
        for (double valor : valores) {
            suma += valor;
        }

        return suma / valores.length;
    }

    private double calcularMediana(double[] valores) {
        if (valores.length == 0) {
            return Double.NaN; 
        }

        Arrays.sort(valores);

        int n = valores.length;
        if (n % 2 == 0) {
            int indice1 = n / 2 - 1;
            int indice2 = n / 2;
            return (valores[indice1] + valores[indice2]) / 2.0;
        } else {
            return valores[n / 2];
        }
    }

    private double calcularDesviacionTipicaMuestral(double[] valores, double media) {
        if (valores.length <= 1) {
            return Double.NaN; 
        }

        double sumaDiferenciasCuadradas = 0.0;
        for (double valor : valores) {
            sumaDiferenciasCuadradas += Math.pow(valor - media, 2);
        }

        double varianzaMuestral = sumaDiferenciasCuadradas / (valores.length - 1);
        return Math.sqrt(varianzaMuestral);
    }

    private double calcularDesviacionTipicaPoblacional(double[] valores, double media) {
        if (valores.length == 0) {
            return Double.NaN;
        }

        double sumaDiferenciasCuadradas = 0.0;
        for (double valor : valores) {
            sumaDiferenciasCuadradas += Math.pow(valor - media, 2);
        }

        double varianzaPoblacional = sumaDiferenciasCuadradas / valores.length;
        return Math.sqrt(varianzaPoblacional);
    }

    private double calcularVarianzaMuestral(double[] valores, double media) {
        if (valores.length <= 1) {
            return Double.NaN; 
        }

        double sumaDiferenciasCuadradas = 0.0;
        for (double valor : valores) {
            sumaDiferenciasCuadradas += Math.pow(valor - media, 2);
        }

        return sumaDiferenciasCuadradas / (valores.length - 1);
    }

    private double calcularVarianzaPoblacional(double[] valores, double media) {
        if (valores.length == 0) {
            return Double.NaN; 
        }

        double sumaDiferenciasCuadradas = 0.0;
        for (double valor : valores) {
            sumaDiferenciasCuadradas += Math.pow(valor - media, 2);
        }

        return sumaDiferenciasCuadradas / valores.length;
    }

    private double calcularPercentil(double[] valores, int percentil) {
        if (valores.length == 0) {
            return Double.NaN; 
        }

        Arrays.sort(valores);

        double posicion = (percentil / 100.0) * (valores.length + 1);

        if (posicion % 1 == 0) {
            return valores[(int) posicion - 1];
        } else {
            int indiceInferior = (int) Math.floor(posicion) - 1;
            int indiceSuperior = (int) Math.ceil(posicion) - 1;
            double valorInferior = valores[indiceInferior];
            double valorSuperior = valores[indiceSuperior];
            double fraccion = posicion - Math.floor(posicion);
            return valorInferior + fraccion * (valorSuperior - valorInferior);
        }
    }

   
}
