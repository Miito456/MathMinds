package MentesMatematicas.Formulas;

import LibMate.Mate;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FuncionGammaPanel extends JPanel {

    private JTextField txtX;
    private JLabel lblResultado;

    public FuncionGammaPanel() {
        setLayout(null);
        setPreferredSize(new Dimension(980, 700));

        JLabel lblTitulo = new JLabel("Función Gamma");
        lblTitulo.setFont(new Font("Yu Gothic UI", Font.BOLD, 40));
        lblTitulo.setBounds(240, 20, 500, 50);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo);

        JLabel lblInfo = new JLabel("<html>La función gamma es una función matemática que generaliza el concepto de factorial a números reales y complejos.</html>");
        lblInfo.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
        lblInfo.setBounds(240, 80, 500, 100);
        lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblInfo);

        JLabel lblImagen = new JLabel(new ImageIcon("Images/fgamma.png"));
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
                resolverFuncionGamma();
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

    private void resolverFuncionGamma() {
        try {
            double x = Double.parseDouble(txtX.getText().trim());
            double resultado = Mate.calcularFuncionGamma(x);
            lblResultado.setText(String.format("Γ(%.2f) = %.4f", x, resultado));
        } catch (NumberFormatException ex) {
            lblResultado.setText("Ingrese un valor numérico válido para x.");
        }
    }

   
}
