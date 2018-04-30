package hu.edu.elte.codeguesser_controller;

import hu.edu.elte.codeguesser_model.model.Guess;
import hu.edu.elte.codeguesser_model.model.GuessDigitStatusEnum;
import org.apache.logging.log4j.util.Strings;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Calculates the state of a Guess.
 *
 * @author virabia on 4/30/2018
 */
public class GuessStateCalculatorService {

    /**
     * Calculates the state of the given Guess compared to the original number.
     *
     * @param originalNumber The original number
     * @param guess The guess
     *
     * @return The Guess with updated status
     */
    public Guess calculateGuessState(String originalNumber, Guess guess) {
        Assert.isTrue(!Objects.isNull(guess)
                && !Strings.isBlank(guess.getGuess())
                && !Objects.isNull(guess.getDigitStatusList()), "Guess should not be empty.");

        return Guess.from(guess.getGuess(), calculateNewGuessState(originalNumber, guess.getGuess()));
    }

    private List<GuessDigitStatusEnum> calculateNewGuessState(String originalNumber, String guess) {
        List<GuessDigitStatusEnum> newDigitStatusList =
                Arrays.asList(new GuessDigitStatusEnum[originalNumber.length()]);

        if (originalNumber.equals(guess)) {
            newDigitStatusList =
                    Collections.nCopies(originalNumber.length(), GuessDigitStatusEnum.CORRECT_NUMBER_AND_PLACEMENT);
        } else {
            for (int index = 0; index < guess.length(); ++index) {
                boolean containsChar = originalNumber.contains(Character.toString(guess.charAt(index)));
                boolean isCharAtTheRightPlace = originalNumber.indexOf(guess.charAt(index)) == index;

                if (containsChar && isCharAtTheRightPlace) {
                    newDigitStatusList.set(index, GuessDigitStatusEnum.CORRECT_NUMBER_AND_PLACEMENT);
                } else if (containsChar) {
                    newDigitStatusList.set(index, GuessDigitStatusEnum.CORRECT_NUMBER);
                } else {
                    newDigitStatusList.set(index, GuessDigitStatusEnum.WRONG);
                }
            }
        }

        return newDigitStatusList;
    }

}
