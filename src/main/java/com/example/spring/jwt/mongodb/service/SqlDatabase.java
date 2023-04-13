package com.example.spring.jwt.mongodb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.BookDesignFactoryMethod.database.Database;
import com.example.BookDesignFactoryMethod.entity.Book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class SqlDatabase implements Database {
    private Connection conn;

    public SqlDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BookLibrary", "inferyx", "20Inferyx!9");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> getBook() {
        List<Book> books = new ArrayList<>();
        try {
            String sql = "SELECT * FROM book";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getString("id"));
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
    public Optional<Book> getBookById(String id) {
        try {
            String sql = "SELECT * FROM book WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Book book = new Book();
                book.setId(rs.getString("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                return Optional.of(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public String addBook(Book book) {
        try {
            String sql = "INSERT INTO book (id, title, author) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, book.getId());
            stmt.setString(2, book.getTitle());
            stmt.setString(3, book.getAuthor());
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                return "Book added successfully";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Failed to add book";
    }

    @Override
    public String UpdateBook(Book book) {
        try {
            String sql = "UPDATE book SET title = ?, author = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getId());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                return "Book updated successfully";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Failed to update book";
    }

    @Override
    public String deleteBook(String id) {
        try {
            String sql = "DELETE FROM book WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                return "Book deleted successfully";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Failed to delete book";
    }
}













/*public class SqlDatabase implements Database {
	@Autowired
    private JdbcTemplate jdbcTemplate;

	 @Override
    public List<Book> getBook() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";
        jdbcTemplate.query(query, (resultSet, i) -> {
            Book book = new Book(resultSet.getString("id"),
                    resultSet.getString("title"),
                    resultSet.getString("author"));
            books.add(book);
            return book;
        });
        return books;
    }

   //@SuppressWarnings("deprecation")
	@Override
    public Optional<Book> getBookById(String id) {
        String query = "SELECT * FROM books WHERE id = ?";
        Book book = jdbcTemplate.queryForObject(query, new Object[]{id}, (resultSet, i) -> {
            return new Book(resultSet.getString("id"),
                    resultSet.getString("title"),
                    resultSet.getString("author"));
        });
        return Optional.ofNullable(book);
    }

    @Override
    public String addBook(Book book) {
        String query = "INSERT INTO books (id, title, author) VALUES (?, ?, ?)";
        int result = jdbcTemplate.update(query, book.getId(), book.getTitle(), book.getAuthor());
        return result == 1 ? "Book added successfully" : "Failed to add book";
    }

    @Override
    public String UpdateBook(Book book) {
        String query = "UPDATE books SET title = ?, author = ?WHERE id = ?";
        int result = jdbcTemplate.update(query, book.getTitle(), book.getAuthor(), book.getId());
        return result == 1 ? "Book updated successfully" : "Failed to update book";
    }

    @Override
    public String deleteBook(String id) {
        String query = "DELETE FROM books WHERE id = ?";
        int result = jdbcTemplate.update(query, id);
        return result == 1 ? "Book deleted successfully" : "Failed to delete book";
    }
}*/
