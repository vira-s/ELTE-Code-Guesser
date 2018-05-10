package hu.edu.elte.codeguesser_presenter;

import hu.edu.elte.codeguesser_view.CodeguesserView;

import org.apache.log4j.BasicConfigurator;

/**
 * @author virabia on 5/10/2018
 */
public class AppDemo {

    public static void main(String[] args) {
        BasicConfigurator.configure();

        CodeguesserPresenter presenter = new CodeguesserPresenter();
        CodeguesserView view = new CodeguesserView(presenter);

        view.setVisible(true);
    }
}
