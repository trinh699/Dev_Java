package ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import entity.Book;
import entity.BookCollection;
import entity.Receipt;
import entity.User;

@Stateful
public class Admin implements AdminService {
    @EJB
    private DatabaseService db;

    @Override
    public void create(Book book) {
        db.create(book);
    }

    @Override
    public void create(BookCollection collection) {
        db.create(collection);
    }

    @Override
    public void create(User user) {
        db.createAccount(user);
    }

    @Override
    public Book readBook(int id) {
        return db.getBookById(id);
    }

    @Override
    public BookCollection readBookCollection(int id) {
        return db.getCollectionById(id);
    }

    @Override
    public User readUser(int id) {
        return db.getUserById(id);
    }

    @Override
    public Receipt readReceipt(int id) {
        return db.getReceiptById(id);
    }

    @Override
    public void update(Book book) {
        db.update(book);
    }

    @Override
    public void update(BookCollection collection) {
        db.update(collection);
    }

    @Override
    public void update(User user) {
        db.updateAccount(user);
    }

    @Override
    public void delete(Book book) {
        db.delete(book);
    }

    @Override
    public void delete(BookCollection collection) {
        db.delete(collection);
    }

    @Override
    public void delete(User user) {
        db.delete(user);
    }

    @Override
    public List<Book> readAllBook() {
        return db.getAllBooks();
    }

    @Override
    public List<BookCollection> readAllBookCollection() {
        return db.getAllCollections();
    }

    @Override
    public List<User> readAllUser() {
        return db.getAllUsers();
    }

    @Override
    public List<Receipt> readAllReceipt() {
        return db.getAllReceipts();
    }

}