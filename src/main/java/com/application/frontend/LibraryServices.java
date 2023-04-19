package com.application.frontend;

import java.util.Scanner;

public class LibraryServices {

    public void login(){
        System.out.println("Enter valid credentials: ");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter email address: ");
        String emailAddress = sc.next();
        System.out.println("Enter password: ");
        String password = sc.next();

    }

    public void registerNewAccount(){
        System.out.println("");
    }
}
