package Main;

import Model.Book;
import Model.Document;
import Model.User;
import Service.Library;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.println("Welcome to My Application");

        while (!exit) {
            System.out.println("------ Menu ------");
            System.out.println("[0] Exit");
            System.out.println("[1] Add Document");
            System.out.println("[2] Remove Document");
            System.out.println("[3] Update Document");
            System.out.println("[4] Search Document");
            System.out.println("[5] Display All Documents");
            System.out.println("[6] Add User");
            System.out.println("[7] Borrow Document");
            System.out.println("[8] Return Document");
            System.out.println("[9] Display User Information");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "0":
                    exit = true;
                    break;
                case "1":
                    System.out.print("Enter Document ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter Publisher: ");
                    String publisher = scanner.nextLine();
                    System.out.print("Enter Publication Year: ");
                    int year = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter genre");
                    String genre = scanner.nextLine();

                    Document newBook = new Book(title, author, publisher, year, genre);
                    library.addDocument(newBook);
                    System.out.println("Document added successfully.");
                    break;
                case "2":
                    System.out.print("Enter Document ID to remove: ");
                    String delId = scanner.nextLine();
                    library.removeDocument(delId);
                    System.out.println("Document removed (if exists).");
                    break;
                case "3":
                    System.out.println("Document update functionality not implemented yet.");
                    break;
                case "4":
                    System.out.print("Enter keyword to search (Title/Author): ");
                    String keyword = scanner.nextLine();
                    List<Document> results = library.searchDocuments(keyword);
                    if (results.isEmpty()) {
                        System.out.println("No documents found.");
                    } else {
                        for (Document doc : results) {
                            doc.displayInfo();
                            System.out.println("----------------------------");
                        }
                    }
                    break;
                case "5":
                    library.displayAllDocuments();
                    break;
                case "6":
                    System.out.print("Enter User ID: ");
                    String userId = scanner.nextLine();
                    System.out.print("Enter User Name: ");
                    String userName = scanner.nextLine();
                    System.out.print("Enter User Email: ");
                    String emailUser = scanner.nextLine();
                    library.addUser(new User(userId, userName, emailUser));
                    System.out.println("User added successfully.");
                    break;
                case "7":
                    System.out.print("Enter User ID: ");
                    String borrowerId = scanner.nextLine();
                    System.out.print("Enter Document ID to borrow: ");
                    String borrowDocId = scanner.nextLine();
                    if (library.borrowDocument(borrowerId, borrowDocId)) {
                        System.out.println("Document borrowed successfully.");
                    } else {
                        System.out.println("Failed to borrow document.");
                    }
                    break;
                case "8":
                    System.out.print("Enter User ID: ");
                    String returnerId = scanner.nextLine();
                    System.out.print("Enter Document ID to return: ");
                    String returnDocId = scanner.nextLine();
                    if (library.returnDocument(returnerId, returnDocId)) {
                        System.out.println("Document returned successfully.");
                    } else {
                        System.out.println("Failed to return document.");
                    }
                    break;
                case "9":
                    System.out.print("Enter User ID: ");
                    String infoUserId = scanner.nextLine();
                    User user = library.findUserById(infoUserId);
                    if (user != null) {
                        user.printUserInfo();
                    } else {
                        System.out.println("User not found.");
                    }
                    break;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }

        scanner.close();
        System.out.println("Application terminated.");
    }
}