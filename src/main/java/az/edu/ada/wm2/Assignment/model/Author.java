
package az.edu.ada.wm2.Assignment.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.List;

/**
 * The Author class represents an author of a book. It contains information about the author such as full name, birth year, and nationality.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Integer id;

    @Column(name = "full_name")
    @NotBlank(message = "Name is required")
    private String fullName;

    @Column(name = "birth_year")
    @NotNull(message = "Birth year is required")
    private Integer birthYear;

    @Column(name = "nationality")
    @NotBlank(message = "Nationality is required")
    private String nationality;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books;

    /**
     * Constructs an Author object with the given full name, birth year, and nationality.
     *
     * @param fullName    The full name of the author.
     * @param birthYear   The birth year of the author.
     * @param nationality The nationality of the author.
     */
    public Author(String fullName, Integer birthYear, String nationality) {
        this.fullName = fullName;
        this.birthYear = birthYear;
        this.nationality = nationality;
    }
}
