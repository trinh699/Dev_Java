package managedbean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import ejb.DatabaseOperations;
import entity.Book;
import entity.BookCollection;

@Named("home")
@SessionScoped
public class HomePageBean extends BaseBean {
    private static final long serialVersionUID = 4870225850728057511L;

    private List<List<Book>> promotions;
    private List<String> collectionNames;

    // Constructor
    public HomePageBean() {
        promotions = new ArrayList<List<Book>>();
        collectionNames = new ArrayList<String>();
        makePromotion();
    }

    // Populate Promotion list
    private void makePromotion() {
        List<BookCollection> collections = DatabaseOperations.getPromotedCollections();
        for (BookCollection c : collections) {
            promotions.add(c.getCollectionBooks());
            collectionNames.add(c.getCollectionName());
        }
    }
    // ACCESSOR
    public List<List<Book>> getPromotions() {
        return promotions;
    }

    public List<String> getKeyList() {
        return collectionNames;
    }
}