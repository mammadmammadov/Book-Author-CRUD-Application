package az.edu.ada.wm2.Assignment.repository;

import az.edu.ada.wm2.Assignment.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for performing CRUD operations and filtering on Book entities.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    /**
     * Finds books based on various search criteria, such as title, author name, publication year, and genre.
     *
     * @param title           The title of the book (partially matched). Can be {@code null}.
     * @param authorName      The name of the author (partially matched). Can be {@code null}.
     * @param publicationYear The publication year of the book. Can be {@code null}.
     * @param genre           The genre of the book (partially matched). Can be {@code null}.
     * @return A list of books matching the specified criteria.
     */
    @Query(value = "SELECT DISTINCT b.* " +
            "FROM books b " +
            "JOIN book_authors ba ON b.book_id = ba.book_id " +
            "JOIN authors a ON ba.author_id = a.author_id " +
            "WHERE (:title IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%'))) " +
            "AND (:authorName IS NULL OR LOWER(a.full_name) LIKE LOWER(CONCAT('%', :authorName, '%'))) " +
            "AND (:publicationYear IS NULL OR b.publication_year = :publicationYear) " +
            "AND (:genre IS NULL OR LOWER(b.genre) LIKE LOWER(CONCAT('%', :genre, '%')))",
            nativeQuery = true)
    List<Book> findBooksByJoinResult(@Param("title") String title,
                                     @Param("authorName") String authorName,
                                     @Param("publicationYear") Integer publicationYear,
                                     @Param("genre") String genre);
}
