package hu.edu.elte.codeguesser_view.action;

import hu.edu.elte.codeguesser_view.listeners.CustomActionListener;
import hu.edu.elte.codeguesser_view.menu.GameModeMenuItem;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author virabia on 5/15/2018
 */
public class NewGameAction extends AbstractAction {

    private static final Logger LOGGER = LoggerFactory.getLogger(NewGameAction.class);

    private static final Integer[] POSSIBILITIES = {3, 4, 5, 6, 7, 8, 9, 10};

    private ActionType actionType;

    private int codeLength;

    public NewGameAction(ActionType actionType) {
        this.actionType = actionType;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        LOGGER.info("About to start new game.");

        String answer = String.valueOf(JOptionPane.showInputDialog(null, "Are you sure, you want to start a new game?", "New Game",
                JOptionPane.QUESTION_MESSAGE, null, POSSIBILITIES, POSSIBILITIES[0]));

        if (StringUtils.isNumeric(answer)) {
            this.codeLength = Integer.parseInt(answer);

            if (event.getSource() instanceof GameModeMenuItem) {
                GameModeMenuItem source = (GameModeMenuItem) event.getSource();
                source.setCodeLength(this.codeLength);
                source.setActionType(this.actionType);

                List<ActionListener> actionListeners = Arrays.asList(source.getActionListeners());
                if(!actionListeners.isEmpty()) {
                    List<ActionListener> listeners = actionListeners.stream()
                            .filter(listener -> listener instanceof CustomActionListener)
                            .collect(Collectors.toList());
                    if (!listeners.isEmpty()) {
                        CustomActionListener customActionListener = (CustomActionListener) listeners.get(0);
                        customActionListener.actionPerformed(actionType, codeLength);
                    }
                }
            }

        }
    }
}
