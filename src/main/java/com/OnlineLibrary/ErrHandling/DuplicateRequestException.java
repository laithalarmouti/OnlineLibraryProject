package com.OnlineLibrary.ErrHandling;

public class DuplicateRequestException extends RuntimeException {
    public DuplicateRequestException(String bookId, String email) {
        super("Duplicate request for book " + bookId + " by " + email);
    }
}