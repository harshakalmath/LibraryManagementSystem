package com.application.library.controller;

import com.application.library.model.IssuedBook;
import com.application.library.repository.IssuedBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/issuedBooks")
public class IssuedBookController {

    @Autowired
    private IssuedBooksRepository issuedBooksRepository;

    @Autowired
    private MongoTemplate mongoTemplateIssuedBook;


    @PostMapping("/addEntry")
    public String addEntry(@RequestBody IssuedBook issuedBook){
        issuedBooksRepository.save(issuedBook);
        return "Added successfully";
    }

    @GetMapping("/getIssuedBooks")
    public List<String> getIssuedBooks(@RequestBody Integer memberId){
        IssuedBook issuedBooks = issuedBooksRepository.findById(memberId).get();
        return issuedBooks.getBooksIssued();
    }



}
