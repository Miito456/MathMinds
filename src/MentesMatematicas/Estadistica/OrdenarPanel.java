package MentesMatematicas.Estadistica;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.text.AbstractDocument;

public class OrdenarPanel extends JPanel {

    private JTextArea txtDatos;
    private JTextArea txtDatosOrdenados;

    public OrdenarPanel() {
        setLayout(null);
        setPreferredSize(new Dimension(980, 700));

        JLabel lblTitulo = new JLabel("Ordenar Datos");
        lblTitulo.setFont(new Font("Yu Gothic UI", Font.BOLD, 40));
        lblTitulo.setBounds(340, 20, 300, 50);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo);

        JLabel lblIngresarDatos = new JLabel("Ingrese los datos (números reales, separados por espacios o comas):");
        lblIngresarDatos.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
        lblIngresarDatos.setBounds(100, 80, 780, 30);
        lblIngresarDatos.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblIngresarDatos);

        txtDatos = new JTextArea();
        txtDatos.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
        txtDatos.setLineWrap(true); 
        txtDatos.setWrapStyleWord(true); 
        JScrollPane scrollPane = new JScrollPane(txtDatos);
        scrollPane.setBounds(240, 120, 500, 200);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane);

       ((AbstractDocument) txtDatos.getDocument()).setDocumentFilter(new DocumentFilter() {
            private boolean containsOnlyNumbers(String text) {
                return text.matches("^[\\-\\d., ]+$");
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
                String newText = currentText.substring(0, offset) + text + currentText.substring(offset + length);
                if (containsOnlyNumbers(newText)) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }

            @Override
            public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
                String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
                String newText = currentText.substring(0, offset) + text + currentText.substring(offset);
                if (containsOnlyNumbers(newText)) {
                    super.insertString(fb, offset, text, attr);
                }
            }
        });

        JButton btnOrdenar = new JButton("Ordenar");
        btnOrdenar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ordenarDatos();
            }
        });
        btnOrdenar.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        btnOrdenar.setBackground(Color.decode("#2D142C"));
        btnOrdenar.setForeground(Color.WHITE);
        btnOrdenar.setFocusPainted(false);
        btnOrdenar.setBounds(240, 340, 500, 40);
        add(btnOrdenar);

        JLabel lblDatosOrdenados = new JLabel("Datos Ordenados:");
        lblDatosOrdenados.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
        lblDatosOrdenados.setBounds(240, 390, 500, 30);
        lblDatosOrdenados.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblDatosOrdenados);

        txtDatosOrdenados = new JTextArea();
        txtDatosOrdenados.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
        txtDatosOrdenados.setEditable(false); 
        txtDatosOrdenados.setLineWrap(true); 
        txtDatosOrdenados.setWrapStyleWord(true);
        JScrollPane scrollPaneOrdenado = new JScrollPane(txtDatosOrdenados);
        scrollPaneOrdenado.setBounds(240, 430, 500, 200);
        scrollPaneOrdenado.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPaneOrdenado);
    }

    private void ordenarDatos() {
        String[] datos = txtDatos.getText().split("[,\\s]+");
        double[] valores = new double[datos.length];
        for (int i = 0; i < datos.length; i++) {
            try {
                valores[i] = Double.parseDouble(datos[i]);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ingrese valores numéricos válidos.");
                return;
            }
        }

        Arrays.sort(valores);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < valores.length; i++) {
            sb.append(valores[i]);
            if (i < valores.length - 1) {
                sb.append(", ");
            }
        }

        txtDatosOrdenados.setText(sb.toString());
    }

    
}
