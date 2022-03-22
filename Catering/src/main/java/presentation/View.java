package presentation;

import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class View {

    private JFrame frame;
    private JTextField userName;
    private JTextField password;
    private JButton registerBtn;
    private  JButton logInBtn;

    /**
     * Create the application.
     */
    public View() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel jLuserName = new JLabel("User name:");
        jLuserName.setBounds(80, 70, 76, 23);
        frame.getContentPane().add(jLuserName);

        userName = new JTextField();
        userName.setBounds(166, 71, 132, 20);
        frame.getContentPane().add(userName);
        userName.setColumns(10);

        JLabel jlPass = new JLabel("Password:");
        jlPass.setBounds(80, 104, 76, 14);
        frame.getContentPane().add(jlPass);

        password = new JTextField();
        password.setBounds(166, 102, 132, 20);
        frame.getContentPane().add(password);
        password.setColumns(10);

        logInBtn = new JButton("Log in");
        logInBtn.setBounds(165, 170, 89, 23);
        frame.getContentPane().add(logInBtn);

        registerBtn = new JButton("Register");
        registerBtn.setBounds(166, 204, 89, 23);
        frame.getContentPane().add(registerBtn);
        frame.setVisible(true);
    }

    public String getNume(){
        return userName.getText();
    }

    public String genPassword(){
        return password.getText();
    }

    public void logInActionlistener(ActionListener log){
        logInBtn.addActionListener(log);
    }

    public void registerActionListener(ActionListener reg){
        registerBtn.addActionListener(reg);
    }

}

