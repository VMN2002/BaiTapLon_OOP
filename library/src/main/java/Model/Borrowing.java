package Model;

import java.sql.Timestamp;

public class Borrowing {
    private int borrowId;
    private String userId;
    private String docId;
    private Timestamp borrowDate;
    private Timestamp returnDate;

    public Borrowing(int borrowId, String userId, String docId, Timestamp borrowDate, Timestamp returnDate) {
        this.borrowId = borrowId;
        this.userId = userId;
        this.docId = docId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public int getBorrowId() {
        return borrowId;
    }

    public String getUserId() {
        return userId;
    }

    public String getDocId() {
        return docId;
    }

    public Timestamp getBorrowDate() {
        return borrowDate;
    }

    public Timestamp getReturnDate() {
        return returnDate;
    }
}
