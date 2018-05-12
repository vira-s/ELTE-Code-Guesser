package hu.edu.elte.codeguesser_presenter;

import hu.edu.elte.codeguesser_presenter.services.GuessDigitStatusCalculatorService;
import hu.edu.elte.codeguesser_presenter.services.GuessDigitStatusCalculatorServiceImpl;
import hu.edu.elte.codeguesser_presenter.services.SecretCodeGeneratorService;
import hu.edu.elte.codeguesser_presenter.services.SecretCodeGeneratorServiceImpl;
import hu.edu.elte.codeguesser_view.action.ActionType;
import hu.edu.elte.codeguesser_view.listeners.CustomActionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author virabia on 5/10/2018
 */
public class CodeguesserPresenter implements CustomActionListener {

    private final Logger LOGGER = LoggerFactory.getLogger(CodeguesserPresenter.class);

    private final GuessDigitStatusCalculatorService guessDigitStatusCalculatorService;

    private final SecretCodeGeneratorService secretCodeGeneratorService;

    public CodeguesserPresenter() {
        this.guessDigitStatusCalculatorService = new GuessDigitStatusCalculatorServiceImpl();
        this.secretCodeGeneratorService = new SecretCodeGeneratorServiceImpl();
    }

    @Override
    public void actionPerformed(ActionType actionType) {
        LOGGER.info("Something was pressed with type: " + actionType);

        switch (actionType) {
            case EXIT:
                LOGGER.info(ActionType.EXIT.getText() + " was pressed.");
                System.exit(0);
                break;
            case SUBMIT:
                LOGGER.info(ActionType.SUBMIT.getText() + " was pressed.");
                break;
            case GIVE_UP:
                LOGGER.info(ActionType.GIVE_UP.getText() + " was pressed.");
                System.exit(0);
                break;
            case NEW_GAME_EASY:
                LOGGER.info(ActionType.NEW_GAME_EASY.getText() + " was pressed.");
                break;
            case NEW_GAME_MEDIUM:
                LOGGER.info(ActionType.NEW_GAME_MEDIUM.getText() + " was pressed.");
                break;
            case NEW_GAME_HARD:
                LOGGER.info(ActionType.NEW_GAME_HARD.getText() + " was pressed.");
                break;
            default:
                LOGGER.info("idk");
                break;
        }
    }

}
