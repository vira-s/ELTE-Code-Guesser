package hu.edu.elte.codeguesser_presenter;

import hu.edu.elte.codeguesser_view.listeners.CustomActionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.ActionEvent;

/**
 * @author virabia on 5/10/2018
 */
public class CodeguesserPresenter implements CustomActionListener {
    private final Logger LOGGER = LoggerFactory.getLogger(CodeguesserPresenter.class);

    @Override
    public void actionPerformed(ActionEvent event) {
        LOGGER.info("Button pressed");
        System.exit(0);
    }

}
