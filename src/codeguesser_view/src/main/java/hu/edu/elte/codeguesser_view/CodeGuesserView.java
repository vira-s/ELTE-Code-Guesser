package hu.edu.elte.codeguesser_view;

import hu.edu.elte.codeguesser_view.action.ActionType;
import hu.edu.elte.codeguesser_view.button.GameButton;
import hu.edu.elte.codeguesser_view.button.SubmitButton;
import hu.edu.elte.codeguesser_view.listeners.CustomActionListener;
import hu.edu.elte.codeguesser_view.listeners.CustomWindowListener;
import hu.edu.elte.codeguesser_view.menu.GameMenu;

import javax.swing.*;
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

    private JTextField gameInfo;
    private JTextArea previousGuesses;
    private JTextField guessNumbers;
    private SubmitButton submitGuess;
    private JTextField remainingGuess;
    private GameButton giveUp;

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

        gameInfo = new JTextField();
        previousGuesses = new JTextArea();
        guessNumbers = new JTextField();
        submitGuess = new SubmitButton(ActionType.SUBMIT,actionListener);
        remainingGuess = new JTextField();
        giveUp = new GameButton(ActionType.GIVE_UP,actionListener);

        initializeSubmitButton(guessNumbers);

        panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        panel.add(gameInfo);
        panel.add(previousGuesses);
        panel.add(guessNumbers);
        panel.add(submitGuess);
        panel.add(remainingGuess);
        panel.add(giveUp);

        this.add(panel);
    }

    private void initializeSubmitButton(JTextField guess) {
        submitGuess = new SubmitButton(ActionType.SUBMIT, actionListener);
        submitGuess.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                submitGuess.setGuessToSend(guess.getText());
                previousGuesses.setText(guess.getText());
            }
        });
    }
}
