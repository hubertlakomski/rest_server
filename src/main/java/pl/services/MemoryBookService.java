package pl.services;

import org.springframework.stereotype.Service;
import pl.exceptions.NotFoundException;
import pl.interfaces.BookService;
import pl.models.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class MemoryBookService implements BookService {

    private List<Book> books;

    public MemoryBookService() {
        this.books = new ArrayList<>();

        books.add(new Book(1,"9788324631766", "Thinking in Java", "Bruce Eckel",
                "Helion", "programming"));
        books.add(new Book(2,"9788324627738", "Rusz glowa, Java.",
                "Sierra Kathy, Bates Bert", "Helion", "programming"));
        books.add(new Book(3,"9780130819338", "Java 2. Podstawy",
                "Cay Horstmann, Gary Cornell", "Helion", "programming"));
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Book getBookById(long id){

        Optional<Book> matchingBook =
                books.stream()
                .filter(book -> book.getId() == id)
                .findFirst();

        return matchingBook.orElse(null);
    }


    public void createBook(Book addedBook){

        if(isBookIdExists(addedBook.getId())){
            throw new NotFoundException("given id exists");
        }

        addedBook.setId(new Random().nextInt(1000000+1));

        books.add(addedBook);
    }

    public void updateBook (Book book){

        if(!isBookIdExists(book.getId())){
            throw new NotFoundException("given id not exists");
        }

        books.remove(getBookById(book.getId()));

        books.add(book);
    }

    public void deleteBook (long id){

        if(!isBookIdExists(id)){
            throw new NotFoundException("given id not exists");
        }

        books.remove(getBookById(id));
    }

    public boolean isBookIdExists(long id){

        boolean isFound = false;

        for(Book book: books){

            if(book.getId()==id){
                isFound = true;
            }

        }

        return isFound;
    }
}
