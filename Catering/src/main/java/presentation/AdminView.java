package presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.security.PublicKey;

public class AdminView {

    private JFrame frame;
    private JTextField intervalMin;
    private JTextField intervalMax;
    private JTextField moreThanText;
    private JTextField clientsOrderedMoreThantext;
    private JTextField valueTextField;
    private JTextField deleteProductText;
    private JTextField titluModifiyText;
    private JTextField valoareaModificata;
    private JTextField dayTextField;
    private JTextField numberOfTimesText;

    private JButton ImportBtn = new JButton("Import");
    private JButton addBtn = new JButton("Add");
    private JButton deleteBtn = new JButton("Delete");
    private JButton modifyBtn = new JButton("Modify");
    private JButton intervalBtn = new JButton("Interval");
    private JButton orderMoreBtn = new JButton("Order");
    private JButton clientsBtn = new JButton("Clients");
    private JComboBox comboBox = new JComboBox();
    private JButton products = new JButton("Products");
    JButton saveBtn = new JButton("Save");
    JButton loadBtn = new JButton("Load");

    /**
     * Create the application.
     */
    public AdminView() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 729, 461);
        frame.getContentPane().setLayout(null);

        ImportBtn.setBounds(30, 25, 89, 23);
        frame.getContentPane().add(ImportBtn);

        addBtn.setBounds(30, 59, 89, 23);
        frame.getContentPane().add(addBtn);

        deleteBtn.setBounds(30, 93, 89, 23);
        frame.getContentPane().add(deleteBtn);

        modifyBtn.setBounds(30, 127, 89, 23);
        frame.getContentPane().add(modifyBtn);

        intervalBtn.setBounds(30, 161, 89, 23);
        frame.getContentPane().add(intervalBtn);

        intervalMin = new JTextField();
        intervalMin.setBounds(129, 162, 86, 20);
        frame.getContentPane().add(intervalMin);
        intervalMin.setColumns(10);

        intervalMax = new JTextField();
        intervalMax.setBounds(225, 162, 86, 20);
        frame.getContentPane().add(intervalMax);
        intervalMax.setColumns(10);

        orderMoreBtn.setBounds(30, 195, 89, 23);
        frame.getContentPane().add(orderMoreBtn);

        moreThanText = new JTextField();
        moreThanText.setBounds(129, 193, 86, 20);
        frame.getContentPane().add(moreThanText);
        moreThanText.setColumns(10);

        clientsBtn.setBounds(30, 229, 89, 23);
        frame.getContentPane().add(clientsBtn);

        clientsOrderedMoreThantext = new JTextField();
        clientsOrderedMoreThantext.setBounds(129, 230, 86, 20);
        frame.getContentPane().add(clientsOrderedMoreThantext);
        clientsOrderedMoreThantext.setColumns(10);

        valueTextField = new JTextField();
        valueTextField.setBounds(409, 230, 86, 20);
        frame.getContentPane().add(valueTextField);
        valueTextField.setColumns(10);

        JLabel lblNewLabel = new JLabel("Value of the order grather than ");
        lblNewLabel.setBounds(225, 233, 188, 14);
        frame.getContentPane().add(lblNewLabel);

        deleteProductText = new JTextField();
        deleteProductText.setBounds(129, 94, 86, 20);
        frame.getContentPane().add(deleteProductText);
        deleteProductText.setColumns(10);

        titluModifiyText = new JTextField();
        titluModifiyText.setBounds(129, 128, 86, 20);
        frame.getContentPane().add(titluModifiyText);
        titluModifiyText.setColumns(10);

        comboBox.setModel(new DefaultComboBoxModel(new String[] {"Titlu", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"}));
        comboBox.setBounds(225, 127, 86, 22);
        frame.getContentPane().add(comboBox);

        valoareaModificata = new JTextField();
        valoareaModificata.setBounds(321, 128, 86, 20);
        frame.getContentPane().add(valoareaModificata);
        valoareaModificata.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("time interval of the orders");
        lblNewLabel_1.setBounds(321, 165, 203, 14);
        frame.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("the products ordered more than a specified number of times so far");
        lblNewLabel_2.setBounds(235, 199, 417, 14);
        frame.getContentPane().add(lblNewLabel_2);

        products.setBounds(30, 263, 89, 23);
        frame.getContentPane().add(products);

        JLabel jlabel = new JLabel("day");
        jlabel.setBounds(129, 267, 46, 14);
        frame.getContentPane().add(jlabel);

        dayTextField = new JTextField();
        dayTextField.setBounds(185, 261, 86, 20);
        frame.getContentPane().add(dayTextField);
        dayTextField.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("number of times ordered");
        lblNewLabel_3.setBounds(281, 267, 147, 14);
        frame.getContentPane().add(lblNewLabel_3);

        saveBtn.setBounds(30, 361, 89, 23);
        frame.getContentPane().add(saveBtn);

        loadBtn.setBounds(129, 361, 89, 23);
        frame.getContentPane().add(loadBtn);

        numberOfTimesText = new JTextField();
        numberOfTimesText.setBounds(452, 264, 86, 20);
        frame.getContentPane().add(numberOfTimesText);
        numberOfTimesText.setColumns(10);
    }

    public void setVizibile(){
        frame.setVisible(true);
    }

    public void importListener(ActionListener a){
        ImportBtn.addActionListener(a);
    }

    public void deleteListener(ActionListener a){
        deleteBtn.addActionListener(a);
    }

    public String getDeletteValue(){
        return deleteProductText.getText();
    }

    public String getComboBoxValue(){
        return comboBox.getSelectedItem().toString();
    }

    public void modifyListener(ActionListener a){
        modifyBtn.addActionListener(a);
    }

    public String getTitluPentruModificare(){
        return titluModifiyText.getText();
    }

    public String getNouaValoare(){
        return valoareaModificata.getText();
    }

    public void timeIntervalListener(ActionListener a){
        intervalBtn.addActionListener(a);
    }

    public int oraMinima(){
        return Integer.parseInt(intervalMin.getText());
    }

    public int oraMaxima(){
        return Integer.parseInt(intervalMax.getText());
    }

    public void orederMoreListener(ActionListener a){
        orderMoreBtn.addActionListener(a);
    }

    public int orderMoreInt(){
        return Integer.parseInt(moreThanText.getText());
    }

    public void clientListener(ActionListener a){
        clientsBtn.addActionListener(a);
    }

    public int numarulMinimDeComenzi(){
        return Integer.parseInt(clientsOrderedMoreThantext.getText());
    }

    public int amount(){
        return Integer.parseInt(valueTextField.getText());
    }

    public void productsListener(ActionListener a){
        products.addActionListener(a);
    }

    public int getDay(){
        return Integer.parseInt(dayTextField.getText());
    }

    public int getNumberOfTimes(){
        return Integer.parseInt(numberOfTimesText.getText());
    }

    public void addListener(ActionListener a){
        addBtn.addActionListener(a);
    }

    public void saveListener(ActionListener a){
        saveBtn.addActionListener(a);
    }

    public void loadListener(ActionListener a){
        loadBtn.addActionListener(a);
    }
}

