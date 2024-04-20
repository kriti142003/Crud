package com.crud.service;

import com.crud.DAO.BookDAO;
import com.crud.DAO.MySQLBookDAO;
import com.crud.model.Book;

import java.util.List;

public class BookService {
    private BookDAO bookDAO;

    public BookService() {
        bookDAO = new MySQLBookDAO();
    }

    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    public Book getBookById(int id) {
        return bookDAO.getBookById(id);
    }

    public void addBook(Book book) {
        bookDAO.addBook(book);
    }

    public void updateBook(Book book) {
        bookDAO.updateBook(book);
    }

    public void deleteBook(int id) {
        bookDAO.deleteBook(id);
    }
    public List<Integer> getAllBookIds() {
        return bookDAO.getAllBookIds();
    }

    public List<String> getAllBookTitles() {
        return bookDAO.getAllBookTitles();
    }

    public List<String> getAllBookAuthors() {
        return bookDAO.getAllBookAuthors();
    }
}

