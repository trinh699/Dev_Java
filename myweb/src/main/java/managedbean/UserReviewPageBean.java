package managedbean;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entity.*;

@Named
@SessionScoped
public class UserReviewPageBean extends BaseBean {
    private static final long serialVersionUID = -1831610155289959853L;
    private Map<Book, String> reviews;
    @Inject
    private UserPageBean userPage;

    // --------------------------------------CONSTRUCTOR--------------------------------------

    public UserReviewPageBean() {
        reviews = new HashMap<Book, String>();
    }

    // --------------------------------------BEAN_FUNCTION--------------------------------------

    @PostConstruct
    public void initialize() {
        userPage.loadReviews();
        List<Review> userReviews = userPage.getUserReviews();
        for (Review review : userReviews) {
            reviews.put(review.getReviewBook(), review.getReviewContent());
        }
    }

    public void viewReviews() {
        for (Map.Entry<Book, String> entry : reviews.entrySet()) {
            System.out.println("Review");
            System.out.println("Book: " + entry.getKey().getTitle());
            System.out.println("Review: " + entry.getValue());
        }
    }

    // --------------------------------------ACCESSORS--------------------------------------

    public List<String> getReviews() {
        List<String> reviews = new ArrayList<String>();
        for (Book book : this.reviews.keySet()) {
            reviews.add(this.reviews.get(book));
        }
        return reviews;
    }

    public List<Book> getBooks() {
        List<Book> books = new ArrayList<Book>();
        for (Book book : reviews.keySet()) {
            books.add(book);
        }
        return books;
    }
}