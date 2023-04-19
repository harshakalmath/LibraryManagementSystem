package com.application.library.repository;

import com.application.library.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, Integer> {
}
