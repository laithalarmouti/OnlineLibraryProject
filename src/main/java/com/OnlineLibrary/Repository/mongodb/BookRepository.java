package com.OnlineLibrary.Repository.mongodb;

import com.OnlineLibrary.Entities.mongodb.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {
    List<Book> findByCategoryId(Long categoryId);
}