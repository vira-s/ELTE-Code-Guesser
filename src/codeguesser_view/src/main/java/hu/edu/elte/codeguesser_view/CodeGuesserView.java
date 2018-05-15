package hu.edu.elte.codeguesser_view;

import hu.edu.elte.codeguesser_view.action.ActionType;
import hu.edu.elte.codeguesser_view.button.GameButton;
import hu.edu.elte.codeguesser_view.button.SubmitButton;
import hu.edu.elte.codeguesser_view.listeners.CustomActionListener;
import hu.edu.elte.codeguesser_view.listeners.CustomWindowListener;
import hu.edu.elte.codeguesser_view.menu.GameMenu;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

/**
 * @author virabia on 5/10/2018
 */
public class CodeGuesserView extends JFrame {

    private static final String WINDOW_TITLE = "ELTE Code Guesser Game";

    private CustomActionListener actionListener;
    private CustomWindowListener windowListener;
    private JPanel panel;

    private GameButton exitButton;
    private SubmitButton submitButton;
    private GameButton newGameButton;

    public CodeGuesserView(CustomActionListener actionListener) {
        super(WINDOW_TITLE);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        this.actionListener = actionListener;
        this.windowListener = new CustomWindowListener();

        initializeWindow();
    }

    public CustomActionListener getActionListener() {
        return actionListener;
    }

    private void initializeWindow() {
        setSize(800, 600);
        addWindowListener(windowListener);

        setJMenuBar(new GameMenu(this));

        JLabel label = new JLabel("333333");
        initializeSubmitButton(label);

        newGameButton = new GameButton(ActionType.NEW_GAME_MEDIUM, actionListener);
        exitButton = new GameButton(ActionType.EXIT, actionListener);

        panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1));
        panel.add(exitButton);
        panel.add(submitButton);
        panel.add(newGameButton);
        panel.add(label);

        this.add(panel);
    }

    private void initializeSubmitButton(JLabel label) {
        submitButton = new SubmitButton(ActionType.SUBMIT, actionListener);
        submitButton.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                submitButton.setGuessToSend(label.getText());
            }
        });
    }
}
