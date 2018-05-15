package hu.edu.elte.codeguesser_presenter;

import hu.edu.elte.codeguesser_model.GuessDigitStatusEnum;
import hu.edu.elte.codeguesser_presenter.services.GuessDigitStatusCalculatorService;
import hu.edu.elte.codeguesser_presenter.services.GuessDigitStatusCalculatorServiceImpl;
import hu.edu.elte.codeguesser_presenter.services.SecretCodeGeneratorService;
import hu.edu.elte.codeguesser_presenter.services.SecretCodeGeneratorServiceImpl;
import hu.edu.elte.codeguesser_presenter.utils.GameMode;
import hu.edu.elte.codeguesser_view.action.ActionType;
import hu.edu.elte.codeguesser_view.listeners.CustomActionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author virabia on 5/10/2018
 */
public class CodeGuesserPresenter implements CustomActionListener {

    private final Logger LOGGER = LoggerFactory.getLogger(CodeGuesserPresenter.class);

    private final GuessDigitStatusCalculatorService guessDigitStatusCalculatorService;

    private final SecretCodeGeneratorService secretCodeGeneratorService;

    private GameMode gameMode;

    private String secretCode;

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
                System.exit(0);
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
                LOGGER.info("SecretCode: " + secretCode + ", Guess: " + guess + ", Status: " + result);
                break;
            default:
                break;
        }
    }

    @Override
    public void actionPerformed(ActionType actionType, int codeLength) {
        switch (actionType) {
            case NEW_GAME_EASY:
                LOGGER.info("Received actionType=" + actionType + ", codeLength=" + codeLength);
                LOGGER.info(ActionType.NEW_GAME_EASY.getText() + " was pressed.");
                secretCode = secretCodeGeneratorService.generateSecretCode(codeLength);
                gameMode = GameMode.EASY;
                LOGGER.info("Generated secret code=" + secretCode);
                break;
            //TODO missing implementation
            // case NEW_GAME_MEDIUM:
            //     break;
            // case NEW_GAME_HARD:
            //     break;
            default:
                break;
        }
    }
}
