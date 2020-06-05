package managedbean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import ejb.DatabaseService;
import entity.Book;
import entity.BookCollection;

@Named("list")
@SessionScoped
public class ListPageBean extends BaseBean {
    private static final long serialVersionUID = -7900371187282104730L;
    @EJB
    DatabaseService db;
    private Set<Integer> selectedFilter;
    private List<Book> books;
    private boolean isRefreshed;

    // ------------------------------------------CONSTRUCTOR------------------------------------------
    public ListPageBean() {
        isRefreshed = false;
        books = new ArrayList<Book>();
        selectedFilter = new HashSet<Integer>();
    }

    // ------------------------------------------PAGE_FUNCTION------------------------------------------

    public void searchBook(String field, String searchTerm) { // Search for book by title or author
        books = new ArrayList<Book>();
        if (field.isEmpty() || searchTerm.isEmpty()) {
            books = this.db.getAllBooks();
        }
        books = this.db.getBooks(field, searchTerm);
    }

    public void searchByFilter() { // Apply filter to result list
        for (int filterOption : selectedFilter) {
            BookCollection temp = this.db.getCollectionById(filterOption);
            filterBookList(temp.getCollectionBooks());
        }
        return;
    }

    // ------------------------------------------ACCESSORS------------------------------------------
    public Set<Integer> getSelectedFilter() {
        return selectedFilter;
    }

    public void setSelectedFilter(Set<Integer> selectedFilter) {
        this.selectedFilter = selectedFilter;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public boolean isRefreshed() {
        return isRefreshed;
    }

    public void setRefreshed(boolean isRefreshed) {
        this.isRefreshed = isRefreshed;
    }

    // ------------------------------------------PRIVATE_METHOD------------------------------------------
    private void filterBookList(List<Book> list) { // Filter result list
        if (books.size() < 1 || books == null) {
            books = list;
        } else {
            List<Book> temp = new ArrayList<Book>();
            for (Book tempBook : list) {
                for (Book b : books) {
                    if (b.equals(tempBook)) {
                        temp.add(b);
                        break;
                    } else {
                        continue;
                    }
                }
            }
            books = temp;
        }
    }

}