package az.edu.ada.wm2.Assignment.service;

import az.edu.ada.wm2.Assignment.model.Author;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing authors.
 */
public interface AuthorService {

    /**
     * Retrieves all authors.
     *
     * @return a list of all authors
     */
    List<Author> getAllAuthors();

    /**
     * Retrieves an author by ID.
     *
     * @param id the ID of the author to retrieve
     * @return an Optional containing the author, or empty if not found
     */
    Optional<Author> getAuthorById(Integer id);

    /**
     * Creates a new author.
     *
     * @param author the author to create
     * @return the created author
     */
    Author createAuthor(Author author);

    /**
     * Updates an existing author.
     *
     * @param id     the ID of the author to update
     * @param author the updated author data
     */
    void updateAuthor(Integer id, Author author);

    /**
     * Deletes an author by ID.
     *
     * @param id the ID of the author to delete
     */
    void deleteAuthor(Integer id);

    /**
     * Searches for authors based on the provided criteria (in any combinations).
     *
     * @param fullName    the full name of the author (can be partial)
     * @param birthYear   the birth year of the author
     * @param nationality the nationality of the author
     * @return a list of authors matching the search criteria
     */
    List<Author> searchAuthors(String fullName, Integer birthYear, String nationality);

    /**
     * Retrieves all nationalities of authors.
     *
     * @return a list of all nationalities
     */
    List<String> getAllNationalities();
}
