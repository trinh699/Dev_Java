package managedbean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import ejb.DatabaseOperations;
import entity.Book;
import entity.BookCollection;

@Named("list")
@SessionScoped
public class ListPageBean extends BaseBean {
    private static final long serialVersionUID = -7900371187282104730L;

    private Set<Integer> selectedFilter;
    private List<Book> books;
    private boolean isRefreshed;

    // Constructor
    public ListPageBean() {
        isRefreshed = false;
        books = new ArrayList<Book>();
        selectedFilter = new HashSet<Integer>();
    }

    // GET LIST BASED ON TITLE OR AUTHOR
    public void searchBook(String field, String searchTerm) {
        books = new ArrayList<Book>();
        if(field.isEmpty() || searchTerm.isEmpty()){
            books = DatabaseOperations.getAllBooks();
        }
        books = DatabaseOperations.getBooks(field, searchTerm);
    }

    // GET A FILTERED BOOK LIST
    public void searchByFilter() {
        for (int collectionId : selectedFilter) {
            BookCollection temp = DatabaseOperations.getCollectionById(collectionId);
            filterBookList(temp.getCollectionBooks());
        }
        return;
    }

    // GET FILTERED BOOK IDS
    private void filterBookList(List<Book> list) {
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

    // ACCESSORS
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

}