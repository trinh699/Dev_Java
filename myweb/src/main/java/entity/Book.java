package entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "book")
@NamedQueries({ @NamedQuery(name = "Book.getAllBooks", query = "SELECT b FROM Book b"),
        @NamedQuery(name = "Book.searchByTitle", query = "SELECT b FROM Book b WHERE b.title LIKE :patern"),
        @NamedQuery(name = "Book.searchByAuthor", query = "SELECT b FROM Book b WHERE b.author LIKE :patern") })
public class Book implements Serializable {
    private static final long serialVersionUID = -1310787088963256969L;

    @Id
    private Integer bookId;
    private String title;
    private String author;
    private Double rating;
    private String publisher;
    private Integer year;
    private Double price;
    private String cover;
    private String description;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "book_genre", joinColumns = @JoinColumn(name = "bookid"), inverseJoinColumns = @JoinColumn(name = "genreid"))
    private Set<Genre> bookGenreList = new HashSet<>();

    // Non-accessed
    @ManyToMany(mappedBy = "collectionBooks", cascade = CascadeType.PERSIST)
    private List<BookCollection> bookCollections;
    
    // Non-accessed
    @OneToMany(mappedBy = "orderBook")
    private Set<OrderDetail> bookOrders;

    public Book() {
    }

    public Book(int id, String title, String author, double rating, String publisher, int year, double price,
            String cover, String description) {
        this.bookId = id;
        this.title = title;
        this.author = author;
        this.rating = rating;
        this.publisher = publisher;
        this.year = year;
        this.cover = cover;
        this.price = price;
        this.description = description;
    }

    public Integer getBookid() {
        return bookId;
    }

    public void setBookid(Integer bookid) {
        this.bookId = bookid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Genre> getBookGenreList() {
        return bookGenreList;
    }

    public void setBookGenreList(Set<Genre> bookGenreList) {
        this.bookGenreList = bookGenreList;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Book))
            return false;
        Book other = (Book) object;
        return this.bookId.equals(other.bookId);
    }

}