package hu.edu.elte.codeguesser_view.utils;

/**
 * @author virabia on 5/20/2018
 */
public enum PropertyName {

    GAME_MODE("gameMode"),

    REMAINING_GUESSES_COUNT("remainingGuessCount"),

    CODE_LENGTH("codeLength"),

    STATUS("status"),

    GAME_OVER("gameOver")
    ;

    private final String text;

    PropertyName(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static PropertyName fromText(String text) throws Exception {
        for (PropertyName name : PropertyName.values()) {
            if (text.equals(name.getText())) {
                return name;
            }
        }
        throw new Exception("Unmatched property name: " + text);
    }

    @Override
    public String toString() {
        return "PropertyName{"
                + this.name()
                + "}";
    }
}
