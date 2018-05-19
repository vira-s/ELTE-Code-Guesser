package hu.edu.elte.codeguesser_view;

import hu.edu.elte.codeguesser_view.action.ActionType;
import hu.edu.elte.codeguesser_view.button.GameButton;
import hu.edu.elte.codeguesser_view.button.SubmitButton;
import hu.edu.elte.codeguesser_view.listeners.CustomActionListener;
import hu.edu.elte.codeguesser_view.listeners.CustomWindowListener;
import hu.edu.elte.codeguesser_view.menu.GameMenu;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author virabia on 5/10/2018
 */
public class CodeGuesserView extends JFrame {

    private static final String WINDOW_TITLE = "ELTE Code Guesser Game";

    private CustomActionListener actionListener;
    private CustomWindowListener windowListener;
    private JPanel panel;

    private JLabel gameLevel;
    private JLabel codeLength;
    private JLabel maxGuesses;
    private JTextArea previousGuesses;
    private JTextField guessNumbers;
    private SubmitButton submitButton;
    private JLabel remainingGuesses;
    private GameButton giveUpButton;

    // private GameButton exitButton;
    // private GameButton newGameButton;

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
        setSize(1000, 700);
        addWindowListener(windowListener);

        setJMenuBar(new GameMenu(this));

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gameLevel = new JLabel("Level: " + "getGameLevel()");
        codeLength = new JLabel("Digits: " + "getCodeLength()");
        maxGuesses = new JLabel("Maximum Guesses: " + "getMaxGuesses()");

        previousGuesses = new JTextArea("--------------------------------------------Previous Guesses:--------------------------------------------\n");
        previousGuesses.setEditable(false);
        previousGuesses.setRows(26);

        guessNumbers = new JTextField(10);
        // submitButton = new SubmitButton(ActionType.SUBMIT, actionListener);
        JButton submitButton_TEMPORARY = new JButton("Submit");
        submitButton_TEMPORARY.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                previousGuesses.append("getGuess()" + " - correct: " + "getCorrect()" + ", contains: " + "getContaining()" + ", wrong: " + "getWrong()\n");
            }
        });

        remainingGuesses = new JLabel("Remaining guesses: " + "getRemainingGuesses()");
        giveUpButton = new GameButton(ActionType.GIVE_UP, actionListener);

        JLabel label = new JLabel("Submit");
        initializeSubmitButton(label);

        gc.weightx = 0.5;
        gc.weighty = 0.5;

        gc.gridx = 0;
        gc.gridy = 0;
        panel.add(gameLevel, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        panel.add(codeLength, gc);

        gc.gridx = 2;
        gc.gridy = 0;
        panel.add(maxGuesses, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        panel.add(previousGuesses, gc);

        gc.gridx = 1;
        gc.gridy = 2;
        panel.add(guessNumbers, gc);

        gc.gridx = 1;
        gc.gridy = 3;
        panel.add(submitButton_TEMPORARY, gc);

        gc.gridx = 1;
        gc.gridy = 4;
        panel.add(remainingGuesses, gc);

        gc.gridx = 2;
        gc.gridy = 5;
        panel.add(giveUpButton, gc);

        // newGameButton = new GameButton(ActionType.NEW_GAME_MEDIUM, actionListener);
        // exitButton = new GameButton(ActionType.EXIT, actionListener);

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
