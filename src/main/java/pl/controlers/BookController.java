package pl.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.interfaces.BookService;
import pl.models.Book;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("")
    public List<Book> bookList(){

        return bookService.getBooks();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable long id){

        return bookService.getBookById(id);
    }

    @PostMapping("")
    public void createBook(@RequestBody Book book){

        System.out.println(book);

        bookService.createBook(book);
    }

    @PutMapping("/{id}")
    public void updateBook(
            @PathVariable long id,
            @RequestBody Book book){

        book.setId(id);

        bookService.updateBook(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id){

        bookService.deleteBook(id);
    }
}