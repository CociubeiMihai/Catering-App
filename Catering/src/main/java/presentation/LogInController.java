package presentation;

import business.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.TreeSet;

public class LogInController {

        private View view;
        private DeliveryService deliveryService;
        private AdminView adminView =  new AdminView();
        private EmployeeView employeeView = new EmployeeView();

        public LogInController(){
            view = new View();
            deliveryService = new DeliveryService();

            deliveryService.populate();

            view.logInActionlistener(new Logare());
            view.registerActionListener(new Register());

        }

        class Logare implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                String nume = view.getNume();
                try {
                    User u = deliveryService.findByName(nume);
                    LinkedList<MenuItem> m = new LinkedList<>();
                    if(u!= null && u.getPass().equals(view.genPassword())){
                        if(u.getRole() == Role.CLIENT){
                            ClientView clientView = new ClientView();
                            clientView.setVizibile();
                            clientView.addListener(new OrderListener(u,m,clientView));
                            clientView.produseActionListener(new Produse());
                            clientView.removeElement(new RemoveElement(m,clientView));
                            clientView.orderActionListener(new OrderActionListener(m, u,clientView,employeeView));
                            clientView.filterListener(new FilterListener(clientView));
                        }
                        if(u.getRole() == Role.ADMIN){
                            adminView.setVizibile();
                            AdminController adminController = new AdminController(deliveryService,adminView);
                        }
                        if(u.getRole() == Role.EMPLOYEE){
                            employeeView.setVizibile();
                        }

                    }
                    else {
                        JFrame f = new JFrame();
                        f.setBounds(100, 100, 450, 300);
                        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        f.setVisible(true);
                        JLabel jl = new JLabel("Parola a fost introdusa gresit sau nu exista acest utilizator");
                        f.add(jl);
                    }
                }catch (NoSuchElementException a){
                    JFrame f = new JFrame();
                    f.setBounds(100, 100, 450, 300);
                    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    f.setVisible(true);
                    JLabel jl = new JLabel("Nu exista acest utilizator");
                    f.add(jl);
                }
            }

            class FilterListener implements ActionListener{

                ClientView clientView;

                public FilterListener(ClientView clientView) {
                    this.clientView = clientView;
                }

                @Override
                public void actionPerformed(ActionEvent e) {
                    LinkedList<MenuItem> meniu =  deliveryService.filtrare(clientView.filterValue(),clientView.comboBoxval());
                    System.out.println(meniu.size());
                    JFrame jFrame = new JFrame();
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

            class OrderActionListener implements ActionListener{

                LinkedList<MenuItem> menuItems;
                User u;
                ClientView clientView;
                EmployeeView employeeView;

                public OrderActionListener(LinkedList<MenuItem> menuItems, User u, ClientView clientView,EmployeeView employeeView) {
                    this.menuItems = menuItems;
                    this.u = u;
                    this.clientView = clientView;
                    this.employeeView = employeeView;
                }

                @Override
                public void actionPerformed(ActionEvent e) {
                    int orderId = deliveryService.idOrder();
                    Order order= new Order(orderId,((Client)u).getClientId(),LocalDateTime.now());
                    LinkedList<MenuItem> men = new LinkedList<>();
                    for(MenuItem m: menuItems){
                        men.add(m);
                    }
                    deliveryService.creadteOrder(order,men,u,employeeView);
                    //deliveryService.order();
                    menuItems.clear();
                    clientView.serTotal(deliveryService.cmputePrice(menuItems));
                    clientView.removeOrder();
                }
            }

            class RemoveElement implements ActionListener{

                LinkedList<MenuItem> m ;
                ClientView clientView;

                public RemoveElement(LinkedList<MenuItem> m, ClientView clientView) {
                    this.m = m;
                    this.clientView = clientView;
                }

                @Override
                public void actionPerformed(ActionEvent e) {
                    int x = Integer.parseInt(clientView.getText());
                    x--;
                    clientView.removeTable(x);
                    m.remove(x);
                    clientView.serTotal(deliveryService.cmputePrice(m));
                }
            }

            class OrderListener implements ActionListener{

                User u;
                LinkedList<MenuItem> menuItems;
                ClientView clientView;

                public OrderListener(User u, LinkedList<MenuItem> menuItems, ClientView clientView) {
                    this.u = u;
                    this.menuItems = menuItems;
                    this.clientView = clientView;
                }

                @Override
                public void actionPerformed(ActionEvent e) {
                    String titlu = clientView.getText();
                    MenuItem m = deliveryService.gasesteElementul(titlu);
                    menuItems.add(m);

                    String[] s = new String[7];
                    s[0] = m.getTitle();
                    s[1] =String.valueOf(m.getRating());
                    s[2] = String.valueOf(m.getCalories());
                    s[3] = String.valueOf(m.getProtein());
                    s[4] = String.valueOf(m.getFat());
                    s[5] = String.valueOf(m.getSodium());
                    s[6] = String.valueOf(m.getPrice());
                    clientView.addTable(s);
                    clientView.serTotal(deliveryService.cmputePrice(menuItems));

                }

                public void reset(){
                    menuItems = new LinkedList<>();
                }

            }

            class Produse implements ActionListener{

                @Override
                public void actionPerformed(ActionEvent e) {

                    JFrame jFrame = new JFrame();
                    String []column = {"Titlu","Rating","Calories", "Protein","Fat","Sodium","Price"};

                   TreeSet<MenuItem> meniu =  deliveryService.getMeniu();
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
        }

        class Register implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                Client u = new Client(deliveryService.userId(),view.getNume(),view.genPassword(), Role.CLIENT);
                deliveryService.createAccount(u);
            }
        }

}
