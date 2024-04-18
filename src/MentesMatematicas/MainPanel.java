
package MentesMatematicas;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    public MainPanel() {
        initialize();
    }

    private void initialize() {
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Seleccionaste:");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setForeground(Color.WHITE);
        add(label, BorderLayout.CENTER);

        setBackground(Color.decode("#801336"));
    }
}
