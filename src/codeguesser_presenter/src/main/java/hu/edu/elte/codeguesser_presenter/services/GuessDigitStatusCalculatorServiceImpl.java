package hu.edu.elte.codeguesser_presenter.services;

import hu.edu.elte.codeguesser_model.GuessDigitStatusEnum;
import hu.edu.elte.codeguesser_presenter.utils.GameMode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author virabia on 5/12/2018
 */
public class GuessDigitStatusCalculatorServiceImpl implements GuessDigitStatusCalculatorService {

    private static final char MATCHED_DIGIT_MARKER = 'X';

    @Override
    public List<GuessDigitStatusEnum> calculateGuessDigitStatus(GameMode gameMode, String secretCode, String guess) {
        validateCodes(secretCode, guess);

        List<GuessDigitStatusEnum> guessDigitStatuses = new ArrayList<>();

        switch (gameMode) {
            case EASY:
                guessDigitStatuses = calculateDigitStatusEasy(secretCode, guess, GuessDigitStatusEnum.WRONG);
                break;
            case MEDIUM:
                guessDigitStatuses = calculateDigitStatusMedium(secretCode, guess, GuessDigitStatusEnum.WRONG);
                break;
            case HARD:
                guessDigitStatuses = calculateDigitStatusHard(secretCode, guess,  GuessDigitStatusEnum.UNKNOWN);
                break;
            default:
                break;
        }

        return guessDigitStatuses;
    }

    private List<GuessDigitStatusEnum> calculateDigitStatusHard(String secretCode, String guess, GuessDigitStatusEnum statusForMismatch) {
        Map<String, Map<String, List<GuessDigitStatusEnum>>> result =
                findCorrectlyPlacedDigits(secretCode, guess, statusForMismatch);

        List<GuessDigitStatusEnum> guessDigitStatuses = extractDigitStatuses(result);

        return guessDigitStatuses;
    }

    private List<GuessDigitStatusEnum> calculateDigitStatusMedium(String secretCode, String guess, GuessDigitStatusEnum statusForMismatch) {
        Map<String, Map<String, List<GuessDigitStatusEnum>>> result = findContainingDigits(secretCode, guess, statusForMismatch);

        List<GuessDigitStatusEnum> guessDigitStatuses = extractDigitStatuses(result);

        return guessDigitStatuses;
    }

    private List<GuessDigitStatusEnum> calculateDigitStatusEasy(String secretCode, String guess, GuessDigitStatusEnum statusForMismatch) {
        Map<String, Map<String, List<GuessDigitStatusEnum>>> result =
                findCorrectlyPlacedDigits(secretCode, guess, GuessDigitStatusEnum.UNKNOWN);

        secretCode = extractSecretCode(result);
        guess = extractGuessBySecretCode(result);
        List<GuessDigitStatusEnum> guessDigitStatuses = extractDigitStatuses(result)
                .stream()
                .filter(status -> status != GuessDigitStatusEnum.UNKNOWN)
                .collect(Collectors.toList());

        result = findContainingDigits(secretCode, guess, statusForMismatch);
        guessDigitStatuses.addAll(extractDigitStatuses(result));

        return guessDigitStatuses;
    }

    private Map<String, Map<String, List<GuessDigitStatusEnum>>> findCorrectlyPlacedDigits(String secretCode,
                                                                                           String guess,
                                                                                           GuessDigitStatusEnum statusForMismatch) {
        int codeLength = secretCode.length();
        List<GuessDigitStatusEnum> guessDigitStatuses = new ArrayList<>();

        for (int i = 0; i < codeLength; ++i) {
            String currentSecretDigit = String.valueOf(secretCode.charAt(i));
            String currentGuessDigit = String.valueOf(guess.charAt(i));

            if (currentGuessDigit.equals(currentSecretDigit)) {
                secretCode = replaceDigitWithMatchedMarkerAtIndex(secretCode, i);
                guess = replaceDigitWithMatchedMarkerAtIndex(guess, i);

                guessDigitStatuses.add(GuessDigitStatusEnum.CORRECT_NUMBER_AND_CORRECT_PLACEMENT);
            } else {
                guessDigitStatuses.add(statusForMismatch);
            }
        }

        Map<String, List<GuessDigitStatusEnum>> digitStatusesByGuess = new HashMap<>();
        digitStatusesByGuess.put(guess, guessDigitStatuses);

        Map<String, Map<String, List<GuessDigitStatusEnum>>> secretCodeWithGuessAndStatuses = new HashMap<>();
        secretCodeWithGuessAndStatuses.put(secretCode, digitStatusesByGuess);

        return secretCodeWithGuessAndStatuses;
    }

    private Map<String, Map<String, List<GuessDigitStatusEnum>>> findContainingDigits(String secretCode,
                                                                                      String guess,
                                                                                      GuessDigitStatusEnum statusForMismatch) {
        int codeLength = secretCode.length();
        List<GuessDigitStatusEnum> guessDigitStatuses = new ArrayList<>();

        for (int i = 0; i < codeLength; ++i) {
            String currentGuessDigit = String.valueOf(guess.charAt(i));

            if (!currentGuessDigit.equals(String.valueOf(MATCHED_DIGIT_MARKER))
                    && secretCode.contains(currentGuessDigit)) {
                secretCode = replaceDigitWithMatchedMarkerAtIndex(secretCode, secretCode.indexOf(currentGuessDigit));
                guess = replaceDigitWithMatchedMarkerAtIndex(guess, i);

                guessDigitStatuses.add(GuessDigitStatusEnum.CORRECT_NUMBER_AND_WRONG_PLACEMENT);

            } else if (!currentGuessDigit.equals(String.valueOf(MATCHED_DIGIT_MARKER))
                    && !secretCode.contains(currentGuessDigit)) {
                guessDigitStatuses.add(statusForMismatch);
            }
        }

        Map<String, List<GuessDigitStatusEnum>> digitStatusesByGuess = new HashMap<>();
        digitStatusesByGuess.put(guess, guessDigitStatuses);

        Map<String, Map<String, List<GuessDigitStatusEnum>>> secretCodeWithGuessAndStatuses = new HashMap<>();
        secretCodeWithGuessAndStatuses.put(secretCode, digitStatusesByGuess);

        return secretCodeWithGuessAndStatuses;
    }


    private String extractSecretCode(Map<String, Map<String, List<GuessDigitStatusEnum>>> result) {
        return result.keySet()
                .stream()
                .findFirst()
                .get();
    }

    private String extractGuessBySecretCode(Map<String, Map<String, List<GuessDigitStatusEnum>>> result) {
        Map<String, List<GuessDigitStatusEnum>> statusesByGuess = result.get(extractSecretCode(result));
        return statusesByGuess.keySet()
                .stream()
                .findFirst()
                .get();
    }

    private List<GuessDigitStatusEnum> extractDigitStatuses(Map<String, Map<String, List<GuessDigitStatusEnum>>> result) {
        String secretCode = extractSecretCode(result);
        Map<String, List<GuessDigitStatusEnum>> digitStatusesByGuess = result.get(secretCode);

        return digitStatusesByGuess.isEmpty()
                ? Collections.emptyList()
                : digitStatusesByGuess.get(extractGuessBySecretCode(result));

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
