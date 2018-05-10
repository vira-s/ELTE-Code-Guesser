package hu.edu.elte.codeguesser_view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author virabia on 5/10/2018
 */
public class CodeguesserView extends JFrame {

    protected static final String WINDOW_TITLE = "ELTE Code Guesser Game";

    protected CustomActionListener actionListener;
    protected JPanel panel;
    protected JButton button;

    public CodeguesserView(CustomActionListener actionListener) {
        super(WINDOW_TITLE);

        this.actionListener = actionListener;

        initializeWindow();
    }

    private void initializeWindow() {
        setSize(800, 600);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        button = new JButton("Exit");
        button.addActionListener(actionListener);

        panel.setLayout(new GridLayout(1, 1));
        panel.add(button);

        this.add(panel);
    }
}
