package MentesMatematicas.Quimica;

import LibMate.Mate;
import javax.swing.*;
import java.awt.*;

public class EcuacionNernstPanel extends JPanel {

    private JTextField txtConcentracionIon;
    private JTextField txtConcentracionIonEstandar;
    private JTextField txtCargaIon;
    private JTextField txtTemperatura;
    private JTextField txtE0;
    private JLabel lblResultado;

    public EcuacionNernstPanel() {
        setLayout(null);
        setPreferredSize(new Dimension(980, 700));

        JLabel lblTitulo = new JLabel("Ecuación de Nernst");
        lblTitulo.setFont(new Font("Yu Gothic UI", Font.BOLD, 40));
        lblTitulo.setBounds(240, 20, 500, 50);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo);

        JLabel lblImagen = new JLabel(new ImageIcon("Images/fec.png"));
        lblImagen.setBounds(240, 80, 500, 200);
        add(lblImagen);

        JLabel lblConcentracionIon = new JLabel("Concentración del ion:");
        lblConcentracionIon.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        lblConcentracionIon.setBounds(240, 300, 300, 40);
        add(lblConcentracionIon);

        txtConcentracionIon = new JTextField();
        txtConcentracionIon.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        txtConcentracionIon.setBounds(550, 300, 200, 40);
        add(txtConcentracionIon);

        JLabel lblConcentracionIonEstandar = new JLabel("Concentración ion estándar:");
        lblConcentracionIonEstandar.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        lblConcentracionIonEstandar.setBounds(240, 350, 300, 40);
        add(lblConcentracionIonEstandar);

        txtConcentracionIonEstandar = new JTextField();
        txtConcentracionIonEstandar.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        txtConcentracionIonEstandar.setBounds(550, 350, 200, 40);
        add(txtConcentracionIonEstandar);

        JLabel lblCargaIon = new JLabel("Carga del ion:");
        lblCargaIon.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        lblCargaIon.setBounds(240, 400, 300, 40);
        add(lblCargaIon);

        txtCargaIon = new JTextField();
        txtCargaIon.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        txtCargaIon.setBounds(550, 400, 200, 40);
        add(txtCargaIon);

        JLabel lblTemperatura = new JLabel("Temperatura (K):");
        lblTemperatura.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        lblTemperatura.setBounds(240, 450, 300, 40);
        add(lblTemperatura);

        txtTemperatura = new JTextField();
        txtTemperatura.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        txtTemperatura.setBounds(550, 450, 200, 40);
        add(txtTemperatura);

        JLabel lblE0 = new JLabel("Potencial inicial (E0):");
        lblE0.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        lblE0.setBounds(240, 500, 300, 40);
        add(lblE0);

        txtE0 = new JTextField();
        txtE0.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        txtE0.setBounds(550, 500, 200, 40);
        add(txtE0);

        JButton btnResolver = new JButton("Resolver");
        btnResolver.addActionListener(e -> resolverEcuacionNernst());
        btnResolver.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        btnResolver.setBackground(Color.decode("#2D142C"));
        btnResolver.setForeground(Color.WHITE);
        btnResolver.setFocusPainted(false);
        btnResolver.setBounds(240, 570, 500, 40);
        add(btnResolver);

        lblResultado = new JLabel("");
        lblResultado.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
        lblResultado.setBounds(240, 630, 500, 30);
        add(lblResultado);
    }

    private void resolverEcuacionNernst() {
        try {
            double concentracionIon = Double.parseDouble(txtConcentracionIon.getText());
            double concentracionIonEstandar = Double.parseDouble(txtConcentracionIonEstandar.getText());
            double cargaIon = Double.parseDouble(txtCargaIon.getText());
            double temperatura = Double.parseDouble(txtTemperatura.getText());
            double E0 = Double.parseDouble(txtE0.getText());

            double n = cargaIon;
            double Q = concentracionIon / concentracionIonEstandar;
            double resultado = E0 - (0.0592 / n) * Mate.logaritmo(10, Q);
            lblResultado.setText(String.format("Potencial de equilibrio: %.2f V", resultado));
        } catch (NumberFormatException ex) {
            lblResultado.setText("Ingrese valores numéricos válidos.");
        }
    }
}
