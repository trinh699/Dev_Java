package managedbean.helper;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import entity.Book;

@Named
@SessionScoped
public class ShoppingCartItem implements Serializable {
    public static final long serialVersionUID = -2117560387169978901L;
    private Book item;
    private int quantity;

    public ShoppingCartItem() {
        //
    }

    public ShoppingCartItem(Book itemObj) {
        item = itemObj;
        quantity = 1;
    }

    // INCREASE QUANTITY BY 1
    public void incrementQuantity() {
        quantity++;
    }

    // DECREASE QUANTITY BY 1
    public void decrementQuantity() {
        quantity--;
    }

    // ACCESSORS
    public Book getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}