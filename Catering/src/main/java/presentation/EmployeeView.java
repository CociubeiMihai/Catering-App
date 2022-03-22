package presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JLabel;

public class EmployeeView {

    private JFrame frame;
    JTextArea textArea = new JTextArea();

    /**
     * Create the application.
     */
    public EmployeeView() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 747, 534);
        frame.getContentPane().setLayout(null);

        textArea.setBounds(33, 32, 674, 428);
        frame.getContentPane().add(textArea);
    }

    public void setTextArea(String s){
        textArea.setText(s);
    }

    public void setVizibile(){
        frame.setVisible(true);
    }
}

