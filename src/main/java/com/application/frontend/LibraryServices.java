package com.application.frontend;

import com.application.library.model.Account;
import com.application.library.model.Member;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class LibraryServices {

    ApiCalls apiCalls = new ApiCalls();
    String memberId;
    Account account;
    Member member;


    public void login() throws IOException {
        System.out.println("Enter valid credentials: ");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter memberId: ");
        String memberId = sc.next();
        System.out.println("Enter password: ");
        String password = sc.next();
        System.out.println("Are you a librarian? ");
        String answer = sc.next();
        boolean isMember = false;
        if(answer.equalsIgnoreCase("yes"))
            isMember = false;
        else
            isMember = true;
        String dataToSend = "{" +
                "\"memberId\": "+"\""+memberId+"\","+
                "\"password\": "+"\""+password+"\""+
                "" +
                "}";
        if(!isMember)
            account = (Account) apiCalls.callLoginApi(dataToSend,isMember);
        else
            member = (Member) apiCalls.callLoginApi(dataToSend,isMember);

    }

    public void registerNewMember() throws IOException {
        System.out.println("Enter new account details: ");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your email address: ");
        String emailAddress = sc.nextLine();
        System.out.println("Set a password: ");
        String password = sc.nextLine();
        String dataToSend = "{" +
                "\"emailAddress\": "+"\""+emailAddress+"\","+
                "\"password\": "+"\""+password+"\","+
                "" +
                "\"type\": \"member\"}";
        apiCalls.callCreateAccount(dataToSend);
        //call createAccount api

    }

    public void searchByName() throws IOException {
        System.out.println("Enter the name of the book: ");
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        String dataToSend = "{" +
                "\"name\": "+"\""+name+"\"}";
        apiCalls.callSearchByNameApi(dataToSend);
    }

    public void searchByAuthor() throws IOException {
        System.out.println("Enter the author name: ");
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        String dataToSend = "{" +
                "\"author\": "+"\""+name+"\"}";
        apiCalls.callSearchByAuthorName(dataToSend);
    }

    public void searchByGenre() throws IOException {
        System.out.println("Enter the genre: ");
        Scanner sc = new Scanner(System.in);
        String genre = sc.next();
        String dataToSend = "{" +
                "\"genre\": "+"\""+genre+"\"}";
        apiCalls.callSearchByGenreApi(dataToSend);

    }

    public void returnBook() throws IOException {
        // randomly check if fine is applicable
        boolean fine = fine();
        if(fine) {
            System.out.println("Sorry! You are returning the book late , you will be charged with a fine.");
            System.out.println("You are a "+member.getLendingBehaviour()+" member.");
            // call fine api in the backend
            // call update books api in the backend

        }

    }

    public void issueBook() throws IOException {
        // call issueBook api in the backend

    }

    public static boolean fine() {
        Random random = new Random();
        int rand = random.nextInt(0,2);
        if(rand==0)
            return true;
        else return false;
    }

}
