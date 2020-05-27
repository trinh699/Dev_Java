package managedbean.helper;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import entity.*;

@Named
@SessionScoped
public class ShoppingCart implements Serializable {
    private static final long serialVersionUID = 7889289171540881053L;

    private HashMap<Integer, ShoppingCartItem> cart;
    private int itemCount;
    private double total;

    // ----------------------------------CONSTRUCTORS----------------------------------

    public ShoppingCart() {
        cart = new HashMap<Integer, ShoppingCartItem>();
        itemCount = 0;
    }

    // ----------------------------------CART_FUNCTIONS----------------------------------

    // increment an item quantity
    public synchronized void add(int bookId) {
        if (cart.containsKey(bookId)) {
            ShoppingCartItem item = (ShoppingCartItem) cart.get(bookId);
            item.incrementQuantity();
        }
        updateItemCount();
        updateTotal();
    }

    // decrement an item quantity
    public synchronized void remove(int bookId) {
        if (cart.containsKey(bookId)) {
            ShoppingCartItem item = (ShoppingCartItem) cart.get(bookId);
            item.decrementQuantity();
            if (item.getQuantity() < 1) {
                cart.remove(bookId);
            }
            updateItemCount();
            updateTotal();
        }
    }

    // add a new item to cart
    public synchronized void addItem(Book book) {
        ShoppingCartItem item = new ShoppingCartItem(book);
        cart.put(book.getBookId(), item);
        updateItemCount();

        updateItemCount();
        updateTotal();
    }

    // remove all items
    public synchronized void clear() {
        cart.clear();
        updateItemCount();
        updateTotal();
    }

    // ----------------------------------ACESSORS----------------------------------

    public Set<ShoppingCartItem> getCartItems() {
        Set<ShoppingCartItem> temp = new HashSet<ShoppingCartItem>();
        for (ShoppingCartItem item : cart.values()) {
            temp.add(item);
        }
        return temp;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    // ----------------------------------PRIVATE_METHODS----------------------------------

    private void updateItemCount() {
        int count = 0;
        for (ShoppingCartItem item : cart.values()) {
            count += item.getQuantity();
        }
        itemCount = count;
    }

    private void updateTotal() {
        double amount = 0;
        for (ShoppingCartItem item : cart.values()) {
            amount += item.getItem().getPrice() * item.getQuantity();
        }
        total = amount;
    }

}