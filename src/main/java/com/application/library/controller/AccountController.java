package com.application.library.controller;

import com.application.library.model.Account;
import com.application.library.repository.AccountRepository;
import com.application.library.service.LibraryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private LibraryServices libraryServices;

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/createAccount")
    public String createAccount(@RequestBody String data1){
        JSONObject data = new JSONObject(data1);
        Account account = libraryServices.createAccountService(data.get("type").toString(),data.get("emailAddress").toString(),data.get("password").toString());
        accountRepository.save(account);
        return "Added to database successfully with id: ";
    }

    @GetMapping("/getAccounts")
    public List<Account> getAccounts(){
        return accountRepository.findAll();
    }

    @PostMapping("/getAccount")
    public Account getAccount(@RequestBody String data1){ //login api call
        JSONObject data = new JSONObject(data1);
        String memberId = data.getString("memberId");
        String password = data.getString("password");
        Account account = accountRepository.findById(memberId).get();
        if(account!=null) {
            if (password.equalsIgnoreCase(account.getPassword())) {
                System.out.println(account.toString());
                return account;
            }

            else return null;
        }
        else return null;
        //return accountRepository.findById(memberId).get();

    }

    @DeleteMapping("/removeAccount")
    public String removeAccount(@RequestBody String memberId){
        Account accountInfo = getAccount(memberId);
        accountRepository.delete(accountInfo);
        return "Deleted Successfully";
    }
}
