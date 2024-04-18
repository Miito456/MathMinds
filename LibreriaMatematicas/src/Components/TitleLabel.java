package Components;

import javax.swing.*;
import java.awt.*;

public class TitleLabel extends JLabel {

    public TitleLabel(String text) {
        super(text);
        customize();
    }

    private void customize() {
        setFont(new Font("Yu Gothic UI", Font.BOLD, 40));
        setHorizontalAlignment(SwingConstants.CENTER);
    }
}
