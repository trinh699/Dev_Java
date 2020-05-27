package entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@NamedQueries({
    @NamedQuery(name = "User.verifyLogin", query = "SELECT u FROM User u WHERE u.userMail = :mail AND u.userPassword = :pass")
})
public class User implements Serializable {
    private static final long serialVersionUID = -5150606925567750371L;

    @Id
    private Integer userId;
    private String userName;
    private String userPassword;
    private String userAddress;
    private String userPhone;
    private String userMail;

    @OneToMany(mappedBy = "collectionUser", cascade = CascadeType.ALL)
    private List<BookCollection> userCollections;

    @OneToMany(mappedBy = "reviewUser", cascade = CascadeType.ALL)
    private List<Review> userReviews;

    @OneToMany(mappedBy = "orderUser", cascade = CascadeType.ALL)
    private Set<Receipt> userReceipts;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public List<BookCollection> getUserCollections() {
        return userCollections;
    }

    public void setUserCollections(List<BookCollection> userCollections) {
        this.userCollections = userCollections;
    }

    public List<Review> getUserReviews() {
        return userReviews;
    }

    public void setUserReviews(List<Review> userReviews) {
        this.userReviews = userReviews;
    }

}