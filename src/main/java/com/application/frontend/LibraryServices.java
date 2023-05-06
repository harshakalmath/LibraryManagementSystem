package com.application.frontend;

import com.application.library.model.Account;
import com.application.library.model.Book;
import com.application.library.model.Member;
import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class LibraryServices {

    ApiCalls apiCalls = new ApiCalls();
    Account account;
    Member member;


    public String login() throws IOException {
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
        System.out.println("is member: "+isMember);
        String dataToSend = "{" +
                "\"memberId\": "+"\""+memberId+"\","+
                "\"password\": "+"\""+password+"\""+
                "" +
                "}";
        if(!isMember) {
            account = (Account) apiCalls.callLoginApi(dataToSend, isMember);
            return "librarian";
        }
        else {
            member = (Member) apiCalls.callLoginApi(dataToSend, isMember);
            return "member";
        }


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

        System.out.println("Please enter the bookId of the book you want to return: ");
        Scanner sc = new Scanner(System.in);
        String bookId = sc.next();
        List<String> bookIds = new ArrayList<>();
        bookIds.add(bookId);
        // randomly check if fine is applicable
        boolean fine = fine();
        System.out.println("Fine? "+fine);
        if(true) {
            System.out.println("Sorry! You are returning the book late , you will be charged with a fine.");
            System.out.println("You are a "+member.getLendingBehaviour()+" member.");
            apiCalls.callFineApi(member.getLendingBehaviour());
        }
        JSONArray jsonArray = new JSONArray(bookIds);
        String data =  "{" +
                "\"bookIds\": "+jsonArray+
                "}";
        System.out.println(data);
        apiCalls.callRemoveBookfromMemberApi(member.getId(),data);
        String dataToSend = "{" +
                "\"_id\": "+"\""+bookId+"\"," +
                "\"copies\": "+"\"1\""+
                "}";
        apiCalls.callUpdateBookCopiesApi(dataToSend);

    }

    public void issueBook() throws IOException {
        // call add books to member api in the backend
        // call update book copies api

        System.out.println("Please enter the bookId of the book you want to issue: ");
        Scanner sc = new Scanner(System.in);
        String bookId = sc.next();
        boolean isAvailable = apiCalls.findBookById(bookId);
        if(!isAvailable)
            System.out.println("Sorry, the book is currently unavailable!");
        else {
            List<String> bookIds = new ArrayList<>();
            bookIds.add(bookId);
            apiCalls.callAddBookToMemberApi(member.getId(),bookIds);
            String dataToSend = "{" +
                    "\"_id\": "+"\""+bookId+"\"," +
                    "\"copies\": "+"\"-1\""+
                    "}";
            apiCalls.callUpdateBookCopiesApi(dataToSend);
        }

    }

    public void addBook() throws IOException {
        // librarian functionality
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of the book: ");
        String name = sc.next();
        System.out.println("Enter the name of the author: ");
        String author = sc.next();
        System.out.println("Enter the genre of the book: ");
        String genre = sc.next();
        System.out.println("Enter the number of copies: ");
        int copies = sc.nextInt();
        String status = "available";


        Book book = new Book(name,author, copies, genre, status);

        apiCalls.calladdBookApi(book);


    }


    public static boolean fine() {
        Random random = new Random();
        int rand = random.nextInt(0,2);
        if(rand==0)
            return true;
        else return false;
    }

}
