package com.crud.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.crud.model.Book;
import com.crud.service.BookService;

public class BookUpdateGUI extends JFrame {

	private JTextField idField;
    private JTextField titleField;
    private JTextField authorField;
    private MyButton searchButton;
    private MyButton updateButton;
    private MyButton cancelButton;
    private BookService bookService;

    public BookUpdateGUI(BookService bookService) {
        this.bookService = bookService;

        setTitle("Update Book");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridLayout(5, 3, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setContentPane(mainPanel);

        Font labelFont = new Font("Arial", Font.PLAIN, 18);
        Font textFieldFont = new Font("Arial", Font.PLAIN, 18);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);

        JLabel idLabel = new JLabel("ID:");
        idLabel.setFont(labelFont);
        mainPanel.add(idLabel);

        idField = new JTextField();
        idField.setFont(textFieldFont);
        mainPanel.add(idField);

        searchButton = new MyButton("Search");
        searchButton.setFont(buttonFont);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchBook();
            }
        });
        mainPanel.add(new JLabel()); 
        mainPanel.add(searchButton);

        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setFont(labelFont);
        mainPanel.add(titleLabel);

        titleField = new JTextField();
        titleField.setFont(textFieldFont);
        mainPanel.add(titleField);

        JLabel authorLabel = new JLabel("Author:");
        authorLabel.setFont(labelFont);
        mainPanel.add(authorLabel);

        authorField = new JTextField();
        authorField.setFont(textFieldFont);
        mainPanel.add(authorField);

        updateButton = new MyButton("Update Book");
        updateButton.setFont(buttonFont);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBook(); 
            }
        });
        mainPanel.add(updateButton);

        cancelButton = new MyButton("Cancel");
        cancelButton.setFont(buttonFont);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        mainPanel.add(cancelButton);
    }

    private void searchBook() {
        String idText = idField.getText().trim();
        if (!idText.isEmpty()) {
            try {
                int id = Integer.parseInt(idText);
                Book book = bookService.getBookById(id);
                if (book != null) {
                    titleField.setText(book.getTitle());
                    authorField.setText(book.getAuthor());
                } else {
                    JOptionPane.showMessageDialog(this, "Book not found.");
                    clearFields();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID. Please enter a valid integer.");
                clearFields();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter an ID to search.");
            clearFields();
        }
    }

   /* private void updateBook() {
        String idText = idField.getText().trim();
        String title = titleField.getText().trim();
        String author = authorField.getText().trim();

        if (!idText.isEmpty() && !title.isEmpty() && !author.isEmpty()) {
            try {
                int id = Integer.parseInt(idText);
                Book book = bookService.getBookById(id);

                if (book != null) {
                	if (isValidTitle(title) || isValidAuthor(author)) { 
                        book.setTitle(title);
                        book.setAuthor(author);
                        bookService.updateBook(book);

                        JOptionPane.showMessageDialog(this, "Book updated successfully.");
                        clearFields();
                    } else {
                        JOptionPane.showMessageDialog(this, "Title or author is not valid or already exists.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Book not found.");
                    clearFields();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID. Please enter a valid integer.");
                clearFields();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter both title and author.");
        }
    }*/
    private void updateBook() {
        String idText = idField.getText().trim();
        String title = titleField.getText().trim();
        String author = authorField.getText().trim();

        if (!idText.isEmpty() && !title.isEmpty() && !author.isEmpty()) {
            try {
                int id = Integer.parseInt(idText);
                Book book = bookService.getBookById(id);

                if (book != null) {
                    if (isValidTitle(title) && isValidAuthor(author)) {
                        book.setTitle(title);
                        book.setAuthor(author);
                        bookService.updateBook(book); 

                        JOptionPane.showMessageDialog(this, "Book updated successfully.");
                        clearFields();
                    } else {
                        JOptionPane.showMessageDialog(this, "Title or author is not valid or already exists.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Book not found.");
                    clearFields();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID. Please enter a valid integer.");
                clearFields();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter both title and author.");
        }
    }

    private boolean isValidTitle(String title) {
        ArrayList<String> existingTitles = (ArrayList<String>) bookService.getAllBookTitles();
        return !title.isEmpty() && !existingTitles.contains(title) && title.matches("^[a-zA-Z.\\s]+$") && title.length()>=4; 
    }
    private boolean isValidAuthor(String author) {
        ArrayList<String> existingAuthors = (ArrayList<String>) bookService.getAllBookAuthors();
        return !author.isEmpty() && !existingAuthors.contains(author) && author.matches("^[a-zA-Z.\\s]+$") && author.length()>=4;  
    }

    private void clearFields() {
        idField.setText("");
        titleField.setText("");
        authorField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                BookService bookService = new BookService();

                BookUpdateGUI updateGUI = new BookUpdateGUI(bookService);
                updateGUI.setVisible(true);
            }
        });
    }
}





