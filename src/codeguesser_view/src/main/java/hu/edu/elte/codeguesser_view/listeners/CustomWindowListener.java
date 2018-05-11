package hu.edu.elte.codeguesser_view.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * @author virabia on 5/10/2018
 */
public class CustomWindowListener implements WindowListener {

    Logger LOGGER = LoggerFactory.getLogger(CustomWindowListener.class);

    @Override
    public void windowOpened(WindowEvent event) {

    }

    @Override
    public void windowClosing(WindowEvent event) {
        LOGGER.info("Closing window.");

        System.exit(0);
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
