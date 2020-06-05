package managedbean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ejb.*;
import entity.*;

@Named
@SessionScoped
public class CartPageBean extends BaseBean {
    private static final long serialVersionUID = 1135454556376983355L;
    List<Book> bookList;
    Map<Book, Integer> bookQuantityList;
    private int itemCount;
    private Receipt receipt;
    private int requestBookId;
    @EJB
    private ShoppingCartService sc;
    @EJB
    private AccountService as;
    @Inject
    private UserPageBean userPageBean;

    // ------------------------------------------CONSTRUCTOR------------------------------------------
    @PostConstruct
    public void initialize() {
        bookList = new ArrayList<Book>();
        bookQuantityList = new HashMap<Book, Integer>();
        receipt = new Receipt();
        sc.initialize();
        loadCart();
    }

    // ------------------------------------------BEAN_FUNCTION------------------------------------------

    public String updateCart() {
        sc.setCartItems(bookQuantityList);
        return "cart.xhtml?faces-redirect=true";
    }

    public void addBook() { // Add a new book to cart
        sc.addBook(this.requestBookId);
        loadCart();
    }

    public String removeBook() { // Remove a book from cart
        System.out.println("REQUEST BOOKID: " + requestBookId);
        sc.removeBook(requestBookId);
        loadCart();
        return "cart.xhtml?faces-redirect=true";
    }

    public String buy() {
        if (getItemCount() < 1)
            return "cart.xhtml?faces-redirect=true";
        generateReceipt();
        // make transaction
        System.out.println("I'm in managedBean.CartPageBean.buy()");
        sc.makeReceipt(userPageBean.getUser().getUserId(), makeDetails(this.receipt), this.receipt);
        return "paypal.xhtml?faces-redirect=true";
    }

    public String removeAll() {
        sc.removeAll();
        bookList.clear();
        bookQuantityList.clear();
        initialize();
        return "welcome.xhtml?faces-redirect=true";
    }

    public int getRequestBookId() {
        return requestBookId;
    }

    // ------------------------------------------ACCESSORS------------------------------------------

    public double getTotal() { // Get total price
        return sc.getTotal();
    }

    public int getItemCount() { // Get number of item
        return this.itemCount;
    }

    public Receipt getReceipt() { // Get receipt
        return receipt;
    }

    public void setRequestBookId(int id) {
        this.requestBookId = id;
    }

    public int getItemQuantity(Book book) { // Return quantity of an item
        return sc.getItemQuantity(book);
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public Map<Book, Integer> getBookQuantityList() {
        return bookQuantityList;
    }

    public void setBookQuantityList(Map<Book, Integer> bookQuantityList) {
        this.bookQuantityList = bookQuantityList;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }
    // ------------------------------------------PRIVATE_METHODS------------------------------------------

    private List<OrderDetail> makeDetails(Receipt r) { // Make order details
        List<OrderDetail> details = new ArrayList<OrderDetail>();
        OrderBookKey keys = new OrderBookKey();
        keys.setOrderid(r.getOrderid());

        for (Book book : bookList) {
            keys.setBookid(book.getBookId());
            OrderDetail detail = new OrderDetail();
            detail.setAmount(getItemQuantity(book));
            detail.setOrderBook(book);
            detail.setId(keys);
            details.add(detail);
        }
        return details;
    }

    private String getNow() { // Get current datetime
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String s = dtf.format(now).toString();
        return s;
    }

    private void generateReceipt() { // Generate receipt
        receipt = new Receipt(getNow(), "PayPal", new Double(getTotal()));
    }

    private void loadCart() { // Get item and item quantity in cart
        bookList = new ArrayList<Book>();
        bookQuantityList = new HashMap<Book, Integer>();
        for (Book book : sc.getCartItems().keySet()) {
            bookList.add(book);
        }
        bookQuantityList = sc.getCartItems();
        this.itemCount = sc.getItemCount();
        this.receipt = sc.getReceipt();
    }

}