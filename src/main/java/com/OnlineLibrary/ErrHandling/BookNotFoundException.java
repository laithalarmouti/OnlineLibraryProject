package com.OnlineLibrary.ErrHandling;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String bookId) {
        super("Book not found: " + bookId);
    }
}