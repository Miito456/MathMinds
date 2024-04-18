package MentesMatematicas.Formulas;

import LibMate.Mate;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IntegralDeFresnelPanel extends JPanel {

    private JTextField txtX;
    private JLabel lblResultado;

    public IntegralDeFresnelPanel() {
        setLayout(null);
        setPreferredSize(new Dimension(980, 700));

        JLabel lblTitulo = new JLabel("Integral de Fresnel");
        lblTitulo.setFont(new Font("Yu Gothic UI", Font.BOLD, 40));
        lblTitulo.setBounds(240, 20, 500, 50);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo);

        JLabel lblInfo = new JLabel("<html>La integral de Fresnel es una función definida como la integral definida de la función seno integral.</html>");
        lblInfo.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
        lblInfo.setBounds(240, 80, 500, 100);
        lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblInfo);

        JLabel lblImagen = new JLabel(new ImageIcon("Images/ffresnel.png"));
        lblImagen.setBounds(240, 200, 500, 200);
        add(lblImagen);

        JLabel lblIngresarX = new JLabel("Ingrese el valor de x:");
        lblIngresarX.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        lblIngresarX.setBounds(240, 420, 500, 30);
        lblIngresarX.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblIngresarX);

        txtX = new JTextField();
        txtX.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        txtX.setBounds(370, 460, 250, 40);
        add(txtX);

        JButton btnResolver = new JButton("Resolver");
        btnResolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resolverIntegralDeFresnel();
            }
        });
        btnResolver.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        btnResolver.setBackground(Color.decode("#2D142C"));
        btnResolver.setForeground(Color.WHITE);
        btnResolver.setFocusPainted(false);
        btnResolver.setBounds(240, 520, 500, 40);
        add(btnResolver);

        lblResultado = new JLabel("");
        lblResultado.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
        lblResultado.setBounds(240, 570, 600, 30);
        add(lblResultado);
    }

    private void resolverIntegralDeFresnel() {
        try {
            double x = Double.parseDouble(txtX.getText());

            double resultadoS = calcularIntegralDeFresnelS(x);
            double resultadoC = calcularIntegralDeFresnelC(x);

            lblResultado.setText(String.format("Fresnel(x) (S) = %.4f," + " \n " + "Fresnel(x) (C) = %.4f", resultadoS, resultadoC));
        } catch (NumberFormatException ex) {
            lblResultado.setText("Ingrese un valor numérico válido para x.");
        }
    }

    private double calcularIntegralDeFresnelS(double x) {
        int n = 1000;
        double h = x / n;

        double suma = 0;
        for (int i = 0; i <= n; i++) {
            double xi = i * h;
            double fxi = Mate.seno(Mate.PI * xi * xi / 2);
            if (i == 0 || i == n) {
                suma += fxi;
            } else if (i % 2 == 0) {
                suma += 2 * fxi;
            } else {
                suma += 4 * fxi;
            }
        }
        suma *= h / 3;
        return suma;
    }

    private double calcularIntegralDeFresnelC(double x) {
        int n = 1000; 
        double h = x / n; 

        double suma = 0;
        for (int i = 0; i <= n; i++) {
            double xi = i * h;
            double fxi = Mate.coseno(Mate.PI * xi * xi / 2);
            if (i == 0 || i == n) {
                suma += fxi;
            } else if (i % 2 == 0) {
                suma += 2 * fxi;
            } else {
                suma += 4 * fxi;
            }
        }
        suma *= h / 3;
        return suma;
    }

    private double calcularIntegralDeFresnel(double x) {
        double suma = 0;
        for (int n = 0; n < 10; n++) {
            double termino = Mate.potencia(-1, n) * Mate.potencia(Mate.PI * x * x / 2, 4 * n + 1) / (factorial(2 * n + 1) * (4 * n + 1));
            suma += termino;
        }
        return suma;
    }

    private double factorial(int n) {
        if (n <= 0) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    
}
