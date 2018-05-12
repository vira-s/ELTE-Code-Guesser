package hu.edu.elte.codeguesser_view.button;

import hu.edu.elte.codeguesser_view.action.ActionType;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * @author virabia on 5/12/2018
 */
public class GameButton extends JButton {

    private final ActionType buttonActionType;

    private final ActionListener actionListener;

    public GameButton(ActionType buttonType, ActionListener actionListener) {
        this.buttonActionType = buttonType;
        this.actionListener = actionListener;

        addActionListener(this.actionListener);
        setText(this.buttonActionType.getText());
//        setAction(new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent event) {
//                actionListener.actionPerformed();
//            }
//        });
    }

    public ActionType getButtonActionType() {
        return buttonActionType;
    }

    public ActionListener getActionListener() {
        return actionListener;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        GameButton that = (GameButton) other;
        return buttonActionType == that.buttonActionType
                && Objects.equals(actionListener, that.actionListener);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buttonActionType, actionListener);
    }

    @Override
    public String toString() {
        return "GameButton{"
                + "buttonActionType=" + buttonActionType
                + ", actionListener=" + actionListener
                + '}';
    }
}
