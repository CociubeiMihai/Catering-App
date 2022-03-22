package presentation;

import business.*;
import data.Serializator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.TreeSet;

public class AdminController {

    private DeliveryService deliveryService;
    private AdminView adminView;

    public AdminController(DeliveryService deliveryService, AdminView adminView) {
        this.deliveryService = deliveryService;
        this.adminView = adminView;

        adminView.importListener(new ImpotrTabel());
        adminView.deleteListener(new DeleteProduct());
        adminView.modifyListener(new ModifyTable());
        adminView.timeIntervalListener(new TimeInterval());
        adminView.orederMoreListener(new OrderMore());
        adminView.clientListener(new ClientListener());
        adminView.productsListener(new ProductsListener());
        adminView.addListener(new AddListener());
        adminView.saveListener(new SaveListener());
        adminView.loadListener(new LoadListener());
    }

    class SaveListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Serializator serializator = new Serializator();
            serializator.serialization(deliveryService);
        }
    }

    class LoadListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Serializator serializator = new Serializator();
            serializator.derialization(deliveryService);
        }
    }

    class ImpotrTabel implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            deliveryService.populate();
        }
    }

    class DeleteProduct implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String titlu = adminView.getDeletteValue();
            deliveryService.removeElement(titlu);
        }
    }

    class ModifyTable implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            deliveryService.modifica(adminView.getTitluPentruModificare(),adminView.getComboBoxValue(),adminView.getNouaValoare());
        }
    }


    class TimeInterval implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame jFrame = new JFrame();
            jFrame.setVisible(true);
            int min = adminView.oraMinima();
            int max = adminView.oraMaxima();
            LinkedList<Order> o = deliveryService.comparOra(min,max);

            String []column = {"OrderId","ClientId","Date"};
            String[][] data = new String[o.size()][3];
            int i = 0;
            for(Order order: o){
                data[i][0] = String.valueOf(order.getOrderID());
                data[i][1] = String.valueOf(order.getClientID());
                data[i++][2] = String.valueOf(order.getOrderDate());
            }
            JTable jt=new JTable(data,column);
            JScrollPane sp=new JScrollPane(jt);
            jt.setBounds(30,40,1000,600);
            jFrame.add(sp);
            jFrame.setSize(1000,600);
            jFrame.setVisible(true);
        }
    }

    class OrderMore implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           LinkedList<MenuItem> meniu =  deliveryService.elementeComandate(adminView.orderMoreInt());
            JFrame jFrame = new JFrame();
            jFrame.setVisible(true);
            String []column = {"Titlu","Rating","Calories", "Protein","Fat","Sodium","Price"};

            String [][] data = new String[meniu.size()][7];
            int i = 0;
            for(MenuItem m: meniu){
                data[i][0] = m.getTitle();
                data[i][1] =String.valueOf(m.getRating());
                data[i][2] = String.valueOf(m.getCalories());
                data[i][3] = String.valueOf(m.getProtein());
                data[i][4] = String.valueOf(m.getFat());
                data[i][5] = String.valueOf(m.getSodium());
                data[i++][6] = String.valueOf(m.getPrice());
            }
            JTable jt=new JTable(data,column);
            JScrollPane sp=new JScrollPane(jt);
            jt.setBounds(30,40,1000,600);
            jFrame.add(sp);
            jFrame.setSize(1000,600);
            jFrame.setVisible(true);
        }
    }

    class ClientListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            LinkedList<User> u = deliveryService.clientsOrders(adminView.numarulMinimDeComenzi(),adminView.amount());
            JFrame jFrame = new JFrame();
            jFrame.setVisible(true);
            String []column = {"Id","Nume"};
            String [][] data = new String[u.size()][2];
            int i = 0;
            for(User user: u){
                data[i][0]= String.valueOf(((Client)user).getClientId());
                data[i++][1] = user.getUserName();
            }

            JTable jt=new JTable(data,column);
            JScrollPane sp=new JScrollPane(jt);
            jt.setBounds(30,40,200,300);
            jFrame.add(sp);
            jFrame.setSize(1000,600);
            jFrame.setVisible(true);

        }
    }

    class ProductsListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            LinkedList<MenuItem> meniu =  deliveryService.produseleDinZi(adminView.getDay(),adminView.getNumberOfTimes());
            JFrame jFrame = new JFrame();
            jFrame.setVisible(true);
            String []column = {"Titlu","Rating","Calories", "Protein","Fat","Sodium","Price"};

            String [][] data = new String[meniu.size()][7];
            int i = 0;
            for(MenuItem m: meniu){
                data[i][0] = m.getTitle();
                data[i][1] =String.valueOf(m.getRating());
                data[i][2] = String.valueOf(m.getCalories());
                data[i][3] = String.valueOf(m.getProtein());
                data[i][4] = String.valueOf(m.getFat());
                data[i][5] = String.valueOf(m.getSodium());
                data[i++][6] = String.valueOf(m.getPrice());
            }
            JTable jt=new JTable(data,column);
            JScrollPane sp=new JScrollPane(jt);
            jt.setBounds(30,40,1000,600);
            jFrame.add(sp);
            jFrame.setSize(1000,600);
            jFrame.setVisible(true);
        }
    }

    class AddListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            AddController addController = new AddController(deliveryService);
        }
    }

}
