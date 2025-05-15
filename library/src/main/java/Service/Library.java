package Service;

import DAO.BorrowingDAO;
import DAO.DocumentDAO;
import DAO.UserDAO;
import Model.Document;
import Model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private DocumentDAO documentDAO;
    private UserDAO userDAO;
    private BorrowingDAO borrowingDAO;

    public Library() {
        this.documentDAO = new DocumentDAO();
        this.userDAO = new UserDAO();
        this.borrowingDAO = new BorrowingDAO();
    }

    public boolean addDocument(Document doc) {
        return documentDAO.addDocument(doc);
    }

    public boolean addUser(User user) {
        return userDAO.addUser(user);
    }

    public int getDocumentCount() throws SQLException {
        return documentDAO.getDocumentCount();
    }

    public int getUserCount() throws SQLException {
        return userDAO.getUserCount();
    }

    public void removeDocument(String docId) {
        documentDAO.deleteDocument(docId);
    }

    public Document findDocumentById(String docId) {
        return documentDAO.getDocumentById(docId);
    }

    public List<Document> searchDocuments(String keyword) {
        List<Document> allDocuments = documentDAO.getAllDocuments();
        List<Document> result = new ArrayList<>();
        for (Document doc : allDocuments) {
            if (doc.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                    doc.getAuthor().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(doc);
            }
        }
        return result;
    }

    public User findUserById(String userId) {
        return userDAO.getUserById(userId);
    }

    public boolean borrowDocument(String userId, String docId) {
        return borrowingDAO.addBorrowing(userId, docId);
    }

    public boolean returnDocument(String userId, String docId) {
        return borrowingDAO.returnDocumentByUserAndDoc(userId, docId);
    }

    public void displayAllDocuments() {
        List<Document> docs = documentDAO.getAllDocuments();
        for (Document doc : docs) {
            doc.displayInfo();
            System.out.println("----------------------------");
        }
    }
}