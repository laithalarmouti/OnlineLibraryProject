package com.OnlineLibrary.Seed;

import com.OnlineLibrary.Entities.mongodb.Book;
import com.OnlineLibrary.Repository.mongodb.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final BookRepository bookRepository;

    public DatabaseSeeder(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) {
        if (bookRepository.count() == 0) {
            List<Book> books = new ArrayList<>();

            books.add(new Book(
                    "The Da Vinci Code",
                    "Dan Brown",
                    4L,  // Mystery & Thriller
                    true,  // Now available
                    null,  // No holder
                    null,  // No due date
                    new ArrayList<>()
            ));

            books.add(new Book(
                    "Clean Code",
                    "Robert C. Martin",
                    5L,  // Technology & Computing
                    true,
                    null,
                    null,
                    new ArrayList<>()
            ));

            books.add(new Book(
                    "Becoming",
                    "Michelle Obama",
                    6L,  // Biographies & Memoirs
                    true,
                    null,
                    null,
                    new ArrayList<>()
            ));

            books.add(new Book(
                    "The Hobbit",
                    "J.R.R. Tolkien",
                    7L,  // Fantasy
                    true,  // Changed to available
                    null,
                    null,
                    new ArrayList<>()
            ));

            books.add(new Book(
                    "Atomic Habits",
                    "James Clear",
                    8L,  // Self-Help & Motivation
                    true,
                    null,
                    null,
                    new ArrayList<>()
            ));

            books.add(new Book(
                    "Pride and Prejudice",
                    "Jane Austen",
                    9L,  // Romance
                    true,
                    null,
                    null,
                    new ArrayList<>()
            ));

            books.add(new Book(
                    "Dune",
                    "Frank Herbert",
                    10L,  // Science Fiction
                    true,
                    null,
                    null,
                    new ArrayList<>()
            ));

            books.add(new Book(
                    "Rich Dad Poor Dad",
                    "Robert Kiyosaki",
                    11L,  // Business & Finance
                    true,  // Changed to available
                    null,
                    null,
                    new ArrayList<>()
            ));

            books.add(new Book(
                    "Lonely Planet Japan",
                    "Lonely Planet",
                    12L,  // Travel Guides
                    true,
                    null,
                    null,
                    new ArrayList<>()
            ));

            books.add(new Book(
                    "The Odyssey",
                    "Homer",
                    1L,  // Fiction
                    true,
                    null,
                    null,
                    new ArrayList<>()
            ));

            books.add(new Book(
                    "Sapiens",
                    "Yuval Noah Harari",
                    2L,  // Science
                    true,
                    null,
                    null,
                    new ArrayList<>()
            ));

            books.add(new Book(
                    "The Art of War",
                    "Sun Tzu",
                    3L,
                    true,
                    null,
                    null,
                    new ArrayList<>()
            ));

            books.add(new Book(
                    "The Silent Patient",
                    "Alex Michaelides",
                    4L,  // Mystery & Thriller
                    true,
                    null,
                    null,
                    new ArrayList<>()
            ));

            books.add(new Book(
                    "The Pragmatic Programmer",
                    "Andrew Hunt",
                    5L,  // Technology & Computing
                    true,
                    null,
                    null,
                    new ArrayList<>()
            ));

            books.add(new Book(
                    "Educated",
                    "Tara Westover",
                    6L,  // Biographies & Memoirs
                    true,
                    null,
                    null,
                    new ArrayList<>()
            ));

            books.add(new Book(
                    "The Name of the Wind",
                    "Patrick Rothfuss",
                    7L,  // Fantasy
                    true,
                    null,
                    null,
                    new ArrayList<>()
            ));

            books.add(new Book(
                    "The 7 Habits of Highly Effective People",
                    "Stephen Covey",
                    8L,  // Self-Help & Motivation
                    true,
                    null,
                    null,
                    new ArrayList<>()
            ));

            books.add(new Book(
                    "The Notebook",
                    "Nicholas Sparks",
                    9L,  // Romance
                    true,
                    null,
                    null,
                    new ArrayList<>()
            ));

            books.add(new Book(
                    "Neuromancer",
                    "William Gibson",
                    10L,  // Science Fiction
                    true,
                    null,
                    null,
                    new ArrayList<>()
            ));

            books.add(new Book(
                    "The Intelligent Investor",
                    "Benjamin Graham",
                    11L,  // Business & Finance
                    true,
                    null,
                    null,
                    new ArrayList<>()
            ));

            bookRepository.saveAll(books);
        }
    }
}