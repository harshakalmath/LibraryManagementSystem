package com.application.library.repository;

import com.application.library.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, Integer> {
}
