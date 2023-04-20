package com.application.library.model;

import com.application.library.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    String id;
    String emailAddress;
    String password;

    public Account(String emailAddress, String password) {

        this.emailAddress = emailAddress;
        this.password = password;
    }


}
