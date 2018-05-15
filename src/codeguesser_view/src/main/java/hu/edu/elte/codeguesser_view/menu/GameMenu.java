package hu.edu.elte.codeguesser_view.menu;

import hu.edu.elte.codeguesser_view.CodeGuesserView;
import hu.edu.elte.codeguesser_view.action.ActionType;
import hu.edu.elte.codeguesser_view.action.ExitAction;
import hu.edu.elte.codeguesser_view.action.NewGameAction;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.Dimension;
import java.awt.event.KeyEvent;

/**
 * @author virabia on 5/13/2018
 */
public class GameMenu extends JMenuBar {

    private final CodeGuesserView parentView;

    private JMenu optionsMenu;

    private JMenuItem newGameEasy;

    private JMenuItem exitGame;

    public GameMenu(CodeGuesserView parentView) {
        this.parentView = parentView;
        setPreferredSize(new Dimension(getWidth(), 35));
        initOptionsMenu();
        add(optionsMenu);
    }

    private WindowMenuItem initMenuItem(ActionType actionType) {
        switch (actionType) {
            case EXIT:
                return new WindowMenuItem(actionType.getText(), 'x', KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_MASK),
                        new ExitAction());
            case NEW_GAME_EASY:
                return new GameModeMenuItem(actionType.getText(), actionType.name().charAt(0), KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK),
                        new NewGameAction(actionType), actionType, parentView.getActionListener());
            //TODO missing implementation
            // case NEW_GAME_MEDIUM:
            //     return null;
            // case NEW_GAME_HARD:
            //     return null;
            default:
                return null;
        }

    }

    private void initOptionsMenu() {
        optionsMenu = new JMenu("Options");
        optionsMenu.setMnemonic('O');
        setOptionsPreferences();
    }

    private void setOptionsPreferences() {
        newGameEasy = initMenuItem(ActionType.NEW_GAME_EASY);
        optionsMenu.add(newGameEasy);
//        newGameEasy = initMenuItem(ActionType.NEW_GAME_MEDIUM);
//        optionsMenu.add(newGameMedium);
//        newGameEasy = initMenuItem(ActionType.NEW_GAME_HARD);
//        optionsMenu.add(newGameHard);

        optionsMenu.addSeparator();

        exitGame = initMenuItem(ActionType.EXIT);
        optionsMenu.add(exitGame);
    }
}
