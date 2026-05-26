import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LibraryManager {
    private List<Book> books;
    private static final String FILE_NAME = "books.txt";

    public LibraryManager() {
        books = new ArrayList<>();
        loadBooksFromFile();
    }

    public void addBook(int bookId, String title, String author) {
        // Check if book ID already exists
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                System.out.println("Error: A book with ID " + bookId + " already exists.");
                return;
            }
        }
        
        Book newBook = new Book(bookId, title, author, false);
        books.add(newBook);
        saveBooksToFile();
        System.out.println("Book added successfully.");
    }

    public void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
            return;
        }

        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("%-10s %-30s %-20s %-15s\n", "Book ID", "Title", "Author", "Status");
        System.out.println("--------------------------------------------------------------------------------");
        for (Book book : books) {
            String status = book.isIssued() ? "Issued" : "Available";
            System.out.printf("%-10d %-30s %-20s %-15s\n", book.getBookId(), book.getTitle(), book.getAuthor(), status);
        }
        System.out.println("--------------------------------------------------------------------------------");
    }

    public void searchBook(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                System.out.println("Book Found:");
                System.out.println("-------------------------");
                System.out.println(book.toString());
                System.out.println("-------------------------");
                return;
            }
        }
        System.out.println("Book with ID " + bookId + " not found.");
    }

    public void issueBook(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                if (!book.isIssued()) {
                    book.setIssued(true);
                    saveBooksToFile();
                    System.out.println("Book issued successfully.");
                } else {
                    System.out.println("This book is already issued.");
                }
                return;
            }
        }
        System.out.println("Book with ID " + bookId + " not found.");
    }

    public void returnBook(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                if (book.isIssued()) {
                    book.setIssued(false);
                    saveBooksToFile();
                    System.out.println("Book returned successfully.");
                } else {
                    System.out.println("This book was not issued.");
                }
                return;
            }
        }
        System.out.println("Book with ID " + bookId + " not found.");
    }

    public void deleteBook(int bookId) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getBookId() == bookId) {
                books.remove(i);
                saveBooksToFile();
                System.out.println("Book deleted successfully.");
                return;
            }
        }
        System.out.println("Book with ID " + bookId + " not found.");
    }

    private void saveBooksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Book book : books) {
                // Use comma-separated values to store book properties
                writer.write(book.getBookId() + "," + book.getTitle() + "," + book.getAuthor() + "," + book.isIssued());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving books to file: " + e.getMessage());
        }
    }

    private void loadBooksFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    try {
                        int bookId = Integer.parseInt(parts[0].trim());
                        String title = parts[1].trim();
                        String author = parts[2].trim();
                        boolean issued = Boolean.parseBoolean(parts[3].trim());
                        books.add(new Book(bookId, title, author, issued));
                    } catch (NumberFormatException e) {
                        System.out.println("Skipping invalid record in file: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading books from file: " + e.getMessage());
        }
    }
}
