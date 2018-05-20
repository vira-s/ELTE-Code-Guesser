package hu.edu.elte.codeguesser_view.dialogs;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import java.awt.Component;

/**
 * @author virabia on 5/13/2018
 */
public class ConfirmationDialog  extends JDialog {

    private static final String[] OPTIONS = { "Yes", "No" };

    private int answer;

    public ConfirmationDialog(Component parent, String text, String title) {
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        answer = JOptionPane.showOptionDialog(parent, text, title, JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE, null, OPTIONS, OPTIONS[0]);
    }

    public int getAnswer() {
        return answer;
    }
}
