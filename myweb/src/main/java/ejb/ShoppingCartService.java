package ejb;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import entity.*;

@Remote
public interface ShoppingCartService {
    public void initialize();

    public void addBook(int bookId);

    public void removeBook(int bookId);

    public int getItemQuantity(Book book);

    public void setItemQuantity(Book book, Integer quantity);

    public double getTotal();

    public int getItemCount();

    public void removeAll();

    public Map<Book, Integer> getCartItems();

    public void setCartItems(Map<Book, Integer> cartItems);

    public void makeReceipt(int userId, List<OrderDetail> details, Receipt receipt);

    public Receipt getReceipt();
}