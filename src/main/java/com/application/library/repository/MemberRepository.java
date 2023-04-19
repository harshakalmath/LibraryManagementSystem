package com.application.library.repository;

import com.application.library.model.Member;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemberRepository extends MongoRepository<Member, Integer> {
}
