package srimani7.javajungle.guess;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * This DigitKeyAdapter class can be used as an event listener in Swing components that require input of only digits, such as text fields or number fields, to restrict the input to numeric values only.
 * <br>
 * It can be added to a Swing component using the addKeyListener() method, and the keyTyped() method will be automatically invoked when a key is typed by the user in the associated component.
 */
public class DigitKeyAdapter extends KeyAdapter {
    /**
     * Consumes this event if the char is not digit, so that it will not be processed in the default manner by the source which originated it.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {
        super.keyTyped(e);
        char c = e.getKeyChar();
        if (!Character.isDigit(c) ||
                c == KeyEvent.VK_BACK_SLASH ||
                c == KeyEvent.VK_DELETE)
            e.consume();
    }
}
