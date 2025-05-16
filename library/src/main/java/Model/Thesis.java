package Model;

public class Thesis extends Document {
    private String university;

    public Thesis(int docId, String title, String author, String publisher, int publicationYear, String university) {
        super(docId, title, author, publisher, publicationYear);
        this.university = university;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    @Override
    public void displayInfo() {
        System.out.println("Thesis - ID: " + docId + ", Title: " + title + ", Author: " + author +
                ", Publisher: " + publisher + ", Year: " + publicationYear + ", University: " + university);
    }
}
