public class Book {
    private int bookId;
    private String title;
    private String author;
    private boolean issued;

    public Book(int bookId, String title, String author, boolean issued) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.issued = issued;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isIssued() {
        return issued;
    }

    public void setIssued(boolean issued) {
        this.issued = issued;
    }

    @Override
    public String toString() {
        String status = issued ? "Issued" : "Available";
        return "Book ID: " + bookId + "\nTitle: " + title + "\nAuthor: " + author + "\nStatus: " + status;
    }
}
