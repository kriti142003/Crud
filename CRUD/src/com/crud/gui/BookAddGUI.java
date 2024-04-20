package com.crud.gui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.crud.model.Book;
import com.crud.service.BookService;

public class BookAddGUI extends JFrame {
    private static final long serialVersionUID = -417494294136926508L;
	private JTextField idField;
    private JTextField titleField;
    private JTextField authorField;
    private MyButton addButton;
    private MyButton cancelButton;
    private BookService bookService;

    public BookAddGUI(BookService bookService) {
        this.bookService = bookService;

        setTitle("Add New Book");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setContentPane(mainPanel);

        Font labelFont = new Font("Arial", Font.PLAIN, 18);
        Font textFieldFont = new Font("Arial", Font.PLAIN, 18);

        JLabel idLabel = new JLabel("ID:");
        idLabel.setFont(labelFont);
        mainPanel.add(idLabel);
        idField = new JTextField();
        idField.setFont(textFieldFont);
        mainPanel.add(idField);

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

        addButton = new MyButton("Add Book");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });
        mainPanel.add(addButton);

        cancelButton = new MyButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        mainPanel.add(cancelButton);
    }

    private void addBook() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            String title = titleField.getText().trim();
            String author = authorField.getText().trim();

            if (!isValidId(id)) {
                JOptionPane.showMessageDialog(this, "Either the id already exists or invalid input.");
                return;
            }

            if (!isValidTitle(title) || !isValidAuthor(author)) {
                JOptionPane.showMessageDialog(this, "Please enter a valid title and Author name.");
                return;
            }

            Book book = new Book();
            book.setId(id);
            book.setTitle(title);
            book.setAuthor(author);

            bookService.addBook(book);

            JOptionPane.showMessageDialog(this, "Book added successfully.");
            clearFields(); 

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid ID format. Please enter a valid integer ID.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An error occurred while adding the book. Please try again later.");
            e.printStackTrace();
        }
    }
    private boolean isValidId(int id) {
        List<Integer> existingIds = bookService.getAllBookIds();
        return id > 0 && !existingIds.contains(id); 
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

                BookAddGUI addGUI = new BookAddGUI(bookService);
                addGUI.setVisible(true);
            }
        });
    }
}



