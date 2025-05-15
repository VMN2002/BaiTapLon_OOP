package Model;

import java.util.ArrayList;
import java.util.List;


public class User {
    private String userId;
    private String name;
    private String email;
    private List<Document> borrowedDocuments;

    public User(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.borrowedDocuments = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Document> getBorrowedDocuments() {
        return borrowedDocuments;
    }

    public void borrowDocument(Document doc) {
        borrowedDocuments.add(doc);
    }

    public void returnDocument(Document doc) {
        borrowedDocuments.remove(doc);
    }

    public void printUserInfo() {
        System.out.println("User ID: " + userId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Borrowed Documents: ");
        for (Document doc : borrowedDocuments) {
            System.out.println("  - " + doc.getTitle());
        }
    }
}