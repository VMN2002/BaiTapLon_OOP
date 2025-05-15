package Model;

public abstract class Document {
    protected String docId;
    protected String title;
    protected String author;
    protected String publisher;
    protected int publicationYear;

    public Document(String title, String author, String publisher, int publicationYear) {
        this.docId = "0";
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
    }

    public String getDocId() {
        return docId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public abstract void displayInfo();
}