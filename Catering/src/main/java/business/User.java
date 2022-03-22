package business;

import java.io.Serializable;

public class User implements Serializable {

    private String userName;
    private String pass;
    private Role role;

    public User(String userName, String pass, Role role) {
        this.userName = userName;
        this.pass = pass;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
