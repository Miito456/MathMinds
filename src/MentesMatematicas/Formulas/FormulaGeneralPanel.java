package MentesMatematicas.Formulas;

import Components.InfoLabel;
import Components.NumericTextField;
import Components.TitleLabel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import LibMate.Mate;

public class FormulaGeneralPanel extends JPanel {

    private JTextField txtA;
    private JTextField txtB;
    private JTextField txtC;
    private JLabel lblResultado;

    public FormulaGeneralPanel() {
        setLayout(null);
        setPreferredSize(new Dimension(980, 700));

        TitleLabel lblTitulo = new TitleLabel("Fórmula General");
        lblTitulo.setBounds(240, 20, 500, 50);
        add(lblTitulo);

        InfoLabel lblInfo = new InfoLabel("<html>La fórmula general se utiliza para resolver ecuaciones cuadráticas de la forma:</html>");
        lblInfo.setBounds(240, 80, 500, 100);
        add(lblInfo);

        JLabel lblImagen = new JLabel(new ImageIcon("Images/fgeneral.png"));
        lblImagen.setBounds(240, 200, 500, 200);
        add(lblImagen);

        InfoLabel lblIngresarValores = new InfoLabel("Ingrese los valores de a, b y c para calcular las soluciones:");
        lblIngresarValores.setBounds(240, 420, 500, 30);
        add(lblIngresarValores);

        JLabel lblA = new JLabel("Valor de a:");
        lblA.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        lblA.setBounds(240, 460, 120, 40);
        add(lblA);

        txtA = new NumericTextField();
        txtA.setBounds(370, 460, 100, 40);
        add(txtA);

        JLabel lblB = new JLabel("Valor de b:");
        lblB.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        lblB.setBounds(240, 510, 120, 40);
        add(lblB);

        txtB = new NumericTextField();
        txtB.setBounds(370, 510, 100, 40);
        add(txtB);

        JLabel lblC = new JLabel("Valor de c:");
        lblC.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        lblC.setBounds(240, 560, 120, 40);
        add(lblC);

        txtC = new NumericTextField();
        txtC.setBounds(370, 560, 100, 40);
        add(txtC);

        JButton btnResolver = new JButton("Resolver");
        btnResolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resolverFormulaGeneral();
            }
        });
        btnResolver.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        btnResolver.setBackground(Color.decode("#2D142C"));
        btnResolver.setForeground(Color.WHITE);
        btnResolver.setFocusPainted(false);
        btnResolver.setBounds(240, 620, 500, 40);
        add(btnResolver);

        lblResultado = new JLabel("");
        lblResultado.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
        lblResultado.setBounds(240, 670, 500, 30);
        add(lblResultado);
    }

    private void resolverFormulaGeneral() {
        try {
            double a = Double.parseDouble(txtA.getText());
            double b = Double.parseDouble(txtB.getText());
            double c = Double.parseDouble(txtC.getText());

            double discriminante = b * b - 4 * a * c;
            if (discriminante > 0) {
                double x1 = (-b + Mate.raizCuadrada(discriminante)) / (2 * a);
                double x2 = (-b - Mate.raizCuadrada(discriminante)) / (2 * a);
                lblResultado.setText(String.format("x1 = %.2f, x2 = %.2f", x1, x2));
            } else if (discriminante == 0) {
                double x = -b / (2 * a);
                lblResultado.setText(String.format("x = %.2f (solución única)", x));
            } else {
                lblResultado.setText("No hay soluciones reales");
            }
        } catch (NumberFormatException ex) {
            lblResultado.setText("Ingrese valores numéricos válidos para a, b y c.");
        }
    }

  
}
