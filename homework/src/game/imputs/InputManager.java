package game.imputs;

import java.awt.event.KeyEvent;

/**
 * Created by VALV on 7/20/2017.
 */
public class InputManager {
    public boolean rightpressed;
    public boolean leftpressed;
    public boolean uppressed;
    public boolean downpressed;
    public boolean xpressed;

    public void keyPress(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                rightpressed = true;
                break;
            case KeyEvent.VK_LEFT:
                leftpressed = true;
                break;
            case KeyEvent.VK_UP:
                uppressed = true;
                break;
            case KeyEvent.VK_DOWN:
                downpressed = true;
                break;
            case KeyEvent.VK_X:
                xpressed = true;
                break;
            default:
                break;
        }
    }
    public void keyRelease(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                rightpressed = false;
                break;
            case KeyEvent.VK_LEFT:
                leftpressed = false;
                break;
            case KeyEvent.VK_UP:
                uppressed = false;
                break;
            case KeyEvent.VK_DOWN:
                downpressed = false;
                break;
            case KeyEvent.VK_X:
                xpressed = false;
                break;
            default:
                break;
        }
    }
}
