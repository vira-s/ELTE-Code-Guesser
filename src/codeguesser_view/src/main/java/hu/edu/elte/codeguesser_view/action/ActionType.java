package hu.edu.elte.codeguesser_view.action;

/**
 * @author virabia on 5/12/2018
 */
public enum ActionType {

    /**
     * Exit game.
     */
    EXIT("Exit"),

    /**
     * Guess is submitted.
     */
    SUBMIT("Submit"),

    /**
     * Terminate the current game.
     */
    GIVE_UP("Give Up"),

    /**
     * Start new game in EASY mode.
     */
    NEW_GAME_EASY("New Game - Easy"),

    /**
     * Start new game in MEDIUM mode.
     */
    NEW_GAME_MEDIUM("New Game - Medium"),

    /**
     * Start new game in HARD mode.
     */
    NEW_GAME_HARD("New Game - Hard");

    private final String text;

    ActionType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "ActionType{"
                + this.name()
                + "}";
    }
}
