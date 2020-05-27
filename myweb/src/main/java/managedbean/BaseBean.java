package managedbean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import entity.User;
import managedbean.helper.ShoppingCart;

@Named
@SessionScoped
public class BaseBean implements Serializable {
    public static final long serialVersionUID = -3280333692217446994L;

    protected ShoppingCart cart;
    protected Boolean isLoggedIn;
    protected User user;
}