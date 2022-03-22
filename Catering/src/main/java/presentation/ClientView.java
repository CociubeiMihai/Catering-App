package presentation;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

public class ClientView {

    private JFrame frame;
    private JTable table;
    private JLabel totalLabel;
    private JTextField produsTitle;
    private JButton adaugaBtn;
    private JButton removeBtn;
    private JButton produseBtn;
    private JButton orderBtn;
    private DefaultTableModel model = new DefaultTableModel();
    private JTextField filterValue;
    JComboBox comboBox = new JComboBox();
    JButton findBtn = new JButton("Filter");

    /**
     * Create the application.
     */
    public ClientView() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        model.addColumn("Title");
        model.addColumn("Rating");
        model.addColumn("Calories");
        model.addColumn("Protein");
        model.addColumn("Fat");
        model.addColumn("Sodium");
        model.addColumn("Price");

        frame = new JFrame();
        frame.setBounds(100, 100, 729, 529);
        frame.getContentPane().setLayout(null);

        produsTitle = new JTextField();
        produsTitle.setBounds(30, 333, 86, 20);
        frame.getContentPane().add(produsTitle);
        produsTitle.setColumns(10);

        adaugaBtn = new JButton("Add");
        adaugaBtn.setBounds(126, 332, 89, 23);
        frame.getContentPane().add(adaugaBtn);

        removeBtn = new JButton("Remove");
        removeBtn.setBounds(225, 332, 89, 23);
        frame.getContentPane().add(removeBtn);

        produseBtn = new JButton("Produse");
        produseBtn.setBounds(476, 332, 89, 23);
        frame.getContentPane().add(produseBtn);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(15, 10, 673, 271);
        frame.getContentPane().add(scrollPane);

        table = new JTable(model);
        scrollPane.setViewportView(table);

        orderBtn = new JButton("Order");
        orderBtn.setBounds(575, 332, 89, 23);
        frame.getContentPane().add(orderBtn);

        totalLabel = new JLabel("Total: ");
        totalLabel.setBounds(476, 290, 180, 14);
        frame.getContentPane().add(totalLabel);

        findBtn.setBounds(225, 386, 89, 23);
        frame.getContentPane().add(findBtn);

        comboBox.setModel(new DefaultComboBoxModel(new String[] {"Rating", "Calories", "Protein", "Fat", "Sodium", "Price"}));
        comboBox.setBounds(126, 386, 89, 22);
        frame.getContentPane().add(comboBox);

        filterValue = new JTextField();
        filterValue.setBounds(30, 387, 86, 20);
        frame.getContentPane().add(filterValue);
        filterValue.setColumns(10);
    }

    public void addTable(String[]data){
        model.addRow(data);
    }
    public void removeTable(int x){
        model.removeRow(x);
    }

    public void addListener(ActionListener a){
        adaugaBtn.addActionListener(a);
    }

    public String getText(){
        return produsTitle.getText();
    }

    public void produseActionListener(ActionListener a){
        produseBtn.addActionListener(a);
    }

    public void setVizibile(){
        frame.setVisible(true);
    }

    public void serTotal(int s){
        totalLabel.setText("Total: "+ s);
    }

    public void removeElement(ActionListener a){
        removeBtn.addActionListener(a);
    }

    public void orderActionListener(ActionListener a){
        orderBtn.addActionListener(a);
    }

    public void removeOrder(){
        for(int i = model.getRowCount() - 1; i >= 0; i--)
            model.removeRow(i);
    }

    public void filterListener(ActionListener a){
        findBtn.addActionListener(a);
    }

    public String comboBoxval(){
        return comboBox.getSelectedItem().toString();
    }

    public int filterValue(){
        return Integer.parseInt(filterValue.getText());
    }

}

