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
import javax.swing.text.PlainDocument;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Objects;

/**
 * @author virabia on 5/10/2018
 */
public class CodeGuesserView extends JFrame {

    private static final String WINDOW_TITLE = "ELTE Code Guesser Game";

    private CustomActionListener actionListener;
    private CustomWindowListener windowListener;
    private JPanel gamePanel;
    GridBagConstraints gridBagConstraints;
    private int guessLength;

    private String gameInfoString;
    private JTextArea previousGuesses;
    private JTextField guessNumbers;
    private SubmitButton submitGuess;
    private JLabel remainingGuess;
    private JLabel maximalGuess;
    private JLabel gameLevel;
    private JLabel codeLength;
    private GameButton giveUp;

    public CodeGuesserView(CustomActionListener actionListener) {
        super(WINDOW_TITLE);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(1000, 700);

        initializeActionListener(actionListener);

        this.windowListener = new CustomWindowListener();
        addWindowListener(windowListener);
        setJMenuBar(new GameMenu(this));

        initializeGamePanel();
        initializeWindow();

        validate();
    }

    private void initializeGamePanel() {
        gamePanel = new JPanel();
        gamePanel.setLayout(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;

        gamePanel.setVisible(false);
    }

    private void initializeActionListener(CustomActionListener actionListener) {
        this.actionListener = actionListener;
        this.actionListener.addPropertyChangeListener(event -> {
            switch (event.getPropertyName()) {
                case "codeLength": {
                    guessLength = ((int) event.getNewValue());
                    if(guessLength == 0) {
                        return;
                    }
                    guessNumbers.setDocument(new JTextFieldLimit(guessLength));
                    guessNumbers.setColumns(13);

                    gamePanel.remove(guessNumbers);
                    gridBagConstraints.gridx = 1;
                    gridBagConstraints.gridy = 2;
                    gamePanel.add(guessNumbers, gridBagConstraints);

                    gamePanel.setVisible(true);

                    codeLength.setText("Code Length: " + guessLength);
                    gamePanel.remove(codeLength);
                    gridBagConstraints.gridx = 1;
                    gridBagConstraints.gridy = 0;
                    gamePanel.add(codeLength, gridBagConstraints);

                    gamePanel.validate();
                    validate();
                    break;
                }
                case "status": {
                    previousGuesses.append(guessNumbers.getText() + " - " + event.getNewValue()+"\n");

                    guessNumbers.setText("");
                    guessNumbers.setColumns(13);
                    validate();
                    break;
                }
                case "remainingGuessCount": {
                    remainingGuess.setText("Remaining: " + event.getNewValue());

                    gamePanel.remove(remainingGuess);
                    gridBagConstraints.gridx = 1;
                    gridBagConstraints.gridy = 4;
                    gamePanel.add(remainingGuess, gridBagConstraints);

                    if(StringUtils.isBlank(maximalGuess.getText())) {
                        maximalGuess.setText("Maximal number of guesses available: " + event.getNewValue());

                        gamePanel.remove(maximalGuess);
                        gridBagConstraints.gridx = 2;
                        gridBagConstraints.gridy = 0;
                        gamePanel.add(maximalGuess, gridBagConstraints);
                    }

                    validate();
                    break;
                }
                case "gameMode": {
                    previousGuesses = new JTextArea("--------------------------------------------Previous Guesses:--------------------------------------------\n");
                    previousGuesses.setEditable(false);
                    previousGuesses.setRows(26);

                    gamePanel.remove(previousGuesses);
                    gridBagConstraints.gridx = 1;
                    gridBagConstraints.gridy = 1;
                    gamePanel.add(previousGuesses, gridBagConstraints);

                    gameLevel.setText("Game level: " + event.getNewValue());
                    gamePanel.remove(gameLevel);
                    gridBagConstraints.gridx = 0;
                    gridBagConstraints.gridy = 0;
                    gamePanel.add(gameLevel, gridBagConstraints);

                    validate();
                    break;
                }
                case "gameOver": {
                    if(!Objects.isNull(event.getNewValue()) && StringUtils.isNotBlank((String) event.getNewValue())) {
                        JOptionPane.showMessageDialog(this, event.getNewValue());

                        gamePanel.setVisible(false);
                        gamePanel.removeAll();
                        validate();
                    }
                    break;
                }
            }
        });
    }

    public CustomActionListener getActionListener() {
        return actionListener;
    }

    private void initializeWindow() {

        previousGuesses = new JTextArea();
        guessNumbers = new JTextField();
        remainingGuess = new JLabel();
        maximalGuess = new JLabel();
        gameLevel = new JLabel();
        codeLength = new JLabel();

        initializeSubmitButton(guessNumbers);
        initializeGiveUpButton();

        this.add(gamePanel);
        validate();
        gamePanel.updateUI();
    }

    private void initializeSubmitButton(JTextField guess) {
        submitGuess = new SubmitButton(ActionType.SUBMIT, actionListener);

        gamePanel.remove(submitGuess);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gamePanel.add(submitGuess, gridBagConstraints);

        submitGuess.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                submitGuess.setGuessToSend(guess.getText());

                gamePanel.remove(submitGuess);
                gridBagConstraints.gridx = 1;
                gridBagConstraints.gridy = 3;
                gamePanel.add(submitGuess, gridBagConstraints);
            }
        });

        submitGuess.setText(ActionType.SUBMIT.getText());
    }

    private void initializeGiveUpButton() {
        giveUp = new GameButton(ActionType.GIVE_UP,actionListener);

        gamePanel.remove(giveUp);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gamePanel.add(giveUp, gridBagConstraints);
    }

    public void destroyView() {
        gamePanel.removeAll();

        initializeGamePanel();
        initializeWindow();

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