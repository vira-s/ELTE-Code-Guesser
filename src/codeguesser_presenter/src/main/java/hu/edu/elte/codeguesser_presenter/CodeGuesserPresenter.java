package hu.edu.elte.codeguesser_presenter;

import hu.edu.elte.codeguesser_model.GuessDigitStatusEnum;
import hu.edu.elte.codeguesser_presenter.services.GuessDigitStatusCalculatorService;
import hu.edu.elte.codeguesser_presenter.services.GuessDigitStatusCalculatorServiceImpl;
import hu.edu.elte.codeguesser_presenter.services.SecretCodeGeneratorService;
import hu.edu.elte.codeguesser_presenter.services.SecretCodeGeneratorServiceImpl;
import hu.edu.elte.codeguesser_presenter.utils.GameMode;
import hu.edu.elte.codeguesser_view.utils.PropertyName;
import hu.edu.elte.codeguesser_view.action.ActionType;
import hu.edu.elte.codeguesser_view.listeners.CustomActionListener;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.AbstractAction;
import java.util.List;

import static hu.edu.elte.codeguesser_model.GuessDigitStatusEnum.CORRECT_PLACEMENT;

/**
 * @author virabia on 5/10/2018
 */
public class CodeGuesserPresenter extends AbstractAction implements CustomActionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(CodeGuesserPresenter.class);

    private static final String OLD_VALUE_MARKER = "_OLD";

    private final GuessDigitStatusCalculatorService guessDigitStatusCalculatorService;

    private final SecretCodeGeneratorService secretCodeGeneratorService;

    private GameMode gameMode;

    private String secretCode;

    private int remainingGuessCount;

    private String status;

    private int codeLength;

    private String gameOver = "";

    public CodeGuesserPresenter() {
        this.guessDigitStatusCalculatorService = new GuessDigitStatusCalculatorServiceImpl();
        this.secretCodeGeneratorService = new SecretCodeGeneratorServiceImpl();
    }

    @Override
    public void actionPerformed(ActionType actionType) {
        switch (actionType) {
            case EXIT:
                LOGGER.info(ActionType.EXIT.getText() + " was pressed.");
                System.exit(0);
                break;
            case GIVE_UP:
                LOGGER.info(ActionType.GIVE_UP.getText() + " was pressed.");
                setGameOver("Game over. You lose... \nThe correct code: " + secretCode);
                setCodeLength(0);
                break;
            default:
                break;
        }
    }

    @Override
    public void actionPerformed(ActionType actionType, String guess) {
        switch (actionType) {
            case SUBMIT:
                LOGGER.info(ActionType.SUBMIT.getText() + " was pressed.");
                List<GuessDigitStatusEnum> result =
                        guessDigitStatusCalculatorService.calculateGuessDigitStatus(gameMode, secretCode, guess);
                String statusString = parseStatus(result);
                setStatus(statusString);
                decrementRemainingGuessCount();
                if (containsOnlyGoodGuesses(result)) {
                    LOGGER.info("GAME OVER WIN");
                    setGameOver("You win!");
                    setCodeLength(0);

                } else if (remainingGuessCount == 0 && !result.contains(CORRECT_PLACEMENT)) {
                    LOGGER.info("GAME OVER LOSE");
                    setGameOver("Game over. You lose... \nThe correct code: " + secretCode);
                    setCodeLength(0);
                }
                LOGGER.info("SecretCode: " + secretCode + ", Guess: " + guess + ", Status: " + result);
                break;
            default:
                break;
        }
    }

    @Override
    public void actionPerformed(ActionType actionType, int codeLength) {
        remainingGuessCount = calculateRemainingGuesses(codeLength);
        secretCode = secretCodeGeneratorService.generateSecretCode(codeLength);
        gameOver = "";
        this.codeLength = 0;
        switch (actionType) {
            case NEW_GAME_EASY:
                LOGGER.info("Received actionType=" + actionType + ", codeLength=" + codeLength);
                LOGGER.info(ActionType.NEW_GAME_EASY.getText() + " was pressed.");
                gameMode = GameMode.EASY;
                firePropertyChange(PropertyName.GAME_MODE.getText(), null, gameMode);
                LOGGER.info("Generated secret code=" + secretCode);
                LOGGER.info("Remaining guesses: " + remainingGuessCount);
                break;
            case NEW_GAME_MEDIUM:
                LOGGER.info("Received actionType=" + actionType + ", codeLength=" + codeLength);
                LOGGER.info(ActionType.NEW_GAME_MEDIUM.getText() + " was pressed.");
                gameMode = GameMode.MEDIUM;
                firePropertyChange(PropertyName.GAME_MODE.getText(), null, gameMode);
                LOGGER.info("Generated secret code=" + secretCode);
                LOGGER.info("Remaining guesses: " + remainingGuessCount);
                break;
            case NEW_GAME_HARD:
                LOGGER.info("Received actionType=" + actionType + ", codeLength=" + codeLength);
                LOGGER.info(ActionType.NEW_GAME_HARD.getText() + " was pressed.");
                gameMode = GameMode.HARD;
                firePropertyChange(PropertyName.GAME_MODE.getText(), null, gameMode);
                LOGGER.info("Generated secret code=" + secretCode);
                LOGGER.info("Remaining guesses: " + remainingGuessCount);
                break;
            default:
                break;
        }
        firePropertyChange(PropertyName.REMAINING_GUESSES_COUNT.getText(), null, remainingGuessCount);
        setCodeLength(codeLength);
    }

    private int calculateRemainingGuesses(int codeLength) {
        return 2*codeLength + codeLength/2;
    }


    private void setCodeLength(int codeLength) {
        int oldValue = this.codeLength;
        this.codeLength = codeLength;
        firePropertyChange(PropertyName.CODE_LENGTH.getText(), oldValue, codeLength);
    }

    private void setStatus(String status) {
        String oldValue = StringUtils.isBlank(this.status)
                ? OLD_VALUE_MARKER
                : this.status.concat("_OLD");
        this.status = status;
        firePropertyChange(PropertyName.STATUS.getText(), oldValue, status);
    }

    private void decrementRemainingGuessCount() {
        int old = remainingGuessCount;
        remainingGuessCount--;
        firePropertyChange(PropertyName.REMAINING_GUESSES_COUNT.getText(), old, remainingGuessCount);
    }

    private String parseStatus(List<GuessDigitStatusEnum> status) {
        StringBuilder statusString = new StringBuilder("");

        int goodCount = 0;
        int containsCount = 0;
        int wrongCount = 0;
        int unknownCount = 0;
        for(GuessDigitStatusEnum elem : status) {
            switch (elem) {
                case CORRECT_PLACEMENT: {
                    goodCount++;
                    break;
                }
                case CONTAINING_NUMBER: {
                    containsCount++;
                    break;
                }
                case WRONG: {
                    wrongCount++;
                    break;
                }
                case UNKNOWN: {
                    unknownCount++;
                }
            }
        }
        if(goodCount > 0) {
            statusString.append("Good: ").append(goodCount).append(", ");
        }
        if(containsCount > 0) {
            statusString.append("Contains: ").append(containsCount).append(", ");
        }
        if(wrongCount > 0) {
            statusString.append("Bad: ").append(wrongCount);
        }
        if(unknownCount > 0) {
            statusString.append("Unknown: ").append(unknownCount);
        }
        return statusString.toString();
    }

    private boolean containsOnlyGoodGuesses(List<GuessDigitStatusEnum> guessList) {
        boolean result = true;
        for(GuessDigitStatusEnum elem : guessList) {
            result = result && elem == CORRECT_PLACEMENT;
        }
        return result;
    }

    private void setGameOver(String gameOver) {
        this.gameOver = gameOver;
        firePropertyChange(PropertyName.GAME_OVER.getText(), "", gameOver);
    }
}