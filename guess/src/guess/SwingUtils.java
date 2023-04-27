package guess;

import java.awt.Dimension;

/**
 * This interface provides utility methods for Swing components.
 */
public interface SwingUtils {
    /**
     * Creates a new `Dimension` object with the specified width and height.
     *
     * @param width  The width of the `Dimension` object.
     * @param height The height of the `Dimension` object.
     * @return A new `Dimension` object with the specified width and height.
     */
    static Dimension dimen(int width, int height) {
        return new Dimension(width, height);
    }
}
