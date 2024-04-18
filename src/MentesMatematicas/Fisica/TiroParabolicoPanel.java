package MentesMatematicas.Fisica;

import LibMate.Mate;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class TiroParabolicoPanel extends JPanel {

    private JTextField txtVelocidadInicial;
    private JTextField txtAngulo;
    private JLabel lblResultado;

    public TiroParabolicoPanel() {
        setLayout(null);
        setPreferredSize(new Dimension(980, 700));

        JLabel lblTitulo = new JLabel("Tiro Parabólico");
        lblTitulo.setFont(new Font("Yu Gothic UI", Font.BOLD, 40));
        lblTitulo.setBounds(240, 20, 500, 50);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo);

        JLabel lblImagen = new JLabel(new ImageIcon("Images/tparabolico.jpg"));
        lblImagen.setBounds(240, 100, 500, 300);
        add(lblImagen);

        JLabel lblVelocidadInicial = new JLabel("Velocidad inicial (m/s):");
        lblVelocidadInicial.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        lblVelocidadInicial.setBounds(240, 440, 280, 40);
        add(lblVelocidadInicial);

        txtVelocidadInicial = new JTextField();
        txtVelocidadInicial.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        txtVelocidadInicial.setBounds(520, 440, 100, 40);
        add(txtVelocidadInicial);

        JLabel lblAngulo = new JLabel("Ángulo (grados):");
        lblAngulo.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        lblAngulo.setBounds(240, 490, 280, 40);
        add(lblAngulo);

        txtAngulo = new JTextField();
        txtAngulo.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        txtAngulo.setBounds(520, 490, 100, 40);
        add(txtAngulo);

        JButton btnCalcular = new JButton("Calcular");
        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularTiroParabolico();
            }
        });
        btnCalcular.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        btnCalcular.setBackground(Color.decode("#2D142C"));
        btnCalcular.setForeground(Color.WHITE);
        btnCalcular.setFocusPainted(false);
        btnCalcular.setBounds(240, 540, 380, 40);
        add(btnCalcular);

        lblResultado = new JLabel("");
        lblResultado.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
        lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
        lblResultado.setBounds(240, 540, 380, 180);
        add(lblResultado);
    }

    private void calcularTiroParabolico() {
        try {
            double velocidadInicial = Double.parseDouble(txtVelocidadInicial.getText());
            double angulo = Mate.toRadians(Double.parseDouble(txtAngulo.getText())); // Convertir a radianes
            double g = 9.81;

            double alturaMaxima = (Mate.potencia(velocidadInicial, 2) * Mate.potencia(Mate.seno(angulo), 2)) / (2 * g);
            double alcanceMaximo = Mate.potencia(velocidadInicial, 2) * Mate.seno(2 * angulo) / g;
            double tiempoVuelo = (2 * velocidadInicial * Mate.seno(angulo)) / g;

            DecimalFormat df = new DecimalFormat("#.####"); // Formato para 4 decimales
            lblResultado.setText("<html>Altura máxima: " + df.format(alturaMaxima) + " m<br>"
                    + "Alcance máximo: " + df.format(alcanceMaximo) + " m<br>"
                    + "Tiempo de vuelo: " + df.format(tiempoVuelo) + " s</html>");
        } catch (NumberFormatException ex) {
            lblResultado.setText("Ingrese valores numéricos válidos para la velocidad inicial y el ángulo.");
        }
    }
}
