package entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "review")
public class Review implements Serializable {
    private static final long serialVersionUID = -3227695145596672647L;

    private String reviewContent;

    @Id
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "bookid")
    private Book reviewBook;
    
    @Id
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userid")
    private User reviewUser;

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public Book getReviewBook() {
        return reviewBook;
    }

    public void setReviewBook(Book reviewBook) {
        this.reviewBook = reviewBook;
    }

    public User getReviewUser() {
        return reviewUser;
    }

    public void setReviewUser(User reviewUser) {
        this.reviewUser = reviewUser;
    }

}