package business;

import presentation.EmployeeView;

import java.util.LinkedList;

public interface IDeliveryServiceProcessing {//main operations that can be executed by users

    public void populate();

    public void importProducts(MenuItem m);

    public void manageProductsMen(MenuItem m);

    /**
     *
     * @param titlu numele produsului
     * @return returnez produsul cu numele introdus de utilizator
     * @pre titlu !=""
     */
    public MenuItem gasesteElementul(String titlu);
    public User findByName(String name);
    public int userId();
    public int idOrder();
    public int cmputePrice(LinkedList<MenuItem> m);

    /**
     * Sterge un element
     * @param titlu numele produsului'
     * @pre titlu != ""
     *
     */
    public void removeElement(String titlu);

    /**
     *
     * @param titlu numele produsului
     * @param camp campul pe care il dorim sa il modificam
     * @param valoareNoua valoarea pe care o vom pune in locul celei vechi
     *
     * @pre titlu != "" && camp != "" && valoareNoua != ""
     */
    public void modifica(String titlu, String camp, String valoareNoua);

    /**
     *
     * @param order datele despre comanda
     * @param m elementele comandate
     * @param u cel care a comandat
     * @param employeeView pentru a notifica angajatul
     *
     * @pre order != null && m != null && u != null
     * @post comenzi.get(order) != null
     */
    public void creadteOrder(Order order, LinkedList<MenuItem> m, User u, EmployeeView employeeView);
    public void createAccount(User u);

    /**
     * Cauta toate comenzile dintr-un anumit interval orar
     * @param min ora minima
     * @param max ora maxima
     * @return o lista de comenzi
     *
     * @pre min <= max
     * @post elemente != null
     */
    public LinkedList<Order> comparOra(int min, int max);

    /**
     *
     * @param nr numarul minim de elemente comandate
     * @return returneaza lista de MeniuItem care a fost comandata de mai mult de un nr de ori
     *
     * @pre nr > 0
     * @post m != null
     */
    public LinkedList<MenuItem> elementeComandate(int nr);

    /**
     *
     * @param number numarul minim de comenzi
     * @param value valoarea minima a unei comenzi
     * @return Lista de clienti care au comandat
     *
     * @pre number >= 1 && value >= 1
     */
    public LinkedList<User> clientsOrders(int number, int value);

    /**
     * Produsele comandate de un numar specificat de ori dintr-o zi din an
     * @param zi ziua din an
     * @param nr numarul minim de comenzi
     * @return o lista de Produse
     *
     * @pre zi >= 0 && zi <= 365 && nr != 0
     */
    public LinkedList<MenuItem> produseleDinZi(int zi, int nr);

    /**
     * Filtram toate produsele
     * @param min valoarea minima dupa care filtram
     * @param s campul dupa care filtram
     * @return o lista de produse filtrate
     *
     * @pre min != 0 && s != ""
     */
    public LinkedList<MenuItem> filtrare(int min, String s);

}
