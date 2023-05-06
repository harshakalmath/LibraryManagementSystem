package com.application.library.controller;

import com.application.library.model.Book;
import org.bson.types.ObjectId;
import com.application.library.repository.BookRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MongoTemplate mongoTemplateBook;

    @PostMapping("/addBook")
    public String saveBook(@RequestBody Book book){
        System.out.println("Inside add book: ");
        System.out.println(book);
        bookRepository.save(book);
        return "Added Successfully";
    }

    @GetMapping("/findById")
    public Book findBookById(@RequestParam String id){
        //JSONObject data = new JSONObject(data1);
        //String id = data.getString("_id");
        System.out.println("id: "+id);
        ObjectId objectId = new ObjectId(id);
        return mongoTemplateBook.find(new Query().addCriteria(Criteria.where("_id").is(objectId)),Book.class).get(0);

    }

    @PutMapping("/updateBookCopies")
    public String updateBook(@RequestBody String data1) {
        JSONObject data = new JSONObject(data1);
        String id = data.getString("_id");
        int numCopies = Integer.valueOf(data.getString("copies"));
        Book book = findBookById(id);
        book.setNumberOfCopies(book.getNumberOfCopies()+numCopies);
        if(book.getNumberOfCopies()==0)
            book.setStatus("out-of-stock");
        if(book.getNumberOfCopies()>0)
            book.setStatus("available");
        bookRepository.save(book);
        return "Updated copies successfully";
    }


    @PutMapping("/updateBookStatus")
    public String updateBookStatus(@RequestBody String data1) {
        JSONObject data = new JSONObject(data1);
        String id = data.getString("id");
        String status = data.getString("status");
        Book book = findBookById(id);
        book.setStatus(status);
        bookRepository.save(book);
        return "Updated status successfully";
    }

    @GetMapping("/getBooks")
    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    @PostMapping("/getBooksByGenre")
    public List<Book> getBooksByGenre(@RequestBody String data1){
        JSONObject data = new JSONObject(data1);
        String genre = data.getString("genre");
        Query query = new Query();
        query.addCriteria(Criteria.where("genre").is(genre));
        return mongoTemplateBook.find(query,Book.class);
    }

    @PostMapping("/getBooksByName")
    public List<Book> getBooksByName(@RequestBody String data1){
        JSONObject data = new JSONObject(data1);
        String name = data.getString("name");
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        return mongoTemplateBook.find(query,Book.class);
    }

    @PostMapping("/getBooksByAuthor")
    public List<Book> getBooksByAuthor(@RequestBody String data1){
        JSONObject data = new JSONObject(data1);
        String author = data.getString("author");
        Query query = new Query();
        query.addCriteria(Criteria.where("author").is(author));
        return mongoTemplateBook.find(query,Book.class);
    }

}
