package com.application.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "account")
public class Member extends Account{

    //String name;
    List<String> bookIds;
    int numberOfBooksIssued;
    String lendingBehaviour;

    public Member(String emailAddress, String password) {
        super(emailAddress,password);
        bookIds = new ArrayList<>();
        numberOfBooksIssued = 0;
        lendingBehaviour = LibraryHelper.chooseLendingBehaviour();
    }


    // add methods specific to library members


}
