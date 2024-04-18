package MentesMatematicas.Formulas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntropiaShannonPanel extends JPanel {
    private JTextField[] txtProbabilidades;
    private JLabel lblResultado;

    public EntropiaShannonPanel() {
        setLayout(null);
        setPreferredSize(new Dimension(980, 700));

        JLabel lblTitulo = new JLabel("Entropía de Shannon");
        lblTitulo.setFont(new Font("Yu Gothic UI", Font.BOLD, 40));
        lblTitulo.setBounds(240, 20, 500, 50);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo);

        JLabel lblInfo = new JLabel("<html>La Entropía de Shannon es una medida de la incertidumbre en una variable aleatoria.</html>");
        lblInfo.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
        lblInfo.setBounds(240, 80, 500, 100);
        lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblInfo);

        JLabel lblImagen = new JLabel(new ImageIcon("Images/fentropia.jpg"));
        lblImagen.setBounds(240, 200, 500, 200);
        add(lblImagen);

        JLabel lblIngresarProbabilidades = new JLabel("Ingrese las probabilidades:");
        lblIngresarProbabilidades.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
        lblIngresarProbabilidades.setBounds(240, 420, 500, 30);
        lblIngresarProbabilidades.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblIngresarProbabilidades);

        txtProbabilidades = new JTextField[5];
        for (int i = 0; i < txtProbabilidades.length; i++) {
            JLabel lblProbabilidad = new JLabel("P" + (i + 1) + ":");
            lblProbabilidad.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
            lblProbabilidad.setBounds(240, 470 + i * 50, 60, 30);
            add(lblProbabilidad);

            txtProbabilidades[i] = new JTextField();
            txtProbabilidades[i].setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
            txtProbabilidades[i].setBounds(300, 470 + i * 50, 100, 30);
            add(txtProbabilidades[i]);
        }

        // Botón Resolver
        JButton btnResolver = new JButton("Resolver");
        btnResolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resolverEntropiaShannon();
            }
        });
        btnResolver.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        btnResolver.setBackground(Color.decode("#2D142C"));
        btnResolver.setForeground(Color.WHITE);
        btnResolver.setFocusPainted(false);
        btnResolver.setBounds(240, 620, 500, 40);
        add(btnResolver);

        // Etiqueta para mostrar el resultado
        lblResultado = new JLabel("");
        lblResultado.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
        lblResultado.setBounds(240, 670, 500, 30);
        add(lblResultado);
    }

    private void resolverEntropiaShannon() {
        try {
            double[] probabilidades = new double[5];
            for (int i = 0; i < txtProbabilidades.length; i++) {
                probabilidades[i] = Double.parseDouble(txtProbabilidades[i].getText());
            }

            double resultado = calcularEntropiaShannon(probabilidades);

            lblResultado.setText(String.format("Entropía de Shannon = %.4f", resultado));
        } catch (NumberFormatException ex) {
            lblResultado.setText("Ingrese valores numéricos válidos para las probabilidades.");
        }
    }

    private double calcularEntropiaShannon(double[] probabilidades) {
        double entropia = 0;
        for (double probabilidad : probabilidades) {
            if (probabilidad > 0) {
                entropia -= probabilidad * Math.log(probabilidad) / Math.log(2);
            }
        }
        return entropia;
    }

    
}
