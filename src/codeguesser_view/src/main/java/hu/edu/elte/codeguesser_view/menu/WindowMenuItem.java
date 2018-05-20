package hu.edu.elte.codeguesser_view.menu;

import javax.swing.Action;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * @author virabia on 5/13/2018
 */
public class WindowMenuItem extends JMenuItem {

    public WindowMenuItem(String text, char mnemonic, KeyStroke keyStroke, Action action) {
        setAction(action);
        initMenuItem(text, mnemonic, keyStroke);
    }

    private void initMenuItem(String text, char mnemonic, KeyStroke keyStroke) {
        setText(text);
        setMnemonic(mnemonic);
        setAccelerator(keyStroke);
    }
}
