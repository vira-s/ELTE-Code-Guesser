package hu.edu.elte.codeguesser_view.listeners;

import hu.edu.elte.codeguesser_view.action.ActionType;
import hu.edu.elte.codeguesser_view.button.GameButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author virabia on 5/10/2018
 */
public interface CustomActionListener extends ActionListener {

    @Override
    default void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if (source instanceof GameButton) {
            GameButton sourceButton = (GameButton) source;

            this.actionPerformed(sourceButton.getButtonActionType());
        }

    }

    void actionPerformed(ActionType actionType);
}
