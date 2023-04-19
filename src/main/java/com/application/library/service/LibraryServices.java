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

    public void createAccountService(String type, String emailAddress, String password) {
        Account account = AccountFactory.getAccount(type,emailAddress,password);
        accountRepository.save(account);
    }
}
