package hu.edu.elte.codeguesser_presenter.services;

import hu.edu.elte.codeguesser_model.GuessDigitStatusEnum;

import java.util.List;

/**
 * @author virabia on 5/10/2018
 */
public interface GuessDigitStatusCalculatorService {

    /**
     * Calculates the status of each digit in the given guess compared to the current secret code.
     *
     * @param secretCode The secret code
     * @param guess The guess
     *
     * @return The list of statuses
     */
    List<GuessDigitStatusEnum> calculateGuessDigitStatus(String secretCode, String guess);

}
