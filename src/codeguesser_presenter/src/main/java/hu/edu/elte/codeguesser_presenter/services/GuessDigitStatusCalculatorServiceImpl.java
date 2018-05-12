package hu.edu.elte.codeguesser_presenter.services;

import hu.edu.elte.codeguesser_model.GuessDigitStatusEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * @author virabia on 5/12/2018
 */
public class GuessDigitStatusCalculatorServiceImpl implements GuessDigitStatusCalculatorService {

    private static final char MATCHED_DIGIT_MARKER = 'X';

    @Override
    public List<GuessDigitStatusEnum> calculateGuessDigitStatus(String secretCode, String guess) {
        validateCodes(secretCode, guess);

        int codeLength = secretCode.length();
        List<GuessDigitStatusEnum> guessDigitStatuses = new ArrayList<>();

        for (int i = 0; i < codeLength; ++i) {
            String currentSecretDigit = String.valueOf(secretCode.charAt(i));
            String currentGuessDigit = String.valueOf(guess.charAt(i));

            if (currentGuessDigit.equals(currentSecretDigit)) {
                secretCode = replaceDigitWithMatchedMarkerAtIndex(secretCode, i);
                guess = replaceDigitWithMatchedMarkerAtIndex(guess, i);

                guessDigitStatuses.add(GuessDigitStatusEnum.CORRECT_NUMBER_AND_CORRECT_PLACEMENT);
            }
        }

        for (int i = 0; i < codeLength; ++i) {
            String currentGuessDigit = String.valueOf(guess.charAt(i));

            if (!currentGuessDigit.equals("X") && secretCode.contains(currentGuessDigit)) {
                secretCode = replaceDigitWithMatchedMarkerAtIndex(secretCode, secretCode.indexOf(currentGuessDigit));
                guess = replaceDigitWithMatchedMarkerAtIndex(guess, i);

                guessDigitStatuses.add(GuessDigitStatusEnum.CORRECT_NUMBER_AND_WRONG_PLACEMENT);

            } else if (!currentGuessDigit.equals("X") && !secretCode.contains(currentGuessDigit)) {
                guessDigitStatuses.add(GuessDigitStatusEnum.WRONG);
            }
        }

        return guessDigitStatuses;
    }

    private String replaceDigitWithMatchedMarkerAtIndex(String code, int index) {
        StringBuilder codeBuilder = new StringBuilder(code);
        codeBuilder.setCharAt(index, MATCHED_DIGIT_MARKER);

        return codeBuilder.toString();
    }

    private void validateCodes(String secretCode, String guess) {
        Assert.isTrue(StringUtils.isNotBlank(secretCode), "Secret code must not be blank.");
        Assert.isTrue(StringUtils.isNotBlank(guess), "Guess must not be blank.");
        Assert.isTrue(secretCode.length() == guess.length(), "Guess must be exactly as long as the secret code. "
                + "Expected: " + secretCode.length() + " Actual: " + guess.length());
    }

}
