package Model;

public class Book extends Document {
    private String genre;

    public Book(String title, String author, String publisher, int publicationYear, String genre) {
        super(title, author, publisher, publicationYear);
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public void displayInfo() {
        System.out.println("Book - ID: " + docId + ", Title: " + title + ", Author: " + author +
                ", Publisher: " + publisher + ", Year: " + publicationYear + ", Genre: " + genre);
    }
}