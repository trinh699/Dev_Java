package managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import ejb.DatabaseService;
import entity.Book;
import entity.BookCollection;

@Named
@SessionScoped
public class HomePageBean extends BaseBean {
    private static final long serialVersionUID = 4870225850728057511L;

    private List<List<Book>> promotions;
    private List<String> collectionNames;
    @EJB
    DatabaseService db;

    // ------------------------------------------CONSTRUCTOR------------------------------------------
    public HomePageBean() {
        promotions = new ArrayList<List<Book>>();
        collectionNames = new ArrayList<String>();
    }

    @PostConstruct
    private void makePromotion() { // Populate Promotion list
        List<BookCollection> collections = db.getPromotedCollections();
        for (BookCollection c : collections) {
            promotions.add(c.getCollectionBooks());
            collectionNames.add(c.getCollectionName());
        }
    }

    // ------------------------------------------ACCESSOR------------------------------------------
    public List<List<Book>> getPromotions() {
        return promotions;
    }

    public List<String> getKeyList() {
        return collectionNames;
    }

}