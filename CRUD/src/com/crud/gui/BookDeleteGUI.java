package com.crud.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.crud.model.Book;
import com.crud.service.BookService;

public class BookDeleteGUI extends JFrame {
    private static final long serialVersionUID = -9008447361334818801L;
	private JTextField idField;
    private MyButton searchButton;
    private MyButton deleteButton;
    private MyButton cancelButton;
    private BookService bookService;

    public BookDeleteGUI(BookService bookService) {
        this.bookService = bookService;

        setTitle("Delete Book");
        setSize(600, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridLayout(2, 3, 10, 10));
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

        deleteButton = new MyButton("Delete Book");
        deleteButton.setFont(buttonFont);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBook();
            }
        });
        mainPanel.add(deleteButton);

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
                    JOptionPane.showMessageDialog(this, "Book found.\n\nID: " + book.getId() + "\nTitle: " + book.getTitle() + "\nAuthor: " + book.getAuthor());
                } else {
                    JOptionPane.showMessageDialog(this, "Book not found.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID. Please enter a valid integer.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter an ID to search.");
        }
    }

    private void deleteBook() {
        String idText = idField.getText().trim();
        if (!idText.isEmpty()) {
            try {
                int id = Integer.parseInt(idText);
                Book book = bookService.getBookById(id);
                if (book != null) {
                    int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this book?\n\nID: " + book.getId() + "\nTitle: " + book.getTitle() + "\nAuthor: " + book.getAuthor(), "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        bookService.deleteBook(id);
                        JOptionPane.showMessageDialog(this, "Book deleted successfully.");
                        idField.setText("");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Book not found.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID. Please enter a valid integer.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter an ID to delete.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            BookService bookService = new BookService();

            BookDeleteGUI deleteGUI = new BookDeleteGUI(bookService);
            deleteGUI.setVisible(true);
        });
    }
}
