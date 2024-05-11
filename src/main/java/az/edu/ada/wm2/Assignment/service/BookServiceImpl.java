/**
 * The BookServiceImpl class implements the BookService interface and provides methods to perform CRUD operations and search for books.
 */
package az.edu.ada.wm2.Assignment.service;

import az.edu.ada.wm2.Assignment.model.Book;
import az.edu.ada.wm2.Assignment.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for managing books. This service provides methods to perform CRUD operations on books,as well as searching for books based on various criteria.
 */
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    /**
     * Constructs a new instance of BookServiceImpl with the specified BookRepository.
     *
     * @param bookRepository The repository for managing book data.
     */
    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Retrieves all books from the database.
     *
     * @return A list of all books.
     */
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Adds a new book to the database.
     *
     * @param book The book to be added.
     */
    @Override
    public void addBook(Book book) {
        bookRepository.save(book);
    }

    /**
     * Updates an existing book in the database.
     *
     * @param id   The ID of the book to be updated.
     * @param book The updated book object.
     */
    @Override
    public void updateBook(Integer id, Book book) {
        book.setId(id);
        bookRepository.save(book);
    }

    /**
     * Deletes a book from the database.
     *
     * @param id The ID of the book to be deleted.
     */
    @Override
    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }

    /**
     * Retrieves a book by its ID from the database.
     *
     * @param id The ID of the book to retrieve.
     * @return The book object corresponding to the given ID, or throws an IllegalArgumentException if not found.
     */
    @Override
    public Book getBookById(Integer id) {
        return bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
    }

    /**
     * Searches for books based on the provided criteria.
     *
     * @param title           The title of the book (can be partial or full).
     * @param authorName      The name of the author (can be partial or full).
     * @param publicationYear The publication year of the book.
     * @param genre           The genre of the book (can be partial or full).
     * @return A list of books matching the search criteria.
     */
    @Override
    public List<Book> findBooksByJoinResult(String title, String authorName, Integer publicationYear, String genre) {
        return bookRepository.findBooksByJoinResult(title, authorName, publicationYear, genre);
    }
}
