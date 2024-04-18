package MentesMatematicas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomMenuBar extends JPanel {

    public CustomMenuBar() {
        initialize();
    }

    private void initialize() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode("#2D142C"));

        JLabel logoLabel = new JLabel(new ImageIcon("Images/logo.png")); 
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(logoLabel);

        JLabel nameLabel = new JLabel("MathMinds");
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(nameLabel);

        String[] menuItems = {"Matematica elemental", "Algebra", "Fisica", "Quimica", "Graficacion"};
        for (String menuItem : menuItems) {
            JButton button = new JButton(menuItem);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setForeground(Color.WHITE);
            button.setBackground(Color.decode("#2D142C"));
            button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            button.setFocusPainted(false);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Seleccionaste: " + menuItem);
                }
            });
            add(button);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, super.getPreferredSize().height);
    }
}
