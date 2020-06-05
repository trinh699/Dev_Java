package ejb;

import java.util.List;

import javax.ejb.Remote;

import entity.*;

@Remote
public interface AdminService {
    public void create(Book book);

    public void create(BookCollection collection);

    public void create(User user);

    public Book readBook(int id);

    public BookCollection readBookCollection(int id);

    public User readUser(int id);

    public Receipt readReceipt(int id);

    public void update(Book book);

    public void update(BookCollection collection);

    public void update(User user);

    public void delete(Book book);

    public void delete(BookCollection collection);

    public void delete(User user);

    public List<Book> readAllBook();

    public List<BookCollection> readAllBookCollection();

    public List<User> readAllUser();

    public List<Receipt> readAllReceipt();
}