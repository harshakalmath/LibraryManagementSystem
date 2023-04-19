package com.application.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Librarian extends Account{
    public Librarian(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.password = password;
    }

    // add methods related to librarian
}
