package Service;



import Model.Document;
import Model.User;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Document> documents;
    private List<User> users;

    public Library() {
        this.documents = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public void addDocument(Document doc) {
        documents.add(doc);
    }

    public void removeDocument(String docId) {
        documents.removeIf(doc -> doc.getDocId().equals(docId));
    }

    public Document findDocumentById(String docId) {
        for (Document doc : documents) {
            if (doc.getDocId().equals(docId)) {
                return doc;
            }
        }
        return null;
    }

    public List<Document> searchDocuments(String keyword) {
        List<Document> result = new ArrayList<>();
        for (Document doc : documents) {
            if (doc.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                    doc.getAuthor().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(doc);
            }
        }
        return result;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User findUserById(String userId) {
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    public boolean borrowDocument(String userId, String docId) {
        User user = findUserById(userId);
        Document doc = findDocumentById(docId);
        if (user != null && doc != null) {
            user.borrowDocument(doc);
            return true;
        }
        return false;
    }

    public boolean returnDocument(String userId, String docId) {
        User user = findUserById(userId);
        Document doc = findDocumentById(docId);
        if (user != null && doc != null) {
            user.returnDocument(doc);
            return true;
        }
        return false;
    }

    public void displayAllDocuments() {
        for (Document doc : documents) {
            doc.displayInfo();
            System.out.println("----------------------------");
        }
    }
}