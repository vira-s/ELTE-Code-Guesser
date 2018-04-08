package hu.edu.elte.codeguesser_model.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Represents a guess.
 *
 * @author virabia on 4/8/2018
 */
public class Guess {

    private static final Logger LOGGER = LoggerFactory.getLogger(Guess.class);

    private final String guess;

    private List<GuessDigitStatusEnum> digitStatusList;

    /**
     * Constructor.
     *
     * @param guess The guess made by a user
     */
    public Guess(String guess) {
        this.guess = guess;
        initializeDigitStatus(guess.length());
    }

    private void initializeDigitStatus(int length) {
        this.digitStatusList = Arrays.asList(new GuessDigitStatusEnum[length]);
        for (int index = 0; index <  length; ++index) {
            this.digitStatusList.set(index, GuessDigitStatusEnum.UNKNOWN);
        }
    }

    public String getGuess() {
        return guess;
    }

    /**
     * Gets the digitStatusList as an unmodifiable list.
     * To modify the list, use the setDigitStatus(int, GuessDigitStatusEnum) method.
     *
     * @return The unmodifiable digitStatusList
     */
    public List<GuessDigitStatusEnum> getDigitStatusList() {
        return Collections.unmodifiableList(digitStatusList);
    }

    /**
     * Sets the digitStatusList's element at the given index to the given status.
     * If the provided status is null, the UNKNOWN status will be set.
     *
     * @param index The index of the element to modify
     * @param guessDigitStatusEnum The new status
     */
    public void setDigitStatus(int index, GuessDigitStatusEnum guessDigitStatusEnum) {
        GuessDigitStatusEnum status = Objects.nonNull(guessDigitStatusEnum)
                ? guessDigitStatusEnum
                : GuessDigitStatusEnum.UNKNOWN;

        if (0 <= index && index < guess.length()) {
            this.digitStatusList.set(index, status);
        } else {
            LOGGER.info("The provided index(" + index + ") is out of range [0;" + guess.length() + ").");
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Guess that = (Guess) other;
        return Objects.equals(guess, that.guess)
                && Objects.equals(digitStatusList, that.digitStatusList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guess, digitStatusList);
    }

    @Override
    public String toString() {
        return "Guess{"
                + "guess='" + guess + '\''
                + ", digitStatusList=" + digitStatusList
                + '}';
    }
}
