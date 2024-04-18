package MentesMatematicas.Formulas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeoremaDeBayesPanel extends JPanel {
    private JTextField txtP_A;
    private JTextField txtP_B;
    private JTextField txtP_B_dado_A;
    private JLabel lblResultado;

    public TeoremaDeBayesPanel() {
        setLayout(null);
        setPreferredSize(new Dimension(980, 700));

        JLabel lblTitulo = new JLabel("Teorema de Bayes");
        lblTitulo.setFont(new Font("Yu Gothic UI", Font.BOLD, 40));
        lblTitulo.setBounds(240, 20, 500, 50);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo);

        JLabel lblInfo = new JLabel("<html>El Teorema de Bayes es una regla fundamental en la teoría de la probabilidad que describe cómo actualizar las creencias en la probabilidad de un evento a medida que se disponga de nueva evidencia.</html>");
        lblInfo.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
        lblInfo.setBounds(240, 80, 500, 100);
        lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblInfo);

        JLabel lblImagen = new JLabel(new ImageIcon("Images/fbayes.png"));
        lblImagen.setBounds(240, 200, 500, 200);
        add(lblImagen);

        JLabel lblP_A = new JLabel("P(A):");
        lblP_A.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
        lblP_A.setBounds(240, 420, 60, 30);
        add(lblP_A);

        txtP_A = new JTextField();
        txtP_A.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
        txtP_A.setBounds(300, 420, 100, 30);
        add(txtP_A);

        JLabel lblP_B = new JLabel("P(B):");
        lblP_B.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
        lblP_B.setBounds(240, 470, 60, 30);
        add(lblP_B);

        txtP_B = new JTextField();
        txtP_B.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
        txtP_B.setBounds(300, 470, 100, 30);
        add(txtP_B);

        JLabel lblP_B_dado_A = new JLabel("P(B|A):");
        lblP_B_dado_A.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
        lblP_B_dado_A.setBounds(240, 520, 80, 30);
        add(lblP_B_dado_A);

        txtP_B_dado_A = new JTextField();
        txtP_B_dado_A.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
        txtP_B_dado_A.setBounds(330, 520, 100, 30);
        add(txtP_B_dado_A);

        JButton btnResolver = new JButton("Resolver");
        btnResolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resolverTeoremaDeBayes();
            }
        });
        btnResolver.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        btnResolver.setBackground(Color.decode("#2D142C"));
        btnResolver.setForeground(Color.WHITE);
        btnResolver.setFocusPainted(false);
        btnResolver.setBounds(240, 570, 500, 40);
        add(btnResolver);

        lblResultado = new JLabel("");
        lblResultado.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
        lblResultado.setBounds(240, 620, 500, 30);
        add(lblResultado);
    }

    private void resolverTeoremaDeBayes() {
        try {
            double pA = Double.parseDouble(txtP_A.getText());
            double pB = Double.parseDouble(txtP_B.getText());
            double pB_dado_A = Double.parseDouble(txtP_B_dado_A.getText());

            double resultado = calcularTeoremaDeBayes(pA, pB, pB_dado_A);

            lblResultado.setText(String.format("P(A|B) = %.4f", resultado));
        } catch (NumberFormatException ex) {
            lblResultado.setText("Ingrese valores numéricos válidos para las probabilidades.");
        }
    }

    private double calcularTeoremaDeBayes(double pA, double pB, double pB_dado_A) {
        return (pB_dado_A * pA) / pB;
    }

  
}
