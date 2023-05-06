package com.application.frontend;


import java.io.IOException;
import java.util.Scanner;

public class Frontend {

    public static void main(String[] args) throws IOException {
        menu();

    }

    public static void menu() throws IOException {
        Scanner sc = new Scanner(System.in);
        LibraryServices libraryServices = new LibraryServices();

        String type = new String();
        System.out.println("Hi! Welcome to the library..");
        int op = 0;
        while(op!=3) {
            System.out.println("Choose if you want to login/register:");
            System.out.println("1.Login");
            System.out.println("2.Register");
            System.out.println("3.Press 3 to exit menu");
            op = sc.nextInt();

            switch (op) {
                case 1:
                    type = libraryServices.login();
                    break;
                case 2:
                    libraryServices.registerNewMember();
                    break;
                case 3:
                    break;
            }
            if (op == 1)
                if (type.equalsIgnoreCase("librarian")) {
                    System.out.println("Add book to database");
                    libraryServices.addBook();
                } else {
                    int option=0;
                    while (option!=6) {
                        System.out.println("Choose from the following:");
                        System.out.println("1.Search catalog by name");
                        System.out.println("2.Search catalog by author");
                        System.out.println("3.Search catalog by genre");
                        System.out.println("4.Issue book");
                        System.out.println("5.Return book");
                        System.out.println("6.Press 6 to exit menu:");

                        option = sc.nextInt();

                        switch (option) {
                            case 1:
                                libraryServices.searchByName();
                                break;
                            case 2:
                                libraryServices.searchByAuthor();
                                break;
                            case 3:
                                libraryServices.searchByGenre();
                                break;
                            case 4:
                                libraryServices.issueBook();
                                break;
                            case 5:
                                libraryServices.returnBook();
                                break;
                            case 6:
                                break;

                        }

                    }


                }
        }






    }
}
