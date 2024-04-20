package com.crud.DAO;

import com.crud.model.Book;
import java.util.List;

public interface BookDAO {
    List<Book> getAllBooks();
    Book getBookById(int id);
    void addBook(Book book);
    void updateBook(Book book);
    void deleteBook(int id);
    List<Integer> getAllBookIds();
    List<String> getAllBookTitles();
    List<String> getAllBookAuthors();
}

