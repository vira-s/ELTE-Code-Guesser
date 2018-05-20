package hu.edu.elte.codeguesser_view.listeners;

import hu.edu.elte.codeguesser_view.dialogs.ConfirmationDialog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.JOptionPane;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * @author virabia on 5/10/2018
 */
public class CustomWindowListener implements WindowListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomWindowListener.class);

    @Override
    public void windowOpened(WindowEvent event) {

    }

    @Override
    public void windowClosing(WindowEvent event) {
        LOGGER.info("About to close game window.");

        ConfirmationDialog confirmationDialog = new ConfirmationDialog(null, "Are you sure, you want to exit?", "Exit");
        int answer = confirmationDialog.getAnswer();
        if (answer == JOptionPane.YES_OPTION) {
            LOGGER.info("Closing window.");
            System.exit(0);
        } else {
            event.getWindow().setVisible(true);
        }
    }

    @Override
    public void windowClosed(WindowEvent event) {

    }

    @Override
    public void windowIconified(WindowEvent event) {

    }

    @Override
    public void windowDeiconified(WindowEvent event) {

    }

    @Override
    public void windowActivated(WindowEvent event) {

    }

    @Override
    public void windowDeactivated(WindowEvent event) {

    }
}
