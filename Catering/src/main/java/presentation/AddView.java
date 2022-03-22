package presentation;

import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AddView {

    private JFrame frame;
    private JTextField titluText;
    private JTextField ratingText;
    private JTextField caloriesText;
    private JTextField proteinText;
    private JTextField fatText;
    private JTextField sodiumText;
    private JTextField priceText;
    private JTable table;
    JButton newProduct = new JButton("New product");
    JButton existingProduct = new JButton("Existing product");
    JButton createBtn = new JButton("Create ");
    private DefaultTableModel model = new DefaultTableModel();

    /**
     * Create the application.
     */
    public AddView() {
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
        frame.setBounds(100, 100, 748, 544);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Titlu");
        lblNewLabel.setBounds(10, 344, 46, 14);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Rating");
        lblNewLabel_1.setBounds(117, 344, 46, 14);
        frame.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Calories");
        lblNewLabel_2.setBounds(221, 344, 86, 14);
        frame.getContentPane().add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Protein");
        lblNewLabel_3.setBounds(326, 344, 46, 14);
        frame.getContentPane().add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("Fat");
        lblNewLabel_4.setBounds(424, 344, 46, 14);
        frame.getContentPane().add(lblNewLabel_4);

        JLabel lblNewLabel_5 = new JLabel("Sodium");
        lblNewLabel_5.setBounds(520, 344, 46, 14);
        frame.getContentPane().add(lblNewLabel_5);

        JLabel lblNewLabel_6 = new JLabel("Price");
        lblNewLabel_6.setBounds(616, 344, 46, 14);
        frame.getContentPane().add(lblNewLabel_6);

        titluText = new JTextField();
        titluText.setBounds(10, 369, 86, 20);
        frame.getContentPane().add(titluText);
        titluText.setColumns(10);

        ratingText = new JTextField();
        ratingText.setBounds(117, 369, 86, 20);
        frame.getContentPane().add(ratingText);
        ratingText.setColumns(10);

        caloriesText = new JTextField();
        caloriesText.setBounds(221, 369, 86, 20);
        frame.getContentPane().add(caloriesText);
        caloriesText.setColumns(10);

        proteinText = new JTextField();
        proteinText.setBounds(326, 369, 86, 20);
        frame.getContentPane().add(proteinText);
        proteinText.setColumns(10);

        fatText = new JTextField();
        fatText.setBounds(424, 369, 86, 20);
        frame.getContentPane().add(fatText);
        fatText.setColumns(10);

        sodiumText = new JTextField();
        sodiumText.setBounds(520, 369, 86, 20);
        frame.getContentPane().add(sodiumText);
        sodiumText.setColumns(10);

        priceText = new JTextField();
        priceText.setBounds(616, 369, 86, 20);
        frame.getContentPane().add(priceText);
        priceText.setColumns(10);

        newProduct.setBounds(55, 435, 193, 23);
        frame.getContentPane().add(newProduct);

        existingProduct.setBounds(258, 435, 198, 23);
        frame.getContentPane().add(existingProduct);

        createBtn.setBounds(466, 435, 174, 23);
        frame.getContentPane().add(createBtn);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(54, 40, 648, 274);
        frame.getContentPane().add(scrollPane);

        table = new JTable(model);
        scrollPane.setViewportView(table);
        frame.setVisible(true);
    }

    public void addTable(String[]data){
        model.addRow(data);
    }
    public String getTitlu(){
        return titluText.getText();
    }

    public float getRating(){
        return Float.parseFloat(ratingText.getText());
    }

    public int getCalories(){
        return Integer.parseInt(caloriesText.getText());
    }

    public int getProteine(){
        return Integer.parseInt(proteinText.getText());
    }

    public int getFat(){
        return Integer.parseInt(fatText.getText());
    }

    public int getSodium(){
        return Integer.parseInt(sodiumText.getText());
    }

    public int getPrice(){
        return Integer.parseInt(priceText.getText());
    }

    public void newItemListener(ActionListener a){
        newProduct.addActionListener(a);
    }

    public void existingProductListener(ActionListener a){
        existingProduct.addActionListener(a);
    }

    public void createListener(ActionListener a){
        createBtn.addActionListener(a);
    }
    public void removeOrder(){
        for(int i = model.getRowCount() - 1; i >= 0; i--)
            model.removeRow(i);
    }
}
