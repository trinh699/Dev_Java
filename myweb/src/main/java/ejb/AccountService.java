package ejb;

import javax.ejb.Remote;

import entity.*;

@Remote
public interface AccountService {

    public User authenticate(String email, String pass);

    public void createAccount(User user);

    public void updateAccount(User user);

    public void logOut();

    public User getCurrentUser();

    public void setCurrentUser(User user);
}