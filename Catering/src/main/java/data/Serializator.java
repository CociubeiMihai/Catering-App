package data;

import business.DeliveryService;
import business.MenuItem;
import business.Order;
import business.User;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeSet;

public class Serializator {

    private File fMeniu = new File("meniuri.txt");
    private File fUsers = new File("Users.txt");
    private File fComenzi = new File("Comenzi.txt");

    public void serialization(DeliveryService de){
        try {

            FileOutputStream fileMeniu = new FileOutputStream (fMeniu);
            FileOutputStream fileUseri = new FileOutputStream (fUsers);
            FileOutputStream fileComenzi = new FileOutputStream (fComenzi);

            ObjectOutputStream outMeniu = new ObjectOutputStream (fileMeniu);
            ObjectOutputStream outUseri = new ObjectOutputStream (fileUseri);
            ObjectOutputStream outComenzi = new ObjectOutputStream (fileComenzi);

            outMeniu.writeObject(de.getMeniu()); // Method for serialization of object
            outUseri.writeObject(de.getUsers());
            outComenzi.writeObject(de.getComenzi());

            outMeniu.close();
            outComenzi.close();
            outUseri.close();

            fileComenzi.close();
            fileUseri.close();
            fileMeniu.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void derialization(DeliveryService de){

        try {
            FileInputStream fileMeniu = new FileInputStream (fMeniu);
            FileInputStream fileUseri = new FileInputStream (fUsers);
            FileInputStream fileComenzi = new FileInputStream (fComenzi);

            ObjectInputStream inMeniu = new ObjectInputStream (fileMeniu);
            ObjectInputStream inUseri = new ObjectInputStream (fileUseri);
            ObjectInputStream inComenzi = new ObjectInputStream (fileComenzi);


            de.setMeniu((TreeSet<MenuItem>)inMeniu.readObject()); // Method for derialization of object
            de.setUsers((LinkedList<User>)inUseri.readObject());
            de.setComenzi((HashMap< Order, LinkedList<MenuItem>>)inComenzi.readObject());

            inMeniu.close();
            inUseri.close();
            inComenzi.close();

            fileMeniu.close();
            fileUseri.close();
            fileComenzi.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

}
