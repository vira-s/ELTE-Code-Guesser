package hu.edu.elte.codeguesser_view;

import hu.edu.elte.codeguesser_view.listeners.CustomActionListener;
import hu.edu.elte.codeguesser_view.listeners.CustomWindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;

/**
 * @author virabia on 5/10/2018
 */
public class CodeguesserView extends JFrame {

    protected static final String WINDOW_TITLE = "ELTE Code Guesser Game";

    protected CustomActionListener actionListener;
    protected CustomWindowListener windowListener;
    protected JPanel panel;
    protected JButton button;

    public CodeguesserView(CustomActionListener actionListener) {
        super(WINDOW_TITLE);

        this.actionListener = actionListener;
        windowListener = new CustomWindowListener();

        initializeWindow();
    }

    private void initializeWindow() {
        setSize(800, 600);
        addWindowListener(windowListener);

        button = new JButton("Exit");
        button.addActionListener(actionListener);

        panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1));
        panel.add(button);

        this.add(panel);
    }
}
