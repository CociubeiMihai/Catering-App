package business;

import java.io.Serializable;

public class Admin extends User implements Serializable {

    public Admin(String userName, String pass, Role role) {
        super(userName, pass, role);
    }
}
