package MentesMatematicas.Fisica;

import Components.NumericTextField;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import LibMate.Mate;

public class MovimientoAPanel extends JPanel {

    private JTextField txtAmplitud;
    private JTextField txtFrecuencia;
    private JTextField txtTiempo;
    private JLabel lblResultado;

    public MovimientoAPanel() {
        setLayout(null);
        setPreferredSize(new Dimension(980, 700));

        JLabel lblTitulo = new JLabel("Movimiento Armónico Simple (MAS)");
        lblTitulo.setFont(new Font("Yu Gothic UI", Font.BOLD, 30));
        lblTitulo.setBounds(240, 20, 520, 50);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo);

        JLabel lblInfo = new JLabel("<html>La velocidad del cuerpo en el Movimiento Armónico Simple (MAS) se calcula con la siguiente fórmula:</html>");
        lblInfo.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
        lblInfo.setBounds(240, 80, 500, 100);
        lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblInfo);

        JLabel lblFormula = new JLabel("V = A' * ω * cos(ω * t)");
        lblFormula.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        lblFormula.setBounds(240, 150, 500, 40);
        lblFormula.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblFormula);

        JLabel lblAmplitud = new JLabel("Amplitud (A'):");
        lblAmplitud.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        lblAmplitud.setBounds(240, 220, 200, 40);
        add(lblAmplitud);

        txtAmplitud = new NumericTextField();
        txtAmplitud.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        txtAmplitud.setBounds(450, 220, 200, 40);
        add(txtAmplitud);

        JLabel lblFrecuencia = new JLabel("Frecuencia (ω):");
        lblFrecuencia.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        lblFrecuencia.setBounds(240, 280, 200, 40);
        add(lblFrecuencia);

        txtFrecuencia = new NumericTextField();
        txtFrecuencia.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        txtFrecuencia.setBounds(450, 280, 200, 40);
        add(txtFrecuencia);

        JLabel lblTiempo = new JLabel("Tiempo (segundos):");
        lblTiempo.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        lblTiempo.setBounds(240, 340, 250, 40);
        add(lblTiempo);

        txtTiempo = new JTextField();
        txtTiempo.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        txtTiempo.setBounds(500, 340, 150, 40);
        add(txtTiempo);

        JButton btnCalcular = new JButton("Calcular Velocidad");
        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularVelocidad();
            }
        });
        btnCalcular.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        btnCalcular.setBackground(Color.decode("#2D142C"));
        btnCalcular.setForeground(Color.WHITE);
        btnCalcular.setFocusPainted(false);
        btnCalcular.setBounds(240, 400, 410, 40);
        add(btnCalcular);

        lblResultado = new JLabel("");
        lblResultado.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
        lblResultado.setBounds(240, 460, 500, 40);
        add(lblResultado);
    }

    private void calcularVelocidad() {
        try {
            double amplitud = Double.parseDouble(txtAmplitud.getText());
            double frecuencia = Double.parseDouble(txtFrecuencia.getText());
            double tiempo = Double.parseDouble(txtTiempo.getText());

            double velocidad = amplitud * frecuencia * Mate.coseno(frecuencia * tiempo);
            String velocidadFormateada = String.format("%.4f", velocidad); // Limita a 4 decimales

            lblResultado.setText("Velocidad del cuerpo: " + velocidadFormateada);
        } catch (NumberFormatException ex) {
            lblResultado.setText("Ingrese valores numéricos válidos para la amplitud, frecuencia y tiempo.");
        }
    }

   
}
