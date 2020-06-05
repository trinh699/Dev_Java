package ejb;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import entity.*;

@Stateful
public class Account implements AccountService {
    @EJB
    private DatabaseService db;
    private User currentUser;

    @Override
    public User authenticate(String email, String pass) {
        User user = db.verifyLogin(email, pass);
        setCurrentUser(user);
        return user;
    }

    @Override
    public void createAccount(User user) {
        db.createAccount(user);
    }

    @Override
    public void updateAccount(User user) {
        currentUser.setUserName(user.getUserName());
        currentUser.setUserMail(user.getUserMail());
        currentUser.setUserPhone(user.getUserPhone());
        currentUser.setUserAddress(user.getUserAddress());
        currentUser.setUserPassword(user.getUserPassword());
        db.updateAccount(currentUser);
    }

    @Override
    public User getCurrentUser() {
        return currentUser;
    }

    @Override
    public void setCurrentUser(User user) {
        currentUser = user;
    }

    @Override
    public void logOut() {
        currentUser = null;
    }

}