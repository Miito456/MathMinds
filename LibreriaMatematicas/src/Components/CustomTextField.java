package Components;

import javax.swing.*;
import java.awt.*;

public class CustomTextField extends JTextField {

    public CustomTextField(int columns, int x, int y, int width, int height, Font font) {
        super(columns);
        setBounds(x, y, width, height);
        setFont(font);
    }
}
