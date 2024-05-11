package az.edu.ada.wm2.Assignment.controller;

import az.edu.ada.wm2.Assignment.model.Author;
import az.edu.ada.wm2.Assignment.model.Book;
import az.edu.ada.wm2.Assignment.service.AuthorService;
import az.edu.ada.wm2.Assignment.service.BookService;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling book-related requests.
 */
@Data
@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    /**
     * Constructor injection for BookService and AuthorService dependencies.
     *
     * @param bookService   BookService instance to be injected
     * @param authorService AuthorService instance to be injected
     */
    @Autowired
    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    /**
     * Displays a list of books.
     *
     * @param model Model object to add attributes for rendering the view
     * @return list-books.html view for displaying the list of books
     */
    @GetMapping({"", "/"})
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "list-books";
    }

    /**
     * Displays the form for adding a new book.
     *
     * @param model Model object to add attributes for rendering the form
     * @return add-book.html view for displaying the add book form
     */
    @GetMapping("/add")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.getAllAuthors());
        return "add-book";
    }

    /**
     * Handles the submission of the add book form.
     *
     * @param book   Book object containing form data entered by user
     * @param result BindingResult object used for detecting validation errors
     * @param model  Model object to add attributes for rendering the view
     * @return Redirect URL to display the list of books or stuck with the form unless fills-in the form appropriately
     */
    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute("book") Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("authors", authorService.getAllAuthors());
            return "add-book";
        }
        bookService.addBook(book);
        return "redirect:/books";
    }

    /**
     * Displays the form for updating an existing book.
     *
     * @param id    ID of the book that was selected by user to be updated
     * @param model Model object to add attributes for rendering the form
     * @return update-book.html view for displaying the update book form
     */
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Book book = bookService.getBookById(id);
        List<Author> allAuthors = authorService.getAllAuthors();
        model.addAttribute("book", book);
        model.addAttribute("allAuthors", allAuthors);
        return "update-book";
    }

    /**
     * Handles the submission of the update book form.
     *
     * @param id   ID of the book to be updated
     * @param book Updated Book object containing form data
     * @return Redirect URL to display the list of books
     */
    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable("id") Integer id, @ModelAttribute("book") Book book) {
        bookService.updateBook(id, book);
        return "redirect:/books";
    }

    /**
     * Deletes a book.
     *
     * @param id ID of the book to be deleted
     * @return Redirect URL to display the list of books
     */
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Integer id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    /**
     * Searches for books based on given criteria.
     *
     * @param title           Title of the book to search for entered by user
     * @param authorName      Name of the author to search for entered by user
     * @param publicationYear Publication year of the book to search for entered by user
     * @param genre           Genre of the book to search for entered by user
     * @param model           Model object to add attributes for rendering the view
     * @return list-books.html view for displaying the list of books
     */
    @GetMapping("/search")
    public String searchBooks(@RequestParam(name = "title", required = false) String title,
                              @RequestParam(name = "author", required = false) String authorName,
                              @RequestParam(name = "publicationYear", required = false) Integer publicationYear,
                              @RequestParam(name = "genre", required = false) String genre,
                              Model model) {
        List<Book> books = bookService.findBooksByJoinResult(title, authorName,
                publicationYear, genre);
        model.addAttribute("books", books);
        return "list-books";
    }
}
