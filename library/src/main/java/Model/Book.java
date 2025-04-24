package Model;

public class Book extends Document {
    private String publisher;
    private int publicationYear;

    public Book(String id, String title, String author, String publisher, int publicationYear) {
        super(id, title, author);
        this.publisher = publisher;
        this.publicationYear = publicationYear;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    @Override
    public void printInfo() {
        System.out.println("Book ID: " + getId());
        System.out.println("Title: " + getTitle());
        System.out.println("Author: " + getAuthor());
        System.out.println("Publisher: " + publisher);
        System.out.println("Year: " + publicationYear);
    }
}
