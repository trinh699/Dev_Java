package entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "genre")
public class Genre implements Serializable {
    private static final long serialVersionUID = 7578348807331883081L;

    @Id
    private Integer genreId;
    private String genrename;

    @ManyToMany(mappedBy = "bookGenreList", cascade = CascadeType.PERSIST)
    private Set<Book> genreBookList;

    @OneToOne(mappedBy = "collectionGenre")
    private BookCollection genreCollection;

    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }

    public String getGenrename() {
        return genrename;
    }

    public void setGenrename(String genrename) {
        this.genrename = genrename;
    }

    public Set<Book> getGenreBookList() {
        return genreBookList;
    }

    public BookCollection getGenreCollection() {
        return genreCollection;
    }

}