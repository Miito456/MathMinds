package Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class NumericTextField extends JTextField {
    
    public NumericTextField() {
        super();
        customize();
    }
    
    private void customize() {
        setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != '-' && c != '.') {
                    e.consume(); 
                }
            }
        });
    }
}
