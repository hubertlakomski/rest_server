package pl.interfaces;

import pl.models.Book;

import java.util.List;

public interface BookService {

    List<Book> getBooks();
    void setBooks(List<Book> books);

    void createBook(Book addedBook);
    Book getBookById(long id);
    void updateBook (Book book);
    void deleteBook (long id);

    boolean isBookIdExists(long id);
}
