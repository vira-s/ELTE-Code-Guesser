package hu.edu.elte.codeguesser_view.menu;

import hu.edu.elte.codeguesser_view.action.ActionType;

import javax.swing.Action;
import javax.swing.KeyStroke;
import java.awt.event.ActionListener;

/**
 * @author virabia on 5/15/2018
 */
public class GameModeMenuItem extends WindowMenuItem {

    private static final int DEFAULT_CODE_LENGTH = 5;

    private final ActionListener actionListener;

    private ActionType actionType;

    private int codeLength;

    public GameModeMenuItem(String text, char mnemonic, KeyStroke keyStroke, Action action, ActionType actionType, ActionListener actionListener) {
        super(text, mnemonic, keyStroke, action);
        this.actionListener = actionListener;
        addActionListener(this.actionListener);
        this.actionType = actionType;
        this.codeLength = DEFAULT_CODE_LENGTH;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public int getCodeLength() {
        return codeLength;
    }

    public void setCodeLength(int codeLength) {
        this.codeLength = codeLength;
    }
}
