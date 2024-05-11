package az.edu.ada.wm2.Assignment.repository;

import az.edu.ada.wm2.Assignment.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for performing CRUD operations and filtering on Author entities.
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    /**
     * Finds authors by their full name, birth year, and nationality, ignoring case.
     *
     * @param fullName    The full name of the author (partially matched), ignoring case.
     * @param birthYear   The birth year of the author.
     * @param nationality The nationality of the author.
     * @return List of authors matching the given criteria.
     */
    List<Author> findAuthorsByFullNameContainingIgnoreCaseAndBirthYearAndNationality(String fullName, Integer birthYear, String nationality);

    /**
     * Finds authors by their full name and birth year, ignoring case.
     *
     * @param fullName  The full name of the author (partially matched), ignoring case.
     * @param birthYear The birth year of the author.
     * @return List of authors matching the given criteria.
     */
    List<Author> findAuthorsByFullNameContainingIgnoreCaseAndBirthYear(String fullName, Integer birthYear);

    /**
     * Finds authors by their full name and nationality, ignoring case.
     *
     * @param fullName    The full name of the author (partially matched), ignoring case.
     * @param nationality The nationality of the author.
     * @return List of authors matching the given criteria.
     */
    List<Author> findAuthorsByFullNameContainingIgnoreCaseAndNationality(String fullName, String nationality);

    /**
     * Finds authors by their birth year and nationality.
     *
     * @param birthYear   The birth year of the author.
     * @param nationality The nationality of the author.
     * @return List of authors matching the given criteria.
     */
    List<Author> findAuthorsByBirthYearAndNationality(Integer birthYear, String nationality);

    /**
     * Finds authors by their full name, ignoring case.
     *
     * @param fullName The full name of the author (partially matched), ignoring case.
     * @return List of authors matching the given criteria.
     */
    List<Author> findAuthorsByFullNameContainingIgnoreCase(String fullName);

    /**
     * Finds authors by their birth year.
     *
     * @param birthYear The birth year of the author.
     * @return List of authors matching the given criteria.
     */
    @Query("SELECT a FROM Author a WHERE a.birthYear = :birthYear")
    List<Author> findAuthorsByBirthYear(@Param("birthYear") int birthYear);

    /**
     * Finds authors by their nationality.
     *
     * @param nationality The nationality of the author.
     * @return List of authors matching the given criteria.
     */
    @Query(value = "SELECT * FROM authors WHERE nationality = :nationality", nativeQuery = true)
    List<Author> findAuthorsByNationality(@Param("nationality") String nationality);

}
