package com.crud.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import com.crud.model.Book;
import com.crud.service.BookService;

public class BookReadGUI extends JFrame {
    private static final long serialVersionUID = 3360871161990229227L;
	private BookService bookService;
    private JTable bookTable;
    private DefaultTableModel tableModel;

    public BookReadGUI(BookService bookService) {
        this.bookService = bookService;

        setTitle("Read Books");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(new Color(173, 216, 230));
        setContentPane(mainPanel);

        String[] columnNames = {"ID", "Title", "Author"};
        tableModel = new DefaultTableModel(columnNames, 0);
        bookTable = new JTable(tableModel);
        bookTable.setFont(new Font("Arial", Font.PLAIN, 18));
        bookTable.setRowHeight(30);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        bookTable.setDefaultRenderer(Object.class, centerRenderer);

        JScrollPane scrollPane = new JScrollPane(bookTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        updateBookTable();

        MyButton closeButton = new MyButton("Close");
        closeButton.setFont(new Font("Arial", Font.PLAIN, 18));
        closeButton.addActionListener(e -> dispose());

        mainPanel.add(closeButton, BorderLayout.SOUTH);
    }

    private void updateBookTable() {
        List<Book> books = bookService.getAllBooks();
        tableModel.setRowCount(0);
        for (Book book : books) {
            Object[] rowData = {book.getId(), book.getTitle(), book.getAuthor()};
            tableModel.addRow(rowData);
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

            BookReadGUI readGUI = new BookReadGUI(bookService);
            readGUI.setVisible(true);
        });
    }
}


