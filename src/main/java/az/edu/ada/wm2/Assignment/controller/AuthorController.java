package az.edu.ada.wm2.Assignment.controller;

import az.edu.ada.wm2.Assignment.model.Author;
import az.edu.ada.wm2.Assignment.service.AuthorService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller class for handling author-related requests.
 */
@Data
@Controller
@RequestMapping("/authors")
public class AuthorController {


    private final AuthorService authorService;

    /**
     * Constructor injection for AuthorService dependency.
     *
     * @param authorService AuthorService instance to be injected
     */
    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    /**
     * Displays a list of authors.
     *
     * @param model Model object to add attributes for rendering the view
     * @return list-authors.html view for displaying the list of authors
     */
    @GetMapping({"", "/"})
    public String listAuthors(Model model) {
        List<Author> authors = authorService.getAllAuthors();
        model.addAttribute("authors", authors);
        model.addAttribute("nationalities", authorService.getAllNationalities());
        return "list-authors";
    }

    /**
     * Displays the form for adding a new author.
     *
     * @param model Model object to add attributes for rendering the form
     * @return add-author.html view for displaying the add author form
     */
    @GetMapping("/add")
    public String showAddAuthorForm(Model model) {
        model.addAttribute("author", new Author());
        return "add-author";
    }

    /**
     * Handles the submission of the add author form.
     *
     * @param author Author object containing form data
     * @return Redirect URL to display the list of authors
     */
    @PostMapping("/add")
    public String addAuthor(@ModelAttribute("author") Author author) {
        authorService.createAuthor(author);
        return "redirect:/authors";
    }

    /**
     * Displays the form for editing an existing author.
     *
     * @param id    ID of the author to be edited
     * @param model Model object to add attributes for rendering the form
     * @return update-author.html view for displaying the update author form or error.html page if author not found
     */
    @GetMapping("/update/{id}")
    public String showEditAuthorForm(@PathVariable("id") Integer id, Model model) {
        Optional<Author> authorOptional = authorService.getAuthorById(id);
        if (authorOptional.isPresent()) {
            model.addAttribute("author", authorOptional.get());
            return "update-author";
        } else {
            return "error";
        }
    }

    /**
     * Handles the submission of the update author form.
     *
     * @param id            ID of the author to be updated
     * @param updatedAuthor Updated Author object containing form data
     * @return Redirect URL to display the list of authors
     */
    @PostMapping("/update/{id}")
    public String updateAuthor(@PathVariable("id") Integer id, @ModelAttribute("author") Author updatedAuthor) {
        authorService.updateAuthor(id, updatedAuthor);
        return "redirect:/authors";
    }

    /**
     * Deletes an author.
     *
     * @param id ID of the author to be deleted
     * @return Redirect URL to display the list of authors
     */
    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable("id") Integer id) {
        authorService.deleteAuthor(id);
        return "redirect:/authors";
    }

    /**
     * Searches for authors based on given criteria.
     *
     * @param fullName   Full name of the author to search for entered by user
     * @param birthYear  Birth year of the author to search for entered by user
     * @param nationality Nationality of the author to search for selected by user
     * @param model      Model object to add attributes for rendering the view
     * @return list-authors.html view for displaying the list of authors
     */
    @GetMapping("/search")
    public String searchAuthors(@RequestParam(name = "fullName", required = false) String fullName,
                                @RequestParam(name = "birthYear", required = false) Integer birthYear,
                                @RequestParam(name = "nationality", required = false) String nationality,
                                Model model) {
        List<Author> authors = authorService.searchAuthors(fullName, birthYear, nationality);
        model.addAttribute("authors", authors);
        model.addAttribute("nationalities", authorService.getAllNationalities());
        return "list-authors";
    }
}
