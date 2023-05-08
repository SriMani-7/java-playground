package tictactoe;

import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton {

    public CustomButton(String text, boolean ripple) {
        super(text);
        setFocusPainted(false);
        setContentAreaFilled(ripple);
        setBorderPainted(false);
        setOpaque(true);
    }

    public void setColors(Color bg, Color foreground) {
        setBackground(bg);
        setForeground(foreground);
    }

    public void setSize(int size) {
        setSize(size, size);
    }

    public void setFont(String name, int style, int size) {
        setFont(new Font(name, style, size));
    }

    public void setFont(int style, int size) {
        setFont(getFont().deriveFont(style, size));
    }

    public void padding(int horizontal, int vertical) {
        setBorder(BorderFactory.createEmptyBorder(vertical, horizontal, vertical, horizontal));
    }
}