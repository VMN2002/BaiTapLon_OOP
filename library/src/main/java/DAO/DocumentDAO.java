package DAO;

import Model.Book;
import Model.Document;
import Model.Magazine;
import Model.Thesis;
import Util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocumentDAO {

    public List<Document> getAllDocuments() {
        List<Document> documentList = new ArrayList<>();
        String query = "SELECT * FROM Documents";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String type = rs.getString("type");
                if (type == null) {
                    continue;
                }
                Document doc = null;
                switch (type) {
                    case "Book":
                        doc = new Book(
                                rs.getString("title"),
                                rs.getString("author"),
                                rs.getString("publisher"),
                                rs.getInt("publication_year"),
                                rs.getString("genre")
                        );
                        break;
                    case "Magazine":
                        doc = new Magazine(
                                rs.getString("title"),
                                rs.getString("author"),
                                rs.getString("publisher"),
                                rs.getInt("publication_year"),
                                rs.getInt("issue_number")
                        );
                        break;
                    case "Thesis":
                        doc = new Thesis(
                                rs.getString("title"),
                                rs.getString("author"),
                                rs.getString("publisher"),
                                rs.getInt("publication_year"),
                                rs.getString("university")
                        );
                        break;
                    default:
                        System.err.println("Unknown document type: " + type);
                        break;
                }
                if (doc != null) {
                    documentList.add(doc);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return documentList;
    }

    public boolean addDocument(Document doc) {
        String query = "INSERT INTO Documents (title, author, publisher, publication_year, type, genre, issue_number, university) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, doc.getTitle());
            pstmt.setString(2, doc.getAuthor());
            pstmt.setString(3, doc.getPublisher());
            pstmt.setInt(4, doc.getPublicationYear());

            if (doc instanceof Book) {
                Book book = (Book) doc;
                pstmt.setString(5, "Book");
                pstmt.setString(6, book.getGenre());
                pstmt.setNull(7, Types.INTEGER);
                pstmt.setNull(8, Types.VARCHAR);
            } else if (doc instanceof Magazine) {
                Magazine magazine = (Magazine) doc;
                pstmt.setString(5, "Magazine");
                pstmt.setNull(6, Types.VARCHAR);
                pstmt.setInt(7, magazine.getIssueNumber());
                pstmt.setNull(8, Types.VARCHAR);
            } else if (doc instanceof Thesis) {
                Thesis thesis = (Thesis) doc;
                pstmt.setString(5, "Thesis");
                pstmt.setNull(6, Types.VARCHAR);
                pstmt.setNull(7, Types.INTEGER);
                pstmt.setString(8, thesis.getUniversity());
            } else {
                return false;
            }

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    int generatedId = rs.getInt(1);
                    String formattedId = String.format("B%03d", generatedId);
                    doc.setDocId(formattedId);
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getDocumentCount() throws SQLException {
        String query = "SELECT COUNT(*) FROM Documents";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        }
    }

    public boolean deleteDocument(String docId) {
        String query = "DELETE FROM Documents WHERE doc_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, docId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Document getDocumentById(String docId) {
        String query = "SELECT * FROM Documents WHERE doc_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, docId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String type = rs.getString("type");
                    if (type == null) {
                        return null;
                    }
                    Document doc = null;
                    switch (type) {
                        case "Book":
                            doc = new Book(
                                    rs.getString("title"),
                                    rs.getString("author"),
                                    rs.getString("publisher"),
                                    rs.getInt("publication_year"),
                                    rs.getString("genre")
                            );
                            break;
                        case "Magazine":
                            doc = new Magazine(
                                    rs.getString("title"),
                                    rs.getString("author"),
                                    rs.getString("publisher"),
                                    rs.getInt("publication_year"),
                                    rs.getInt("issue_number")
                            );
                            break;
                        case "Thesis":
                            doc = new Thesis(
                                    rs.getString("title"),
                                    rs.getString("author"),
                                    rs.getString("publisher"),
                                    rs.getInt("publication_year"),
                                    rs.getString("university")
                            );
                            break;
                        default:
                            System.err.println("Unknown document type: " + type);
                            break;
                    }
                    return doc;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}