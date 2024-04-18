package Components;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton {

    public Button(String text, int x, int y, int width, int height) {
        super(text);
        setBounds(x, y, width, height);
        setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        setBackground(Color.decode("#2D142C"));
        setForeground(Color.WHITE);
        setFocusPainted(false);
    }
}
