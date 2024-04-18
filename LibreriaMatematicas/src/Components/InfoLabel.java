package Components;

import javax.swing.*;
import java.awt.*;

public class InfoLabel extends JLabel {
    
    public InfoLabel(String text) {
        super(text);
        customize();
    }
    
    private void customize() {
        setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
        setHorizontalAlignment(SwingConstants.CENTER);
    }
}
