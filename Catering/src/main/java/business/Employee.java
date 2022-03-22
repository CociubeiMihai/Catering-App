package business;

import java.io.Serializable;

public class Employee extends User implements Serializable {


    public Employee(String userName, String pass, Role role) {
        super(userName, pass, role);
    }
}
