package hu.edu.elte.codeguesser_presenter;

import hu.edu.elte.codeguesser_view.CodeGuesserView;

import org.apache.log4j.BasicConfigurator;

/**
 * @author virabia on 5/10/2018
 */
public class AppDemo {

    public static void main(String[] args) {
        BasicConfigurator.configure();

        CodeGuesserPresenter presenter = new CodeGuesserPresenter();
        CodeGuesserView view = new CodeGuesserView(presenter);

        view.setVisible(true);
    }
}
