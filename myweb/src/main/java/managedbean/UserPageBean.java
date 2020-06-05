package managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import ejb.AccountService;
import entity.*;

@Named
@SessionScoped
public class UserPageBean extends BaseBean {
    private static final long serialVersionUID = -2813160860598505747L;
    private User user;
    private String name;
    private String email;
    private String password;
    private String address;
    private String phone;
    private List<BookCollection> userBookCollections;
    private List<Receipt> userReceipts;
    private List<Review> userReviews;
    @EJB
    private AccountService as;

    // ------------------------------------------CONSTRUCTOR------------------------------------------

    public UserPageBean() {
        user = new User();
        updateBean();
        userBookCollections = new ArrayList<BookCollection>();
        userReceipts = new ArrayList<Receipt>();
        userReviews = new ArrayList<Review>();
    }

    // ------------------------------------------BEAN_FUNCTION------------------------------------------

    public String signIn() { // Sign in
        User user = as.authenticate(email, password);
        if (user != null) {
            System.out.println("Loging in");
            this.user = user;
            System.out.println(this.user.getUserName());
            updateBean();
            return "welcome.xhtml?faces-redirect=true";
        }
        System.out.println("USER LOGGING IN: " + this.user.getUserName());
        return "fail";
    }

    public String signUp() { // Sign up
        User user = new User(this.name, this.password, this.address, this.phone, this.email);
        as.createAccount(user);
        User newUser = as.authenticate(email, password);
        if (newUser != null) {
            this.user = newUser;
            updateBean();
            return "welcome.xhtml?faces-redirect=true";
        } else {
            return "fail";
        }
    }

    public String signOut() { // Sign out
        as.logOut();
        user = null;
        updateBean();
        return "welcome.xhtml?faces-redirect=true";
    }

    public void updateAccount() { // Make changes to account
        User modifiedUser = new User(name, password, address, phone, email);
        as.updateAccount(modifiedUser);
        user = as.getCurrentUser();
        updateBean();
    }

    public void loadReviews() {
        userReviews = user.getUserReviews();
    }

    public void loadLibrary() {
        userBookCollections = user.getUserCollections();
    }

    public void loadReceipt() {
        userReceipts = user.getUserReceipts();
    }

    public String isLoggedIn() {
        if (this.name != null) {
            return "account.xhtml?faces-redirect=true";
        } else {
            return "signin.xhtml?faces-redirect=true";
        }
    }

    private void updateBean() {
        if (user != null) {
            this.name = user.getUserName();
            this.email = user.getUserMail();
            this.password = user.getUserPassword();
            this.address = user.getUserAddress();
            this.phone = user.getUserPhone();
        } else {
            this.name =null;
            this.email = null;
            this.password = null;
            this.address = null;
            this.phone = null;
        }
    }
    // ------------------------------------------ACESSORS------------------------------------------

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        if (name.length() == 0 || name == null) {
            return "Login";
        }
        return name;
    }

    public void setUserName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BookCollection> getUserBookCollections() {
        return userBookCollections;
    }

    public void setUserBookCollections(List<BookCollection> userBookCollections) {
        this.userBookCollections = userBookCollections;
    }

    public List<Receipt> getUserReceipts() {
        return userReceipts;
    }

    public void setUserReceipts(List<Receipt> userReceipts) {
        this.userReceipts = userReceipts;
    }

    public List<Review> getUserReviews() {
        return userReviews;
    }

    public void setUserReviews(List<Review> userReviews) {
        this.userReviews = userReviews;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}