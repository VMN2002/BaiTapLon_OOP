package DAO;

import Model.Book;
import Model.Document;
import Model.Magazine;
import Model.Thesis;
import Util.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DocumentDAO {

    public List<Document> getAllDocuments() {
        List<Document> documentList = new ArrayList<>();
        String query = "SELECT * FROM Documents";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String type = rs.getString("type");
                Document doc = null;

                switch (type) {
                    case "Book":
                        doc = new Book(
                                rs.getString("doc_id"),
                                rs.getString("title"),
                                rs.getString("author"),
                                rs.getString("publisher"),
                                rs.getInt("publication_year"),
                                rs.getString("genre")
                        );
                        break;
                    case "Magazine":
                        doc = new Magazine(
                                rs.getString("doc_id"),
                                rs.getString("title"),
                                rs.getString("author"),
                                rs.getString("publisher"),
                                rs.getInt("publication_year"),
                                rs.getInt("issue_number")
                        );
                        break;
                    case "Thesis":
                        doc = new Thesis(
                                rs.getString("doc_id"),
                                rs.getString("title"),
                                rs.getString("author"),
                                rs.getString("publisher"),
                                rs.getInt("publication_year"),
                                rs.getString("university")
                        );
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
}