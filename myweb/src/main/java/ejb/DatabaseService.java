package ejb;

import entity.*;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface DatabaseService {
    public List<Book> getAllBooks();

    public List<Book> getBooks(String field, String value);

    public Book getBookById(int id);

    public List<BookCollection> getAllCollections();

    public List<BookCollection> getCollections(String name);

    public List<BookCollection> getPromotedCollections();

    public BookCollection getCollectionById(int collectionId);

    public User getUserById(int id);

    public User verifyLogin(String email, String pass);

    public void createAccount(User user);

    public void updateAccount(User user);

    public Receipt getReceiptById(int id);

    public Receipt makeReceipt(Integer userId, List<OrderDetail> details, Receipt receipt);

    // ADMIN METHODS

    public void create(Book book);

    public void create(BookCollection collection);

    public void update(Book book);

    public void update(BookCollection collection);

    public void delete(Book book);

    public void delete(BookCollection collection);

    public void delete(User user);

    public List<Receipt> getAllReceipts();

    public List<User> getAllUsers();
}