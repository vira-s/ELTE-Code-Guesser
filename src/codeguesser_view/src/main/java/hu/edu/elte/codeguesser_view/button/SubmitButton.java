package hu.edu.elte.codeguesser_view.button;

import hu.edu.elte.codeguesser_view.action.ActionType;

import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * @author virabia on 5/13/2018
 */
public class SubmitButton extends GameButton {

    private String guessToSend;

    public SubmitButton(ActionType buttonType, ActionListener actionListener) {
        super(buttonType, actionListener);
    }

    public String getGuessToSend() {
        return guessToSend;
    }

    public void setGuessToSend(String guessToSend) {
        this.guessToSend = guessToSend;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        if (!super.equals(other)) return false;
        SubmitButton that = (SubmitButton) other;
        return Objects.equals(buttonActionType, buttonActionType)
                && Objects.equals(guessToSend, that.guessToSend);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), guessToSend);
    }

    @Override
    public String toString() {
        return "SubmitButton{"
                + "guessToSend='" + guessToSend + '\''
                + ", buttonActionType=" + buttonActionType
                + ", actionListener=" + actionListener
                + '}';
    }
}
