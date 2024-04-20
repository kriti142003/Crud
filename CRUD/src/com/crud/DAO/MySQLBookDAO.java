package com.crud.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.crud.model.Book;

public class MySQLBookDAO implements BookDAO {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/crud";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Krits@14";

    public MySQLBookDAO() {
        createTableIfNotExists();
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
    }

    private void createTableIfNotExists() {
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(
                     "CREATE TABLE IF NOT EXISTS books (" +
                             "id INT AUTO_INCREMENT PRIMARY KEY," +
                             "title VARCHAR(255)," +
                             "author VARCHAR(255))"
             )) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM books");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public Book getBookById(int id) {
        Book book = null;
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM books WHERE id = ?")) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    book = new Book();
                    book.setId(rs.getInt("id"));
                    book.setTitle(rs.getString("title"));
                    book.setAuthor(rs.getString("author"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public void addBook(Book book) {
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement("INSERT INTO books (title, author) VALUES (?, ?)")) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   /* @Override
    public void updateBook(Book book) {
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement("UPDATE books SET title = ?, author = ? WHERE id = ?")) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setInt(3, book.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
    @Override
    public void updateBook(Book book) {
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement("UPDATE books SET title = Das, author = Karl Max WHERE id = 4")) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setInt(3, book.getId());
            stmt.executeUpdate();

            // Explicitly commit the transaction
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBook(int id) {
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement("DELETE FROM books WHERE id = ?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Integer> getAllBookIds() {
        List<Integer> ids = new ArrayList<>();
        String query = "SELECT DISTINCT id FROM books";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                ids.add(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ids;
    }

	@Override
	public List<String> getAllBookTitles() {
		List<String> titles = new ArrayList<>();
	    String query = "SELECT DISTINCT title FROM books";

	    try (Connection connection = getConnection();
	         PreparedStatement statement = connection.prepareStatement(query);
	         ResultSet resultSet = statement.executeQuery()) {

	        while (resultSet.next()) {
	            String title = resultSet.getString("title");
	            titles.add(title);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return titles;
	}

	public List<String> getAllBookAuthors() {
	    List<String> authors = new ArrayList<>();
	    String query = "SELECT DISTINCT author FROM books";

	    try (Connection connection = getConnection();
	         PreparedStatement statement = connection.prepareStatement(query);
	         ResultSet resultSet = statement.executeQuery()) {

	        while (resultSet.next()) {
	            String author = resultSet.getString("author");
	            authors.add(author);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return authors;
	}

}

