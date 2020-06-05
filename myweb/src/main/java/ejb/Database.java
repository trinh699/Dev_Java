package ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.*;

@Stateless
public class Database implements DatabaseService {

    @PersistenceContext(unitName = "MyUnits")
    private EntityManager em;

    public Database() throws Exception {
    }

    // ------------------------------------BOOK------------------------------------

    // Get all Books
    @SuppressWarnings("unchecked")
    public List<Book> getAllBooks() {
        Query q = em.createNamedQuery("Book.getAllBooks");
        List<Book> books = q.getResultList();
        for (Book book : books) {
            book.getBookGenreList().size();
        }
        return books;
    }

    // Search Books by author or title
    @SuppressWarnings("unchecked")
    public List<Book> getBooks(String field, String value) {
        Query q;
        if (field.equals("author")) {
            q = em.createNamedQuery("Book.searchByAuthor");
        } else {
            q = em.createNamedQuery("Book.searchByTitle");
        }
        q.setParameter("patern", "%" + value + "%");
        List<Book> books = q.getResultList();
        for (Book book : books) {
            book.getBookGenreList().size();
        }
        return books;
    }

    // Search Book by ID
    public Book getBookById(int id) {
        Book book = em.find(Book.class, id);
        book.getBookGenreList().size();
        return book;
    }

    // ------------------------------------COLLECTION------------------------------------

    // Get all BooksCollection
    @SuppressWarnings("unchecked")
    public List<BookCollection> getAllCollections() {
        Query q = em.createNamedQuery("BookCollection.getAll");
        List<BookCollection> bookCollections = q.getResultList();
        for (BookCollection c : bookCollections) {
            c.getCollectionGenre();
            c.getCollectionBooks().size();
        }
        return bookCollections;
    }

    // Search BookCollections by name
    @SuppressWarnings("unchecked")
    public List<BookCollection> getCollections(String name) {
        Query q = em.createNamedQuery("BookCollection.searchByName");
        q.setParameter("genre", name);
        List<BookCollection> bookCollections = q.getResultList();
        for (BookCollection c : bookCollections) {
            c.getCollectionGenre();
            c.getCollectionBooks().size();
        }
        return bookCollections;
    }

    // Get promoted BookCollections
    @SuppressWarnings("unchecked")
    public List<BookCollection> getPromotedCollections() {
        Query q = em.createNamedQuery("BookCollection.getPremades");
        List<BookCollection> bookCollections = q.getResultList();
        for (BookCollection c : bookCollections) {
            c.getCollectionGenre();
            c.getCollectionBooks().size();
        }
        return bookCollections;
    }

    // Search BookCollection by ID
    public BookCollection getCollectionById(int collectionId) {
        BookCollection collection = em.find(BookCollection.class, collectionId);
        collection.getCollectionBooks().size();
        collection.getCollectionGenre();
        return collection;
    }

    // ------------------------------------USER------------------------------------

    // Search User by ID
    public User getUserById(int id) {
        User user = em.find(User.class, id);
        user.getUserCollections().size();
        user.getUserReviews().size();
        user.getUserReceipts().size();
        return user;
    }

    // Search User by mail and password for authorization
    public User verifyLogin(String email, String pass) {
        Query q = em.createNamedQuery("User.verifyLogin");
        q.setParameter("mail", email);
        q.setParameter("pass", pass);
        User user = null;
        user = (User) q.getSingleResult();
        user.getUserCollections().size();
        for (BookCollection collection : user.getUserCollections()) {
            collection.getCollectionBooks().size();
        }
        user.getUserReviews().size();
        user.getUserReceipts().size();
        for (Receipt r : user.getUserReceipts()) {
            r.getOrderDetails().size();
        }
        return user;
    }

    // Create a new account
    public void createAccount(User user) {
        em.persist(user);
        return;
    }

    // Update an account
    public void updateAccount(User user) {
        em.merge(user);
        return;
    }

    // ------------------------------------RECEIPT------------------------------------

    // Search Receipt by ID
    public Receipt getReceiptById(int id) {
        Receipt o = em.find(Receipt.class, id);
        return o;
    }

    // Make Receipt
    public Receipt makeReceipt(Integer userId, List<OrderDetail> details, Receipt receipt) {
        receipt.setUser(this.getUserById(userId));
        for (OrderDetail o : details) {
            o.setDetailOrder(receipt);
            o.getOrderBook().hashCode();
            o.getDetailOrder().hashCode();
            // em.persist(o);
            receipt.addOrderDetail(o);
        }
        System.out.println("I'm in ejb.Database.makeReceipt()");
        em.persist(receipt);
        return receipt;
    }

    // ------------------------------------ADMIN_METHODS------------------------------------

    @Override
    public void create(Book book) {
        em.clear();
        em.persist(book);
    }

    @Override
    public void create(BookCollection collection) {
        em.clear();
        em.persist(collection);
    }

    @Override
    public void update(Book book) {
        em.clear();
        em.merge(book);
    }

    @Override
    public void update(BookCollection collection) {
        em.clear();
        em.merge(collection);
    }

    @Override
    public void delete(Book book) {
        em.remove(book);
    }

    @Override
    public void delete(BookCollection collection) {
        em.remove(collection);
    }

    @Override
    public void delete(User user) {
        em.remove(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Receipt> getAllReceipts() {
        Query q = em.createNamedQuery("Receipt.getAllReceipts");
        List<Receipt> receipts = q.getResultList();
        for (Receipt receipt : receipts) {
            receipt.getOrderDetails().size();
        }
        return receipts;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        Query q = em.createNamedQuery("User.getAllUsers");
        List<User> users = q.getResultList();
        for (User user : users) {
            user.getUserReceipts().size();
        }
        return users;
    }

}