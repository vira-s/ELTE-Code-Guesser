package hu.edu.elte.codeguesser_view.action;

import hu.edu.elte.codeguesser_view.dialogs.ConfirmationDialog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;

/**
 * @author virabia on 5/13/2018
 */
public class ExitAction extends AbstractAction {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExitAction.class);

    @Override
    public void actionPerformed(ActionEvent event) {
        LOGGER.info("About to close game window.");

        ConfirmationDialog exitDialog = new ConfirmationDialog(null, "Are you sure, you want to exit?", "Exit");
        if (exitDialog.getAnswer() == JOptionPane.YES_OPTION) {
            LOGGER.info("Closing window.");
            System.exit(0);
        } else {
            exitDialog.setVisible(false);
        }
    }

}
