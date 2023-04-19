package com.application.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "book")
public class Book {

    @Id
    private String id;

    String name;
    String author;
    Integer numberOfCopies;
    String genre; // pre-defined list
    String status; // pre-defined list
    int rackNumber; // auto-generated



}
