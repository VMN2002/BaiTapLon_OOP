package Model;

public class Magazine extends Document {
    private int issueNumber;

    public Magazine(int docId, String title, String author, String publisher, int publicationYear, int issueNumber) {
        super(docId, title, author, publisher, publicationYear);
        this.issueNumber = issueNumber;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    @Override
    public void displayInfo() {
        System.out.println("Magazine - ID: " + docId + ", Title: " + title + ", Author: " + author +
                ", Publisher: " + publisher + ", Year: " + publicationYear + ", Issue: " + issueNumber);
    }
}
