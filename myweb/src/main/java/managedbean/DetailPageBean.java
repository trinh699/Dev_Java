package managedbean;

import ejb.DatabaseService;
import entity.Book;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named
@RequestScoped
public class DetailPageBean extends BaseBean {
    private static final long serialVersionUID = -9186255292380380072L;
    private Book selectedBook;
    @EJB
    private DatabaseService db;
    @Inject
    private CartPageBean cart;

    public DetailPageBean() {
        //
    }

    public String setSelectedBook(int bookId) {
        System.out.println("Book id: " + bookId);
        selectedBook = db.getBookById(bookId);
        cart.setRequestBookId(selectedBook.getBookId());
        return "detail.xhtml?faces-redirect=true";
    }

    public Book getSelectedBook() {
        return this.selectedBook;
    }
}