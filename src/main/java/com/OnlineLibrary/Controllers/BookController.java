package com.OnlineLibrary.Controllers;

import com.OnlineLibrary.Entities.mongodb.Book;
import com.OnlineLibrary.Repository.mongodb.BookRepository;
import com.OnlineLibrary.Services.LibraryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookRepository bookRepository;
    private final LibraryService libraryService;

    public BookController(BookRepository bookRepository, LibraryService libraryService) {
        this.bookRepository = bookRepository;
        this.libraryService = libraryService;
    }
    @GetMapping
    public ResponseEntity<List<Book>> getBooksByCategory(
            @RequestParam Long categoryId
    ) {
        List<Book> books = bookRepository.findByCategoryId(categoryId);
        return ResponseEntity.ok(books);
    }
    @PostMapping("/{bookId}/order")
    public ResponseEntity<String> orderBook(
            @PathVariable String bookId,
            @RequestParam String userEmail
    ) {
        libraryService.checkoutBook(bookId, userEmail);
        return ResponseEntity.ok("Book ordered successfully");
    }
    @PostMapping("/{bookId}/return")
    public ResponseEntity<String> returnBook(
            @PathVariable String bookId
    ) {
        libraryService.returnBook(bookId);
        return ResponseEntity.ok("Book returned successfully");
    }
    @PostMapping("/{bookId}/request")
    public ResponseEntity<String> requestBook(
            @PathVariable String bookId,
            @RequestParam String requesterEmail,
            @RequestParam boolean withMeeting
    ) {
        libraryService.requestBook(bookId, requesterEmail, withMeeting);
        return ResponseEntity.ok("Request submitted successfully");
    }
}