package com.application.library.repository;

import com.application.library.model.IssuedBook;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IssuedBooksRepository extends MongoRepository<IssuedBook, Integer> {
}
