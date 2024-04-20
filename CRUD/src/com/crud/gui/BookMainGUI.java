package com.crud.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.crud.service.BookService;

public class BookMainGUI extends JFrame {
    private static final long serialVersionUID = -5447522693896084948L;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton readButton;
    private BookService bookService;

    public BookMainGUI(BookService bookService) {
        this.bookService = bookService;

        setTitle("CRUD Book Management");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridLayout(0, 1, 0, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setContentPane(mainPanel);

        Font buttonFont = new Font("Arial", Font.PLAIN, 26);
        Color backgroundColor = new Color(240, 240, 240); 
        Color buttonColor = new Color(0, 150, 136); 
        Color textColor = Color.BLACK;

        mainPanel.setBackground(backgroundColor);

        String imagePath1 = "/Users/kritimisra/Desktop/image/addbook.jpeg";
        try {
            BufferedImage originalImage = ImageIO.read(new File(imagePath1));
            int iconWidth = 32; 
            int iconHeight = 32;
            Image scaledImage = originalImage.getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
            addButton = new JButton("Add Book", new ImageIcon(scaledImage));
            addButton.setFont(buttonFont); 
            addButton.setBackground(buttonColor);
            addButton.setForeground(textColor);
            addButton.addActionListener(event -> openAddGUI());
            mainPanel.add(addButton);
        } catch (IOException e) {
            e.printStackTrace();
            addButton = new MyButton("Add Book"); 
            addButton.setFont(UIManager.getFont("Button.font")); 
            addButton.addActionListener(event -> openAddGUI());
            mainPanel.add(addButton);
        }
        String imagePath2 = "/Users/kritimisra/Desktop/image/updatebook.jpeg";
        try {
            BufferedImage originalImage = ImageIO.read(new File(imagePath2));
            int iconWidth = 32; 
            int iconHeight = 32;
            Image scaledImage = originalImage.getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
            updateButton = new JButton("Update Book", new ImageIcon(scaledImage));
            updateButton.setFont(buttonFont); 
            updateButton.setBackground(buttonColor);
            updateButton.setForeground(textColor);
            updateButton.addActionListener(event -> openUpdateGUI());
            mainPanel.add(updateButton);
        } catch (IOException e) {
            e.printStackTrace();
            updateButton = new MyButton("Update Book"); 
            updateButton.setFont(UIManager.getFont("Button.font")); 
            updateButton.addActionListener(event -> openUpdateGUI());
            mainPanel.add(updateButton);
        }
        String imagePath3 = "/Users/kritimisra/Desktop/image/deletebook.jpeg";
        try {
            BufferedImage originalImage = ImageIO.read(new File(imagePath3));
            int iconWidth = 32; 
            int iconHeight = 32; 
            Image scaledImage = originalImage.getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
            deleteButton = new JButton("Delete Book", new ImageIcon(scaledImage));
            deleteButton.setFont(buttonFont); 
            deleteButton.setBackground(buttonColor);
            deleteButton.setForeground(textColor);
            deleteButton.addActionListener(event -> openDeleteGUI());
            mainPanel.add(deleteButton);
        } catch (IOException e) {
            e.printStackTrace();
            deleteButton = new MyButton("Delete Book"); 
            deleteButton.setFont(UIManager.getFont("Button.font"));
            deleteButton.addActionListener(event -> openDeleteGUI());
            mainPanel.add(deleteButton);
        }
        String imagePath4 = "/Users/kritimisra/Desktop/image/readbook.jpeg";
        try {
            BufferedImage originalImage = ImageIO.read(new File(imagePath4));
            int iconWidth = 36; 
            int iconHeight = 36; 
            Image scaledImage = originalImage.getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
            readButton = new JButton("Read Book", new ImageIcon(scaledImage));
            readButton.setFont(buttonFont); 
            readButton.setBackground(buttonColor);
            readButton.setForeground(textColor);
            readButton.addActionListener(event -> openReadGUI());
            mainPanel.add(readButton);
        } catch (IOException e) {
            e.printStackTrace();
            readButton = new MyButton("Read Book"); 
            readButton.setFont(UIManager.getFont("Button.font")); 
            readButton.addActionListener(event -> openReadGUI());
            mainPanel.add(readButton);
        }
        
    }

    private void openAddGUI() {
        BookAddGUI addGUI = new BookAddGUI(bookService);
        addGUI.setVisible(true);
    }

    private void openUpdateGUI() {
        BookUpdateGUI updateGUI = new BookUpdateGUI(bookService);
        updateGUI.setVisible(true);
    }

    private void openDeleteGUI() {
        BookDeleteGUI deleteGUI = new BookDeleteGUI(bookService);
        deleteGUI.setVisible(true);
    }

    private void openReadGUI() {
        BookReadGUI readGUI = new BookReadGUI(bookService);
        readGUI.setVisible(true);
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

                BookMainGUI mainGUI = new BookMainGUI(bookService);
                mainGUI.setVisible(true);
            }
        });
    }
}




