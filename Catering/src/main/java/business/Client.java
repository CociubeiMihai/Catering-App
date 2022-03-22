package business;

import java.io.Serializable;

public class Client extends User implements Comparable<Client>, Serializable{

    private int clientId;

    public Client(int clientId, String userName, String pass, Role role) {
        super(userName, pass, role);
        this.clientId = clientId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Override
    public int compareTo(Client o) {
        if(clientId == o.clientId){
            return 0;
        }
        return -1;
    }
}
