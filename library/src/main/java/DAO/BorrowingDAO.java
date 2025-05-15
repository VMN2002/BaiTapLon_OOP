package DAO;

import Model.Borrowing;
import Util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowingDAO {

    public List<Borrowing> getAllBorrowings() {
        List<Borrowing> borrowingList = new ArrayList<>();
        String query = "SELECT * FROM Borrowing";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Borrowing borrowing = new Borrowing(
                        rs.getInt("borrow_id"),
                        rs.getString("user_id"),
                        rs.getString("doc_id"),
                        rs.getTimestamp("borrow_date"),
                        rs.getTimestamp("return_date")
                );
                borrowingList.add(borrowing);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrowingList;
    }

    public boolean addBorrowing(String userId, String docId) {
        String query = "INSERT INTO Borrowing (user_id, doc_id) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, userId);
            pstmt.setString(2, docId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean returnDocument(int borrowId) {
        String query = "UPDATE Borrowing SET return_date = CURRENT_TIMESTAMP WHERE borrow_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, borrowId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Borrowing> getUnreturnedBorrowings(String userId) {
        List<Borrowing> borrowings = new ArrayList<>();
        String query = "SELECT * FROM Borrowing WHERE user_id = ? AND return_date IS NULL";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Borrowing borrowing = new Borrowing(
                        rs.getInt("borrow_id"),
                        rs.getString("user_id"),
                        rs.getString("doc_id"),
                        rs.getTimestamp("borrow_date"),
                        rs.getTimestamp("return_date")
                );
                borrowings.add(borrowing);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrowings;
    }

    public boolean returnDocumentByUserAndDoc(String userId, String docId) {
        String query = "UPDATE Borrowing SET return_date = CURRENT_TIMESTAMP " +
                "WHERE user_id = ? AND doc_id = ? AND return_date IS NULL";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, userId);
            pstmt.setString(2, docId);
            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
