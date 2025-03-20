package com.OnlineLibrary.Entities.mongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "books")
public class Book {
    @Id
    private String bookId;
    private String title;
    private String author;
    private Long categoryId;
    private boolean isAvailable;
    private String currentHolder;
    private Date dueDate;
    private List<Requester> requesters = new ArrayList<>();
    public Book() {}

    public Book(String title, String author, Long categoryId, boolean isAvailable,
                String currentHolder, Date dueDate, List<Requester> requesters) {
        this.title = title;
        this.author = author;
        this.categoryId = categoryId;
        this.isAvailable = isAvailable;
        this.currentHolder = currentHolder;
        this.dueDate = dueDate;
        this.requesters = requesters;
    }
    public static class Requester {
        private String email;
        private Date requestDate;
        private String message;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public Date getRequestDate() { return requestDate; }
        public void setRequestDate(Date requestDate) { this.requestDate = requestDate; }

        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }

    public String getBookId() { return bookId; }
    public void setBookId(String bookId) { this.bookId = bookId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }

    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    public String getCurrentHolder() { return currentHolder; }
    public void setCurrentHolder(String currentHolder) { this.currentHolder = currentHolder; }

    public Date getDueDate() { return dueDate; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }

    public List<Requester> getRequesters() { return requesters; }
    public void setRequesters(List<Requester> requesters) { this.requesters = requesters; }


    public void addRequester(Requester requester) {
        this.requesters.add(requester);
    }
}