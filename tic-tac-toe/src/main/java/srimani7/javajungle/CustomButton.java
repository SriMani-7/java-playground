package srimani7.javajungle;

import javax.swing.*;
import java.awt.*;

/**
 * CustomButton is a class that extends JButton to provide additional customization options.
 * It allows setting the background color, foreground color, size, font, and padding for the button.
 * This class also provides an option to enable/disable default ripple effect for the button.
 */
public class CustomButton extends JButton {

    /**
     * Constructs a CustomButton with the specified text and ripple effect setting.
     *
     * @param text   the text to be displayed on the button
     * @param ripple true to enable ripple effect, false otherwise
     */
    public CustomButton(String text, boolean ripple) {
        super(text);
        setFocusPainted(false);
        setContentAreaFilled(ripple);
        setBorderPainted(false);
        setOpaque(true);
    }

    /**
     * Sets the background color and foreground color for the button.
     *
     * @param bg         the background color
     * @param foreground the foreground color
     */
    public void setColors(Color bg, Color foreground) {
        setBackground(bg);
        setForeground(foreground);
    }

    /**
     * Sets the size of the button.
     *
     * @param size the width and height of the button
     */
    public void setSize(int size) {
        setSize(size, size);
    }

    /**
     * Sets the font for the button.
     *
     * @param name  the font name
     * @param style the font style (e.g. Font.BOLD)
     * @param size  the font size
     */
    public void setFont(String name, int style, int size) {
        setFont(new Font(name, style, size));
    }

    /**
     * Sets the font style and size for the button.
     *
     * @param style the font style (e.g. Font.BOLD)
     * @param size  the font size
     */
    public void setFont(int style, int size) {
        setFont(getFont().deriveFont(style, size));
    }

    /**
     * Sets the padding for the button.
     *
     * @param horizontal the horizontal padding
     * @param vertical   the vertical padding
     */
    public void padding(int horizontal, int vertical) {
        setBorder(BorderFactory.createEmptyBorder(vertical, horizontal, vertical, horizontal));
    }
}