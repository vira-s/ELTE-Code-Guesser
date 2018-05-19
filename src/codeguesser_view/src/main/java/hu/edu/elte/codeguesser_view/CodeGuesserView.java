package hu.edu.elte.codeguesser_view;

import hu.edu.elte.codeguesser_view.action.ActionType;
import hu.edu.elte.codeguesser_view.button.GameButton;
import hu.edu.elte.codeguesser_view.button.SubmitButton;
import hu.edu.elte.codeguesser_view.listeners.CustomActionListener;
import hu.edu.elte.codeguesser_view.listeners.CustomWindowListener;
import hu.edu.elte.codeguesser_view.menu.GameMenu;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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
        this.actionListener.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                switch (evt.getPropertyName()) {
                    case "codeLength": {
                        guessLength = ((int) evt.getNewValue());
                        if(guessLength == 0)
                            return;
                        gameInfoString += "Code Length: " + evt.getNewValue();
                        gameInfo.setText(gameInfoString);
                        guessNumbers.setDocument(new JTextFieldLimit(guessLength));
                        panel.setVisible(true);
                        break;
                    }
                    case "status": {
                        previousGuesses.append(guessNumbers.getText() + " - " + evt.getNewValue()+"\n");
                        guessNumbers.setText("");
                        break;
                    }
                    case "remainingGuessCount": {
                        remainingGuess.setText("Remaining: " + evt.getNewValue());
                        gameInfoString += "Maximal numbers of guesses available: " + evt.getNewValue() + " | ";
                        break;
                    }
                    case "gameMode": {
                        previousGuesses.setText("Previous Guesses: \n");
                        gameInfoString = "Game level: " + evt.getNewValue() + " | ";
                        break;
                    }
                    case "gameOver": {
                        if(evt.getNewValue() != null && !"".equals(evt.getNewValue())) {
                            JOptionPane.showMessageDialog(self, evt.getNewValue());
                            gameInfo.setText("");
                            previousGuesses.setText("Previous Guesses: \n");
                            panel.setVisible(false);
                        }
                        break;
                    }
                }
            }
        });
        this.windowListener = new CustomWindowListener();
        addWindowListener(windowListener);
        setJMenuBar(new GameMenu(this));
        initializeWindow();
        panel.setVisible(false);
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
        //idk miért nem működik máshogy
        submitGuess.setText(ActionType.SUBMIT.getText());

        this.add(panel);
        panel.updateUI();
    }

    private void initializeSubmitButton(JTextField guess) {
        submitGuess.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                submitGuess.setGuessToSend(guess.getText());
                //guess.setText("");
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