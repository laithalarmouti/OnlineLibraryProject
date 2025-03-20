package com.OnlineLibrary.ErrHandling;

public class BookAvailableException extends RuntimeException {
    public BookAvailableException(String bookId) {
        super("Book is available: " + bookId + " - direct checkout instead");
    }
}