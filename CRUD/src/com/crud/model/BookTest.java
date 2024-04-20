package com.crud.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BookTest {

    @Test
    public void testBookGettersAndSetters() {
        Book book = new Book();

        book.setId(1);
        book.setTitle("Sample Book");
        book.setAuthor("Kriti Misra");

        assertEquals(1, book.getId());
        assertEquals("Sample Book", book.getTitle());
        assertEquals("Kriti Misra", book.getAuthor());
    }

    @Test
    public void testBookToString() {
        Book book = new Book(1, "Sample Book", "Kriti Misra");

        assertEquals("Book{id=1, title='Sample Book', author='Kriti Misra'}", book.toString());
    }
}
