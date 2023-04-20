package com.application.library.service;

import com.application.library.model.Account;
import com.application.library.model.AccountFactory;
import com.application.library.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryServices {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccountService(String type, String emailAddress, String password) {
        System.out.println("Create factory with type: "+type);
        Account account = AccountFactory.getAccount(type,emailAddress,password);
        System.out.println(account.toString());
        String id = accountRepository.save(account).getId();
        return account;
    }

}
