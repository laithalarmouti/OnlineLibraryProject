package com.OnlineLibrary.ErrHandling;

public class BookAlreadyCheckedOutException extends RuntimeException {
    public BookAlreadyCheckedOutException(String bookId) {
        super("Book already checked out: " + bookId);
    }
}