package az.edu.ada.wm2.Assignment.data;

import az.edu.ada.wm2.Assignment.model.Author;
import az.edu.ada.wm2.Assignment.model.Book;
import az.edu.ada.wm2.Assignment.repository.AuthorRepository;
import az.edu.ada.wm2.Assignment.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * InitialDataLoader is a component responsible for loading initial data into the application database upon startup. It implements the CommandLineRunner interface to execute the data loading logic when the application starts.
 *
 */
@Component
@RequiredArgsConstructor
public class InitialDataLoader implements CommandLineRunner {

    @Autowired
    private AuthorRepository authorRepo;

    @Autowired
    private BookRepository bookRepo;

    /**
     * This method is called when the application starts. It loads initial data about authors and books into the database.
     * It saves authors and books into their respective repositories.
     *
     * @param args Command line arguments passed to the application.
     * @throws Exception If there is any error during data loading.
     */
    @Override
    public void run(String... args) throws Exception {

        Author author1 = new Author("F. Scott Fitzgerald", 1896, "American");
        Author author2 = new Author("Harper Lee", 1926, "American");
        Author author3 = new Author("J.K. Rowling", 1965, "British");
        Author author4 = new Author("J.R.R. Tolkien", 1892, "British");
        Author author5 = new Author("Orhan Pamuk", 1952, "Turkish");
        Author author6 = new Author("Sabahattin Ali", 1907, "Turkish");


        Book book1 = new Book("The Great Gatsby", List.of(author1), 1925, "Classic Fiction");
        Book book2 = new Book("To Kill a Mockingbird", List.of(author2), 1960, "Classic Literature");
        Book book3 = new Book("Harry Potter and the Philosopher's Stone", List.of(author3), 1997, "Fantasy");
        Book book4 = new Book("The Hobbit", List.of(author4), 1937, "Fantasy");
        Book book5 = new Book("My Name is Red", List.of(author5, author6), 1998, "Historical Fiction");
        Book book6 = new Book("Madonna in a Fur Coat", List.of(author6), 1943, "Novel");

        authorRepo.saveAll(List.of(author1, author2, author3, author4, author5, author6));
        bookRepo.saveAll(List.of(book1, book2, book3, book4, book5, book6));
    }
}
