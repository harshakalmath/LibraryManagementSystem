package com.application.library.controller;

import com.application.library.model.Account;
import com.application.library.repository.AccountRepository;
import com.application.library.service.LibraryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/account")
public class AccountInfoController {

    @Autowired
    private LibraryServices libraryServices;

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/createAccount")
    public String createAccount(@RequestBody String emailAddress, String password, String type){
        // call service class method , make it return an account info object and store that in the mongodb database.
        //accountRepository.save(accountInfo);
        libraryServices.createAccountService(type,emailAddress,password);
        return "Added to database successfully!";
    }

    @GetMapping("/getAccount")
    public Account getAccount(@RequestBody Integer memberId){
        return accountRepository.findById(memberId).get();
    }

    @DeleteMapping("/removeAccount")
    public String removeAccount(@RequestBody Integer memberId){
        Account accountInfo = getAccount(memberId);
        accountRepository.delete(accountInfo);
        return "Deleted Successfully";
    }
}
