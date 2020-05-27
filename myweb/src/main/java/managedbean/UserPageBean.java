package managedbean;

import java.util.HashMap;
import java.util.Set;

import entity.BookCollection;

public class UserPageBean extends BaseBean {
    private static final long serialVersionUID = -2813160860598505747L;

    private HashMap<String, String> userDetails;
    private HashMap<Integer, String> reviews;
    private Set<BookCollection> library;
    
    // ACESSORS
    public HashMap<String, String> getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(HashMap<String, String> userDetails) {
        this.userDetails = userDetails;
    }

    public HashMap<Integer, String> getReviews() {
        return reviews;
    }

    public Set<BookCollection> getLibrary() {
        return library;
    }

}