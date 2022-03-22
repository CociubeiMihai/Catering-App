package business;

import presentation.EmployeeView;

import java.io.*;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Optional;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DeliveryService extends Observable implements IDeliveryServiceProcessing, Serializable {

    private HashMap<Order, LinkedList<MenuItem>> comenzi;
    private TreeSet<MenuItem> meniu;
    private LinkedList<User> users;

    public DeliveryService() {
        comenzi = new HashMap<Order, LinkedList<MenuItem>>();
        meniu = new TreeSet<MenuItem>();
        users = new LinkedList<>();

        users.add(new Admin("Mihai","12345",Role.ADMIN));
        users.add(new Employee("John","123",Role.EMPLOYEE));
        users.add(new Client(1,"Marcel","12",Role.CLIENT));
    }


    public boolean isWellFormed(){

        for(User user: users){
            if(user.getRole() == Role.ADMIN)
                return true;
        }
        return false;
    }

    public static Function<String, MenuItem> mapToProduct = (line) -> {
        String[] p = line.split(",");
        return new BaseProduct(p[0], Float.parseFloat(p[1]),Integer.parseInt(p[2]),Integer.parseInt(p[3]),
                Integer.parseInt(p[4]),Integer.parseInt(p[5]),Integer.parseInt(p[6]));
    };

    @Override
    public void populate() {
        try {
            InputStream is = new FileInputStream(new File("products.csv"));
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            meniu = br.lines()
                    .skip(1)
                    .map(mapToProduct)
                    .filter(menuItem -> menuItem.getRating() > 2)
                    .collect(Collectors.toCollection(TreeSet::new));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            assert(isWellFormed());
        }
    }

    @Override
    public void importProducts(MenuItem m) {
        meniu.add(m);
    }

    @Override
    public void manageProductsMen(MenuItem m) {
        meniu.remove(m);
    }

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
    @Override
    public void creadteOrder(Order order, LinkedList<MenuItem> m, User u, EmployeeView employeeView) {
        assert(order != null && m != null && u != null);
        comenzi.put(order, m);
        orderobservable(order,(Client)u ,m,employeeView);

        assert(comenzi.get(order) != null);
        assert(isWellFormed());
    }

    /**
     *
     * @param titlu numele produsului
     * @return returnez produsul cu numele introdus de utilizator
     * @pre titlu !=""
     */
    public MenuItem gasesteElementul(String titlu){
        assert (titlu !="");
        for(MenuItem m: meniu){
            if(m.getTitle().equals(titlu))
                return m;
        }
        System.out.println("Nu am gasit");
        System.out.println(titlu);
        assert(isWellFormed());
        return null;
    }

    @Override
    public void createAccount(User u) {
        users.add(u);
    }

    public TreeSet<MenuItem> getMeniu() {
        return meniu;
    }

    /*public User findByName(String name){
        for(User u : users){
            if(u.getUserName().equals(name)){
                return u;
            }
        }
        return null;
    }*/

    public User findByName(String name)
    {
        LinkedList<User> u =null;

         u = users.stream()
                .filter(user -> user.getUserName().equals(name))
                .collect(Collectors.toCollection(LinkedList::new));

        return u.getFirst();
    }


    public int userId(){
        int i = 0;
        for(User u: users){
            if(u instanceof Client){
                if(i < ((Client) u).getClientId()){
                    i = ((Client) u).getClientId();
                }
            }
        }
        return i+1;
    }

    public int idOrder(){
        int i = 0;
        for(Order o: comenzi.keySet()){
            if(i < o.getOrderID())
                i = o.getOrderID();
        }
        return i + 1;
    }

    public int cmputePrice(LinkedList<MenuItem> m){
        int total = 0;
        for(MenuItem men: m){
            total += men.getPrice();
        }
        return total;
    }

    /**
     * Sterge un element
     * @param titlu numele produsului'
     * @pre titlu != ""
     *
     */

     public void removeElement(String titlu){
        assert (titlu != "");
        LinkedList<MenuItem> items  = new LinkedList<>();

        items = meniu.stream()
                .filter(menu -> menu.getTitle().equals(titlu))
                .collect(Collectors.toCollection(LinkedList::new));
        for(MenuItem m: items){
            meniu.remove(m);
        }


        assert (isWellFormed());
    }


    /**
     *
     * @param titlu numele produsului
     * @param camp campul pe care il dorim sa il modificam
     * @param valoareNoua valoarea pe care o vom pune in locul celei vechi
     *
     * @pre titlu != "" && camp != "" && valoareNoua != ""
     */
    public void modifica(String titlu, String camp, String valoareNoua){
        assert (titlu != "" && camp != "" && valoareNoua != "");
        for(MenuItem m: meniu){
            if(m.getTitle().equals(titlu)){
                if(camp.equals("Titlu"))
                    m.setTitle(valoareNoua);
                else if(camp.equals("Rating"))
                    m.setRating(Float.parseFloat(valoareNoua));
                else if(camp.equals("Calories"))
                    m.setCalories(Integer.parseInt(valoareNoua));
                else if(camp.equals("Protein"))
                    m.setProtein(Integer.parseInt(valoareNoua));
                else if(camp.equals("Fat"))
                    m.setFat(Integer.parseInt(valoareNoua));
                else if(camp.equals("Sodium"))
                    m.setSodium(Integer.parseInt(valoareNoua));
                else if(camp.equals("Price"))
                    m.setPrice(Integer.parseInt(valoareNoua));
            }
        }
        assert(isWellFormed());
    }

    /**
     *
     * @param min ora minima
     * @param max ora maxima
     * @return o lista de comenzi
     *
     * @pre min <= max
     * @post elemente != null
     */
    public LinkedList<Order> comparOra(int min, int max){
        assert (min <= max);
        LinkedList<Order> elemente = new LinkedList<>();

        elemente = comenzi.keySet().stream()
                .filter(coanda -> coanda.getOrderDate().getHour() >= min && coanda.getOrderDate().getHour() <= max)
                .collect(Collectors.toCollection(LinkedList::new));
        assert (elemente != null);
        assert(isWellFormed());
        return elemente;
    }

    public int findPosition(MenuItem m){
        int i = 0;

        for(MenuItem menuItem: meniu){
            if(menuItem.getTitle().equals(m.getTitle()))
                return i;
            i++;
        }

        return -1;
    }

    public MenuItem findPosition(int i){
        int j = 0;
        for(MenuItem m: meniu){
            if(i == j)
                return m;
            j++;
        }
        return null;
    }

    /**
     *
     * @param nr numarul minim de elemente comandate
     * @return returneaza lista de MeniuItem care a fost comandata de mai mult de un nr de ori
     *
     * @pre nr > 0
     * @post m != null
     */
    public LinkedList<MenuItem> elementeComandate(int nr){
        assert (nr > 0);
        LinkedList<MenuItem> m = new LinkedList<>();
        int[] v = new int[meniu.size()];

        for(LinkedList<MenuItem> menuItem: comenzi.values()){
            for(MenuItem men: menuItem){
                v[findPosition(men)]++;

            }
        }
        for(int i = 0; i < meniu.size(); i++){
            if(v[i] >= nr)
                m.add(findPosition(i));
        }
        assert (m != null);
        assert(isWellFormed());
        return m;
    }

    /**
     *
     * @param number numarul minim de comenzi
     * @param value valoarea minima a unei comenzi
     * @return Lista de clienti care au comandat
     *
     * @pre number >= 1 && value >= 1
     */
    public LinkedList<User> clientsOrders(int number, int value){
        assert (number >= 1 && value >= 1);
        LinkedList<User> clients = new LinkedList<>();
        int[] v = new int[users.size()];

        for(Order ord: comenzi.keySet()){
            LinkedList<MenuItem> men = comenzi.get(ord);
            int price = cmputePrice(men);
            if(price >= value){
                for(User u: users){
                    if(u instanceof Client)
                    if(((Client)u).getClientId() == ord.getClientID()) {
                        v[((Client) u).getClientId()]++;
                        System.out.println(u.getUserName());
                    }

                }
            }
        }

        for(int i = 0; i < users.size();i++){
            if(v[i] >= number){
                for(User u: users){
                    if(u instanceof Client)
                    if(((Client)u).getClientId() == i)
                        clients.add(u);
                }
            }
        }
        assert(isWellFormed());
        return clients;
    }

    /**
     * Produsele comandate de un numar specificat de ori dintr-o zi din an
     * @param zi ziua din an
     * @param nr numarul minim de comenzi
     * @return o lista de Produse
     *
     * @pre zi >= 0 && zi <= 365 && nr != 0
     */
    public LinkedList<MenuItem> produseleDinZi(int zi, int nr){
        assert (zi >= 0 && zi <= 365 && nr != 0);
        LinkedList<MenuItem> menuIte = new LinkedList<>();
        int[] v = new int[meniu.size()];

        for(Order o: comenzi.keySet()){
            System.out.println(o.getOrderDate().getDayOfYear());
            if(o.getOrderDate().getDayOfYear() == zi){
                LinkedList<MenuItem> men = comenzi.get(o);
                for(MenuItem m: men){
                    v[findPosition(m)]++;
                }
            }

        }

        for(int i = 0; i < meniu.size();i++){
            if(v[i] >= nr)
            menuIte.add(findPosition(i));
        }
        assert(isWellFormed());
        return menuIte;
    }

    public void addNewElement(CompositeProduct compositeProduct){
        meniu.add(compositeProduct);
    }

    public HashMap<Order, LinkedList<MenuItem>> getComenzi() {
        return comenzi;
    }

    public LinkedList<User> getUsers() {
        return users;
    }

    public void setComenzi(HashMap<Order, LinkedList<MenuItem>> comenzi) {
        this.comenzi = comenzi;
    }

    public void setMeniu(TreeSet<MenuItem> meniu) {
        this.meniu = meniu;
    }

    public void setUsers(LinkedList<User> users) {
        this.users = users;
    }

    /**
     * Filtram toate produsele
     * @param min valoarea minima dupa care filtram
     * @param s campul dupa care filtram
     * @return o lista de produse filtrate
     *
     * @pre min != 0 && s != ""
     */
    public LinkedList<MenuItem> filtrare(int min, String s){
        assert (min != 0 && s != "");
        LinkedList<MenuItem> m = new LinkedList<>();
            if(s.equals("Rating")) {
               m =  meniu.stream()
                        .filter(menuItem -> menuItem.getRating() >= min)
                        .collect(Collectors.toCollection(LinkedList::new));
            }
            else if(s.equals("Calories")){
                m =  meniu.stream()
                        .filter(menuItem -> menuItem.getCalories() >= min)
                        .collect(Collectors.toCollection(LinkedList::new));
            }

            else if(s.equals("Protein")){
                m =  meniu.stream()
                        .filter(menuItem -> menuItem.getProtein() >= min)
                        .collect(Collectors.toCollection(LinkedList::new));
            }

            else if(s.equals("Fat")){
                m =  meniu.stream()
                        .filter(menuItem -> menuItem.getFat() >= min)
                        .collect(Collectors.toCollection(LinkedList::new));
            }

            else if(s.equals("Sodium")){
                m =  meniu.stream()
                        .filter(menuItem -> menuItem.getSodium() >= min)
                        .collect(Collectors.toCollection(LinkedList::new));
            }

            else if(s.equals("Price")){
                m =  meniu.stream()
                        .filter(menuItem -> menuItem.getPrice() >= min)
                        .collect(Collectors.toCollection(LinkedList::new));
            }
        assert(isWellFormed());
        return m;
    }
}

