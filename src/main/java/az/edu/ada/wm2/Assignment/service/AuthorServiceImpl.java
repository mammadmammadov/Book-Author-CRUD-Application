package az.edu.ada.wm2.Assignment.service;

import az.edu.ada.wm2.Assignment.model.Author;
import az.edu.ada.wm2.Assignment.model.Book;
import az.edu.ada.wm2.Assignment.repository.AuthorRepository;
import az.edu.ada.wm2.Assignment.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service implementation for managing authors. This service provides methods to perform CRUD operations on authors,as well as searching for authors based on various criteria.
 */
@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    private List<String> allNationalities;


    /**
     * Constructs an instance of AuthorServiceImpl. Initializes the author and book repositories and fetches all nationalities from the database.
     *
     * @param authorRepository the author repository
     * @param bookRepository   the book repository
     */
    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.allNationalities = getAllNationalities();
    }

    /**
     * Gets all unique nationalities from the authors stored in the database. If no nationalities are found, returns an empty list.
     *
     * @return a list of all unique nationalities
     */
    public List<String> getAllNationalities() {
        List<String> nationalities = authorRepository.findAll().stream().map(Author::getNationality).distinct().collect(Collectors.toList());
        return nationalities.isEmpty() ? Collections.emptyList() : nationalities;
    }


    /**
     * Retrieves all authors stored in the database.
     *
     * @return a list of all authors
     */
    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    /**
     * Retrieves an author by their ID.
     *
     * @param id the ID of the author to retrieve
     * @return an optional containing the author if found, otherwise empty
     */
    @Override
    public Optional<Author> getAuthorById(Integer id) {
        return authorRepository.findById(id);
    }

    /**
     * Creates a new author.
     *
     * @param author the author to create
     * @return the created author
     */
    @Override
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    /**
     * Updates an existing author.
     *
     * @param id     the ID of the author to update
     * @param author the updated author object
     */
    @Override
    public void updateAuthor(Integer id, Author author) {
        author.setId(id);
        authorRepository.save(author);
    }

    /**
     * Deletes an author by their ID. Also removes associations with any books authored by the deleted author. If book has no author in the database, it should also get deleted.
     *
     * @param id the ID of the author to delete
     * @throws IllegalArgumentException if the author with the specified ID is not found
     */
    @Override
    public void deleteAuthor(Integer id) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();

            Iterator<Book> iterator = author.getBooks().iterator();
            while (iterator.hasNext()) {
                Book book = iterator.next();
                if (book.getAuthors().size() == 1 && book.getAuthors().contains(author)) {
                    iterator.remove();
                    bookRepository.delete(book);
                } else {
                    book.getAuthors().remove(author);
                }
            }

            authorRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Author with ID " + id + " not found.");
        }
    }

    /**
     * Searches for authors based on the provided criteria.
     * If no criteria are provided, returns all authors.
     *
     * @param fullName    the full name of the author (can be partial)
     * @param birthYear   the birth year of the author
     * @param nationality the nationality of the author
     * @return a list of authors matching the search criteria
     */
    public List<Author> searchAuthors(String fullName, Integer birthYear, String nationality) {
        if (isNullOrBlank(fullName) && isNullOrBlank(nationality) && birthYear == null) {
            return authorRepository.findAll();
        }

        if (!isNullOrBlank(fullName) && birthYear != null && !isNullOrBlank(nationality)) {
            return authorRepository.findAuthorsByFullNameContainingIgnoreCaseAndBirthYearAndNationality(fullName, birthYear, nationality);
        } else if (!isNullOrBlank(fullName) && birthYear != null) {
            return authorRepository.findAuthorsByFullNameContainingIgnoreCaseAndBirthYear(fullName, birthYear);
        } else if (!isNullOrBlank(fullName) && !isNullOrBlank(nationality)) {
            return authorRepository.findAuthorsByFullNameContainingIgnoreCaseAndNationality(fullName, nationality);
        } else if (birthYear != null && !isNullOrBlank(nationality)) {
            return authorRepository.findAuthorsByBirthYearAndNationality(birthYear, nationality);
        } else if (!isNullOrBlank(fullName)) {
            return authorRepository.findAuthorsByFullNameContainingIgnoreCase(fullName);
        } else if (birthYear != null) {
            return authorRepository.findAuthorsByBirthYear(birthYear);
        } else if (!isNullOrBlank(nationality)) {
            return authorRepository.findAuthorsByNationality(nationality);
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * Checks if a string is null or blank.
     *
     * @param str the string to check
     * @return true if the string is null or blank, otherwise false
     */
    private boolean isNullOrBlank(String str) {
        return str == null || str.trim().isEmpty();
    }
}
