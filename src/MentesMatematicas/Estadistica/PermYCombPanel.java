package MentesMatematicas.Estadistica;

import Components.NumericTextField;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PermYCombPanel extends JPanel {

    private JTextField txtN;
    private JTextField txtR;
    private JLabel lblResultado;

    public PermYCombPanel() {
        setLayout(null);
        setPreferredSize(new Dimension(980, 700));

        JLabel lblTitulo = new JLabel("Permutaciones y Combinaciones");
        lblTitulo.setFont(new Font("Yu Gothic UI", Font.BOLD, 40));
        lblTitulo.setBounds(240, 20, 600, 50);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo);

        JLabel lblIngresarValores = new JLabel("Ingrese los valores de n y r para calcular:");
        lblIngresarValores.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
        lblIngresarValores.setBounds(240, 80, 500, 30);
        lblIngresarValores.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblIngresarValores);

        JLabel lblN = new JLabel("Valor de n:");
        lblN.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        lblN.setBounds(240, 140, 120, 40);
        add(lblN);

        txtN = new NumericTextField();
        txtN.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        txtN.setBounds(370, 140, 100, 40);
        add(txtN);

        JLabel lblR = new JLabel("Valor de r:");
        lblR.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        lblR.setBounds(240, 190, 120, 40);
        add(lblR);

        txtR = new NumericTextField();
        txtR.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        txtR.setBounds(370, 190, 100, 40);
        add(txtR);

        JLabel lblImagen = new JLabel(new ImageIcon("Images/fperm.jpg"));
        lblImagen.setBounds(240, 250, 500, 200);
        add(lblImagen);

        JButton btnCalcular = new JButton("Calcular");
        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularPermutacionesYCombinaciones();
            }
        });
        btnCalcular.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        btnCalcular.setBackground(Color.decode("#2D142C"));
        btnCalcular.setForeground(Color.WHITE);
        btnCalcular.setFocusPainted(false);
        btnCalcular.setBounds(240, 470, 500, 40);
        add(btnCalcular);

        // Etiqueta para mostrar el resultado
        lblResultado = new JLabel("");
        lblResultado.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
        lblResultado.setBounds(240, 520, 500, 100);
        add(lblResultado);
    }

    private void calcularPermutacionesYCombinaciones() {
        try {
            int n = Integer.parseInt(txtN.getText());
            int r = Integer.parseInt(txtR.getText());

            if (n < r) {
                JOptionPane.showMessageDialog(this, "El número total de elementos (n) debe ser mayor o igual que el número de elementos elegidos (r).", "Error", JOptionPane.ERROR_MESSAGE);
                lblResultado.setText("");
                return;
            }

            long permutaciones = calcularPermutaciones(n, r);
            long combinaciones = calcularCombinaciones(n, r);

            lblResultado.setText("<html>Permutaciones: " + permutaciones + "<br/>Combinaciones: " + combinaciones + "</html>");
        } catch (NumberFormatException ex) {
            lblResultado.setText("Ingrese valores numéricos válidos para n y r.");
        }
    }

    private long calcularPermutaciones(int n, int r) {
        long permutaciones = 1;
        for (int i = n; i > n - r; i--) {
            permutaciones *= i;
        }
        return permutaciones;
    }

    private long calcularCombinaciones(int n, int r) {
        long combinaciones = calcularPermutaciones(n, r) / factorial(r);
        return combinaciones;
    }

    private long factorial(int n) {
        long factorial = 1;
        for (int i = 2; i <= n; i++) {
            factorial *= i;
        }
        return factorial;
    }
}
