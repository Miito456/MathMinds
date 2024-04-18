package MentesMatematicas.Formulas;

import LibMate.Mate;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ZetaRiemannPanel extends JPanel {

    private JTextField txtS;
    private JLabel lblResultado;

    public ZetaRiemannPanel() {
        setLayout(null);
        setPreferredSize(new Dimension(980, 700));

        JLabel lblTitulo = new JLabel("Zeta de Riemann");
        lblTitulo.setFont(new Font("Yu Gothic UI", Font.BOLD, 40));
        lblTitulo.setBounds(240, 20, 500, 50);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo);

        JLabel lblInfo = new JLabel("<html>La función Zeta de Riemann es una función matemática analítica de una variable compleja.</html>");
        lblInfo.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
        lblInfo.setBounds(240, 80, 500, 100);
        lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblInfo);

        JLabel lblImagen = new JLabel(new ImageIcon("Images/friemman.png"));
        lblImagen.setBounds(240, 200, 500, 200);
        add(lblImagen);

        JLabel lblIngresarS = new JLabel("Ingrese el valor de s:");
        lblIngresarS.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
        lblIngresarS.setBounds(240, 420, 500, 30);
        lblIngresarS.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblIngresarS);

        JLabel lblS = new JLabel("Valor de s:");
        lblS.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        lblS.setBounds(240, 460, 120, 40);
        add(lblS);

        txtS = new JTextField();
        txtS.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        txtS.setBounds(370, 460, 100, 40);
        add(txtS);

        JButton btnResolver = new JButton("Resolver");
        btnResolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resolverZetaRiemann();
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
        lblResultado.setBounds(240, 570, 500, 30);
        add(lblResultado);
    }

    private void resolverZetaRiemann() {
        try {
            double s = Double.parseDouble(txtS.getText());

            double resultado = calcularZetaDeRiemann(s);

            lblResultado.setText(String.format("ζ(%.2f) = %.4f", s, resultado));
        } catch (NumberFormatException ex) {
            lblResultado.setText("Ingrese un valor numérico válido para s.");
        }
    }

private double calcularZetaDeRiemann(double s) {
    if (s <= 1.0) {
        JOptionPane.showMessageDialog(null, "La función Zeta de Riemann solo está definida para valores de s con parte real mayor que 1.");
        return Double.NaN; // Valor no numérico para indicar un error
    }

    double suma = 0;

    // Suma de los primeros términos
    for (int n = 1; n <= 10; n++) {
        suma += 1.0 / Mate.potencia(n, s);
    }

    // Aproximación utilizando la fórmula de Euler-Maclaurin
    double integral = 0;
    int N = 1000; // Número de términos para la aproximación
    for (int k = 1; k <= N; k++) {
        integral += Mate.potencia(k, -s);
    }
    suma += integral / (s - 1);

    return suma;
}

}
