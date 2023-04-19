package com.application.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "member")
public class Member extends Account{

    //String name;
    String[] bookIds;
    int numberOfBooksIssued;

    public Member(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.password = password;
    }


    // add methods specific to library members


}
