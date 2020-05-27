package managedbean;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import ejb.DatabaseOperations;
import managedbean.BaseBean;
import entity.*;

@Named
@SessionScoped
public class AuthorizationPageBean extends BaseBean {

    private static final long serialVersionUID = 3495649786202439107L;

    private String email;
    private String password;

    // --------------------------------CONSTRUCTORS--------------------------------
    public AuthorizationPageBean() {
    }

    // --------------------------------PAGE_FUNCTIONS--------------------------------
    public String verifyLogin() {
        User u = DatabaseOperations.verifyLogin(email, password);
        if (u == null) {
            return "Fail Login";
        } else {
            this.user = u;
            return "Login Successs";
        }
    }

    // --------------------------------ACCESSORS--------------------------------
    public String getUserName() {
        return email;
    }

    public void setUserName(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
