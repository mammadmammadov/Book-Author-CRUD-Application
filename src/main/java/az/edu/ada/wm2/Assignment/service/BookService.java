/**
 * The BookService interface provides methods to perform CRUD operations and search for books.
 */
package az.edu.ada.wm2.Assignment.service;

import az.edu.ada.wm2.Assignment.model.Book;

import java.util.List;

/**
 * Service interface for managing books.
 */
public interface BookService {

    /**
     * Retrieves all books from the database.
     *
     * @return A list of all books.
     */
    List<Book> getAllBooks();

    /**
     * Adds a new book to the database.
     *
     * @param book The book to be added.
     */
    void addBook(Book book);

    /**
     * Updates an existing book in the database.
     *
     * @param id   The ID of the book to be updated.
     * @param book The updated book object.
     */
    void updateBook(Integer id, Book book);

    /**
     * Deletes a book from the database.
     *
     * @param id The ID of the book to be deleted.
     */
    void deleteBook(Integer id);

    /**
     * Retrieves a book by its ID from the database.
     *
     * @param id The ID of the book to retrieve.
     * @return The book object corresponding to the given ID, or null if not found.
     */
    Book getBookById(Integer id);

    /**
     * Searches for books based on the provided criteria.
     *
     * @param title           The title of the book (can be partial or full).
     * @param authorName      The name of the author (can be partial or full).
     * @param publicationYear The publication year of the book.
     * @param genre           The genre of the book (can be partial or full).
     * @return A list of books matching the search criteria.
     */
    List<Book> findBooksByJoinResult(String title, String authorName, Integer publicationYear, String genre);
}
