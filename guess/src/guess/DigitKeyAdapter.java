package guess;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DigitKeyAdapter extends KeyAdapter {
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
