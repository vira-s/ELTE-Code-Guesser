package hu.edu.elte.codeguesser_view;

import hu.edu.elte.codeguesser_view.action.ActionType;
import hu.edu.elte.codeguesser_view.button.GameButton;
import hu.edu.elte.codeguesser_view.button.SubmitButton;
import hu.edu.elte.codeguesser_view.listeners.CustomActionListener;
import hu.edu.elte.codeguesser_view.listeners.CustomWindowListener;
import hu.edu.elte.codeguesser_view.menu.GameMenu;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

/**
 * @author virabia on 5/10/2018
 */
public class CodeGuesserView extends JFrame {

    private static final String WINDOW_TITLE = "ELTE Code Guesser Game";

    private CustomActionListener actionListener;
    private CustomWindowListener windowListener;
    private JPanel panel;
    private int guessLength;

    private JTextField gameInfo;
    private String gameInfoString;
    private JTextArea previousGuesses;
    private JTextField guessNumbers;
    private SubmitButton submitGuess;
    private JTextField remainingGuess;
    private GameButton giveUp;

    private CodeGuesserView self;


    public CodeGuesserView(CustomActionListener actionListener) {
        super(WINDOW_TITLE);
        self = this;

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(800, 600);

        this.actionListener = actionListener;
        this.actionListener.addPropertyChangeListener(event -> {
            switch (event.getPropertyName()) {
                case "codeLength": {
                    guessLength = ((int) event.getNewValue());
                    if(guessLength == 0) {
                        return;
                    }
                    gameInfoString += "Code Length: " + event.getNewValue();
                    gameInfo.setText(gameInfoString);
                    guessNumbers.setDocument(new JTextFieldLimit(guessLength));
                    panel.setVisible(true);

                    panel.validate();
                    validate();
                    break;
                }
                case "status": {
                    previousGuesses.append(guessNumbers.getText() + " - " + event.getNewValue()+"\n");
                    guessNumbers.setText("");

                    validate();
                    break;
                }
                case "remainingGuessCount": {
                    remainingGuess.setText("Remaining: " + event.getNewValue());
                    gameInfoString += "Maximal number of guesses available: " + event.getNewValue() + " | ";

                    validate();
                    break;
                }
                case "gameMode": {
                    previousGuesses.setText("Previous Guesses: \n");
                    gameInfoString = "Game level: " + event.getNewValue() + " | ";

                    validate();
                    break;
                }
                case "gameOver": {
                    if(!Objects.isNull(event.getNewValue()) && StringUtils.isNotBlank((String) event.getNewValue())) {
                        JOptionPane.showMessageDialog(self, event.getNewValue());
                        gameInfo.setText("");
                        previousGuesses.setText("Previous Guesses: \n");

                        panel.setVisible(false);

                        validate();
                    }
                    break;
                }
            }
        });
        this.windowListener = new CustomWindowListener();
        addWindowListener(windowListener);
        setJMenuBar(new GameMenu(this));
        initializeWindow();
        panel.setVisible(false);

        validate();
    }

    public CustomActionListener getActionListener() {
        return actionListener;
    }

    private void initializeWindow() {

        gameInfo = new JTextField();
        previousGuesses = new JTextArea("Previous Guesses: \n");
        guessNumbers = new JTextField();
        remainingGuess = new JTextField();
        giveUp = new GameButton(ActionType.GIVE_UP,actionListener);
        submitGuess = new SubmitButton(ActionType.SUBMIT, actionListener);


        panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        panel.add(gameInfo);
        panel.add(previousGuesses);
        panel.add(guessNumbers);
        panel.add(submitGuess);
        panel.add(remainingGuess);
        panel.add(giveUp);

        initializeSubmitButton(guessNumbers);
        submitGuess.setText(ActionType.SUBMIT.getText());

        this.add(panel);
        validate();
        panel.updateUI();
    }

    private void initializeSubmitButton(JTextField guess) {
        submitGuess.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                submitGuess.setGuessToSend(guess.getText());
            }
        });
    }

    private class JTextFieldLimit extends PlainDocument {
        private int limit;
        JTextFieldLimit(int limit) {
            super();
            this.limit = limit;
        }

        JTextFieldLimit(int limit, boolean upper) {
            super();
            this.limit = limit;
        }

        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null)
                return;

            if ((getLength() + str.length()) <= limit) {
                super.insertString(offset, str, attr);
            }
        }
    }
}