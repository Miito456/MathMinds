package Components;

import javax.swing.*;
import java.awt.*;

public class CustomLabel extends JLabel {

    public CustomLabel(String text, int x, int y, int width, int height, Font font) {
        super(text);
        setBounds(x, y, width, height);
        setFont(font);
        setHorizontalAlignment(SwingConstants.CENTER);
    }
}
    