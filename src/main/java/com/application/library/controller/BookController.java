package com.application.library.controller;

import com.application.library.model.Book;
import com.application.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("/book")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MongoTemplate mongoTemplateBook;

    @PostMapping("/addBook")
    public String saveBook(@RequestBody Book book){
        bookRepository.save(book);
        return "Added Successfully";
    }

    @GetMapping("/findById")
    public Book findBookById(@RequestBody Integer id){
        return bookRepository.findById(id).get();

    }

    @PutMapping("/updateBookCopies")
    public String updateBook(@RequestBody Integer id, Integer numCopies) {
        //Query query = new Query().addCriteria(Criteria.where("name").is(name));
        //Update update = new Update().set("numberOfCopies",numCopies);
        //mongoTemplateBook.updateFirst(query,update,Book.class);
        //Optional<Book> optionalBook =
        Book book = findBookById(id);
        book.setNumberOfCopies(numCopies);
        bookRepository.save(book);
        return "Updated copies successfully";
    }

    @PutMapping("/updateBookStatus")
    public String updateBookStatus(@RequestBody Integer id, String status) {
        Book book = findBookById(id);
        book.setStatus(status);
        bookRepository.save(book);
        return "Updated status successfully";
    }

    @GetMapping("/getBooks")
    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    @GetMapping("/getBooksByGenre")
    public List<Book> getBooksByGenre(@RequestBody String genre){
        Query query = new Query();
        query.addCriteria(Criteria.where("genre").is(genre));
        return mongoTemplateBook.find(query,Book.class);
    }

    @GetMapping("/getBooksByName")
    public List<Book> getBooksByName(@RequestBody String name){
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        return mongoTemplateBook.find(query,Book.class);
    }

    @GetMapping("/getBooksByAuthor")
    public List<Book> getBooksByAuthor(@RequestBody String author){
        Query query = new Query();
        query.addCriteria(Criteria.where("author").is(author));
        return mongoTemplateBook.find(query,Book.class);
    }
}
