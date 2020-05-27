package ejb;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entity.Book;
import entity.BookCollection;
import entity.OrderDetail;
import entity.Receipt;
import entity.User;

public class DatabaseOperations {
    private static EntityManager em;
    private static EntityTransaction transactionObj;

    // ------------------------------------BOOK------------------------------------

    // Get all Books
    @SuppressWarnings("unchecked")
    public static List<Book> getAllBooks() {
        em = Persistence.createEntityManagerFactory("MyUnits").createEntityManager();
        Query q = em.createNamedQuery("Book.getAllBooks");
        List<Book> books = q.getResultList();
        em.close();
        return books;
    }

    // Search Books by author or title
    @SuppressWarnings("unchecked")
    public static List<Book> getBooks(String field, String value) {
        em = Persistence.createEntityManagerFactory("MyUnits").createEntityManager();
        Query q;
        if (field.equals("author")) {
            q = em.createNamedQuery("Book.searchByAuthor");
        } else {
            q = em.createNamedQuery("Book.searchByTitle");
        }
        q.setParameter("patern", "%" + value + "%");
        List<Book> list = q.getResultList();
        em.close();
        return list;
    }

    // Search Book by ID
    public static Book getBookById(int id) {
        em = Persistence.createEntityManagerFactory("MyUnits").createEntityManager();
        Book b = em.find(Book.class, id);
        em.close();
        return b;
    }

    // ------------------------------------COLLECTION------------------------------------

    // Get all BooksCollection
    @SuppressWarnings("unchecked")
    public static List<BookCollection> getAllCollections() {
        em = Persistence.createEntityManagerFactory("MyUnits").createEntityManager();
        Query q = em.createNamedQuery("BookCollection.getAll");
        List<BookCollection> list = q.getResultList();
        em.close();
        return list;
    }

    // Search BookCollections by name
    @SuppressWarnings("unchecked")
    public static List<BookCollection> getCollections(String name) {
        em = Persistence.createEntityManagerFactory("MyUnits").createEntityManager();
        Query q = em.createNamedQuery("BookCollection.searchByName");
        q.setParameter("genre", name);
        List<BookCollection> list = q.getResultList();
        em.close();
        return list;
    }

    // Get promoted BookCollections
    @SuppressWarnings("unchecked")
    public static List<BookCollection> getPromotedCollections() {
        em = Persistence.createEntityManagerFactory("MyUnits").createEntityManager();
        Query q = em.createNamedQuery("BookCollection.getPremades");
        List<BookCollection> list = q.getResultList();
        em.close();
        return list;
    }

    // Search BookCollection by ID
    public static BookCollection getCollectionById(int collectionId) {
        em = Persistence.createEntityManagerFactory("MyUnits").createEntityManager();
        BookCollection collection = em.find(BookCollection.class, collectionId);
        em.close();
        return collection;
    }

    // ------------------------------------USER------------------------------------

    // Search User by ID
    public static User getUserById(int id) {
        em = Persistence.createEntityManagerFactory("MyUnits").createEntityManager();
        User u = em.find(User.class, id);
        em.close();
        return u;
    }

    // Search User by mail and password for authorization
    public static User verifyLogin(String email, String pass) {
        em = Persistence.createEntityManagerFactory("MyUnits").createEntityManager();
        Query q = em.createNamedQuery("User.verifyLogin");
        q.setParameter("mail", email);
        q.setParameter("pass", pass);
        User u = null;
        try {
            u = (User) q.getSingleResult();
        } finally {
            em.close();
            return u != null ? u : null;
        }
    }

    // ------------------------------------RECEIPT------------------------------------

    // Search Receipt by ID
    public static Receipt getReceiptById(int id) {
        em = Persistence.createEntityManagerFactory("MyUnits").createEntityManager();
        Receipt o = em.find(Receipt.class, id);
        em.close();
        return o;
    }

    // Make Receipt
    public static void makeReceipt(User user, Set<OrderDetail> details, Receipt r) {
        em = Persistence.createEntityManagerFactory("MyUnits").createEntityManager();
        transactionObj = em.getTransaction();
        transactionObj.begin();

        r.setOrderUser(user);
        r.setOrderDetails(details);

        em.persist(r);
        transactionObj.commit();
        em.close();
    }

}