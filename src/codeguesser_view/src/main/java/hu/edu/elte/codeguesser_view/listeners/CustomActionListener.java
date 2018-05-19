package hu.edu.elte.codeguesser_view.listeners;

import hu.edu.elte.codeguesser_view.action.ActionType;
import hu.edu.elte.codeguesser_view.button.GameButton;
import hu.edu.elte.codeguesser_view.button.SubmitButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author virabia on 5/10/2018
 */
public interface CustomActionListener extends ActionListener, Action {

    @Override
    default void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if (source instanceof SubmitButton) {
            SubmitButton sourceButton = (SubmitButton) source;
            this.actionPerformed(sourceButton.getButtonActionType(), sourceButton.getGuessToSend());
        } else if (source instanceof GameButton) {
            GameButton sourceButton = (GameButton) source;
            this.actionPerformed(sourceButton.getButtonActionType());
        }
    }

    void actionPerformed(ActionType actionType);

    void actionPerformed(ActionType actionType, String guess);

    void actionPerformed(ActionType actionType, int codeLength);
}