package com.OnlineLibrary.Services;


import com.OnlineLibrary.Entities.mongodb.Book;
import com.OnlineLibrary.Entities.mongodb.Book.Requester;
import com.OnlineLibrary.Entities.sql.User;
import com.OnlineLibrary.Entities.sql.UserActivity;
import com.OnlineLibrary.ErrHandling.*;
import com.OnlineLibrary.Repository.mongodb.BookRepository;
import com.OnlineLibrary.Repository.sql.UserActivityRepository;
import com.OnlineLibrary.Repository.sql.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;


@Service
public class LibraryService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final UserActivityRepository userActivityRepository;

    public LibraryService(BookRepository bookRepository,
                          UserRepository userRepository,
                          EmailService emailService,
                          UserActivityRepository userActivityRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.userActivityRepository = userActivityRepository;
    }


public void checkoutBook(String bookId, String userEmail) {
    Book book = bookRepository.findById(bookId)
            .orElseThrow(() -> new BookNotFoundException(bookId));
    User user = userRepository.findByEmail(userEmail)
            .orElseThrow(() -> new UserNotFoundException(userEmail));
    if (!book.isAvailable()) {
        throw new BookAlreadyCheckedOutException(bookId); //err handle if book is already taken by other user
    }
    book.setAvailable(false);
    book.setCurrentHolder(userEmail);
    book.setDueDate(Date.from(Instant.now().plusSeconds(1209600))); // sample return due date, 2 weeks
    bookRepository.save(book);

    //Activity log,
    UserActivity activity = new UserActivity();
    activity.setUser(user);
    activity.setBookId(bookId);
    activity.setAction(UserActivity.Action.TOOK);
    activity.setTimestamp(new Date());
    userActivityRepository.save(activity);
}

    public void returnBook(String bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        String currentHolderEmail = book.getCurrentHolder();
        User currentHolder = userRepository.findByEmail(currentHolderEmail)
                .orElseThrow(() -> new UserNotFoundException(currentHolderEmail));

        //update values
        book.setAvailable(true);
        book.setCurrentHolder(null);
        book.setDueDate(null);
        book.getRequesters().clear();
        bookRepository.save(book);

        //Activity log,
        UserActivity activity = new UserActivity();
        activity.setUser(currentHolder); // Get user from currentHolder email
        activity.setBookId(bookId);
        activity.setAction(UserActivity.Action.RETURNED);
        activity.setTimestamp(new Date());
        userActivityRepository.save(activity);
    }

    public void requestBook(String bookId, String requesterEmail, boolean withMeeting) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        User requester = userRepository.findByEmail(requesterEmail)
                .orElseThrow(() -> new UserNotFoundException(requesterEmail));

        if (book.isAvailable()) {
            throw new BookAvailableException(bookId); //Can't request a book from a user if no user has taken it,
        }

        // Check for existing request
        boolean existingRequest = book.getRequesters().stream()
                .anyMatch(r -> r.getEmail().equalsIgnoreCase(requesterEmail));

        if (existingRequest) {
            throw new DuplicateRequestException(bookId, requesterEmail);
        }

        // Create new request
        Requester request = new Requester();
        request.setEmail(requesterEmail);
        request.setRequestDate(new Date());
        request.setMessage(withMeeting ? "Meeting requested" : "I Would like to request the book if you no longer need it, Thanks in advanec.");
        book.addRequester(request);

        // Send notification to current holder via EmailService
        if (book.getCurrentHolder() != null) {
            emailService.sendBookRequest(
                    book.getCurrentHolder(),
                    book.getTitle(),
                    requesterEmail,
                    request.getMessage(),
                    withMeeting
            );
        }

        bookRepository.save(book);

        // Activity log,
        UserActivity.Action actionType = withMeeting ? UserActivity.Action.REQUEST_WITH_MEETING :
                UserActivity.Action.SIMPLE_REQUEST;

        UserActivity activity = new UserActivity();
        activity.setUser(requester);
        activity.setBookId(bookId);
        activity.setAction(actionType);
        activity.setTimestamp(new Date());
        userActivityRepository.save(activity);
    }
}