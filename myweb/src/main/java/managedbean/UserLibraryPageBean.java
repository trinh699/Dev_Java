package managedbean;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entity.*;

@Named
@SessionScoped
public class UserLibraryPageBean extends BaseBean {
    private static final long serialVersionUID = 5324829994135860307L;
    private Map<String, List<Book>> library;
    @Inject
    private UserPageBean userPage;

    // --------------------------------------CONSTRUCTOR--------------------------------------

    public UserLibraryPageBean() {
        library = new HashMap<String, List<Book>>();
    }

    // --------------------------------------BEAN_FUNCTION--------------------------------------

    @PostConstruct
    public void initialize() {
        userPage.loadLibrary();
        List<BookCollection> collections = userPage.getUserBookCollections();
        for (BookCollection c : collections) {
            library.put(c.getCollectionName(), c.getCollectionBooks());
        }
    }

    public void viewLibrary() {
        for (Map.Entry<String, List<Book>> entry : library.entrySet()) {
            System.out.println("Collection: " + entry.getKey());
            for (Book book : entry.getValue()) {
                System.out.println("\t" + book.getTitle());
            }
        }
    }

    // --------------------------------------ACCESSORS--------------------------------------

    public Map<String, List<Book>> getLibrary() {
        return library;
    }

    public void setLibrary(Map<String, List<Book>> library) {
        this.library = library;
    }
}