package az.edu.ada.wm2.Assignment.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.List;

/**
 * The Book class represents a book entity. It contains information about the book such as title, publication year, genre, and authors.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer id;

    @Column(name = "title")
    @NotBlank(message = "Title is required")
    private String title;

    @Column(name = "publication_year")
    @NotNull(message = "Publication Year is required")
    private Integer publicationYear;

    @Column(name = "genre")
    @NotBlank(message = "Genre is required")
    private String genre;

    @ManyToMany
    @JoinTable(name = "book_authors", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;

    /**
     * Constructs a Book object with the given title, authors, publication year, and genre.
     *
     * @param title           The title of the book.
     * @param authors         The list of authors of the book.
     * @param publicationYear The publication year of the book.
     * @param genre           The genre of the book.
     */
    public Book(String title, List<Author> authors, Integer publicationYear, String genre) {
        this.title = title;
        this.authors = authors;
        this.publicationYear = publicationYear;
        this.genre = genre;
    }
}
