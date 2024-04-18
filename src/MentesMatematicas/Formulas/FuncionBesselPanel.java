package MentesMatematicas.Formulas;

import LibMate.Mate;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FuncionBesselPanel extends JPanel {

    private JTextField txtN;
    private JTextField txtX;
    private JLabel lblResultado;

    public FuncionBesselPanel() {
        setLayout(null);
        setPreferredSize(new Dimension(980, 700));

        JLabel lblTitulo = new JLabel("Función de Bessel");
        lblTitulo.setFont(new Font("Yu Gothic UI", Font.BOLD, 40));
        lblTitulo.setBounds(240, 20, 500, 50);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo);

        JLabel lblInfo = new JLabel("<html>La función de Bessel es una función matemática que describe ondas oscilatorias.</html>");
        lblInfo.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
        lblInfo.setBounds(240, 80, 500, 100);
        lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblInfo);

        JLabel lblImagen = new JLabel(new ImageIcon("Images/fbessel.png"));
        lblImagen.setBounds(240, 200, 500, 200);
        add(lblImagen);

        JLabel lblIngresarN = new JLabel("Ingrese el valor de n:");
        lblIngresarN.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
        lblIngresarN.setBounds(70, 420, 500, 30);
        lblIngresarN.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblIngresarN);

        txtN = new JTextField();
        txtN.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        txtN.setBounds(240, 460, 100, 40);
        add(txtN);

        JLabel lblIngresarX = new JLabel("Ingrese el valor de x:");
        lblIngresarX.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
        lblIngresarX.setBounds(330, 420, 500, 30);
        lblIngresarX.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblIngresarX);

        txtX = new JTextField();
        txtX.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        txtX.setBounds(500, 460, 100, 40);
        add(txtX);

        JButton btnResolver = new JButton("Resolver");
        btnResolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resolverFuncionBessel();
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

    private void resolverFuncionBessel() {
        try {
            int n = Integer.parseInt(txtN.getText());
            double x = Double.parseDouble(txtX.getText());

            double resultado = calcularFuncionBessel(n, x);

            lblResultado.setText(String.format("J%d(%.2f) = %.4f", n, x, resultado));
        } catch (NumberFormatException ex) {
            lblResultado.setText("Ingrese valores numéricos válidos para n y x.");
        }
    }

    private double calcularFuncionBessel(int n, double x) {
        double sum = 0.0;
        double term = 1.0;
        double factorialM = 1.0;
        double factorialN = 1.0;
        double xSquaredOverFour = x * x / 4.0;

        for (int m = 0; m <= 1000; m++) {
            double currentTerm = term / (factorialM * factorialN);
            sum += currentTerm;

            term *= -xSquaredOverFour;
            factorialM *= (m + 1.0);
            factorialN *= (m + n + 1.0);

            if (Mate.valorAbsoluto(currentTerm) < 1e-15) {
                break;
            }
        }

        return sum;
    }

  
}
