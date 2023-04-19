package com.application.library.model;

public class AccountFactory {


    public static Account getAccount(String accountType, String emailAddress, String password) {
        if(accountType.equalsIgnoreCase("librarian"))
            return getNewLibrarian(emailAddress,password);
        else if(accountType.equalsIgnoreCase("member"))
            return getNewMember(emailAddress,password);
        return null;
    }

    public static Account getNewLibrarian(String emailAddress, String password) {
        return new Librarian(emailAddress,password);
    }

    public static Account getNewMember(String emailAddress, String password) {
        return new Member(emailAddress,password);
    }


}
