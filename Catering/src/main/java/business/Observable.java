package business;

import presentation.EmployeeView;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class Observable {

    public void orderobservable(Order o, Client c, LinkedList<MenuItem> m, EmployeeView employeeView){
        int suma = 0;
        for(MenuItem menuItem: m){
            suma+= menuItem.getPrice();
        }
        String s = "";
        s += "Comanda cu numarul "+ o.getOrderID()+" a fost pusa la data "+ o.getOrderDate();
        s+= "\nDe catre "+c.getUserName() +" cu id ul" + c.getClientId() + " in valoare de " + suma;
        s+= "\nIar produsele comandate sunt:\n";
        for(MenuItem menuItem: m) {
            s += menuItem.getTitle() +"\n";
        }

        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter("bill.txt");
            myWriter.write(s);
            myWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        employeeView.setTextArea(s);


    }

}
