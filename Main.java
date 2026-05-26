import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibraryManager libraryManager = new LibraryManager();

        while (true) {
            System.out.println("\n=================================");
            System.out.println("Library Management System");
            System.out.println("=================================");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Search Book");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Delete Book");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = -1;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // consume newline
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // consume invalid input
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    int bookId = -1;
                    try {
                        bookId = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid Book ID. Please enter a valid number.");
                        scanner.nextLine();
                        break;
                    }
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    libraryManager.addBook(bookId, title, author);
                    break;
                    
                case 2:
                    libraryManager.viewBooks();
                    break;
                    
                case 3:
                    System.out.print("Enter Book ID to search: ");
                    try {
                        int searchId = scanner.nextInt();
                        libraryManager.searchBook(searchId);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid Book ID. Please enter a valid number.");
                        scanner.nextLine();
                    }
                    break;
                    
                case 4:
                    System.out.print("Enter Book ID to issue: ");
                    try {
                        int issueId = scanner.nextInt();
                        libraryManager.issueBook(issueId);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid Book ID. Please enter a valid number.");
                        scanner.nextLine();
                    }
                    break;
                    
                case 5:
                    System.out.print("Enter Book ID to return: ");
                    try {
                        int returnId = scanner.nextInt();
                        libraryManager.returnBook(returnId);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid Book ID. Please enter a valid number.");
                        scanner.nextLine();
                    }
                    break;
                    
                case 6:
                    System.out.print("Enter Book ID to delete: ");
                    try {
                        int deleteId = scanner.nextInt();
                        libraryManager.deleteBook(deleteId);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid Book ID. Please enter a valid number.");
                        scanner.nextLine();
                    }
                    break;
                    
                case 7:
                    System.out.println("Exiting Library Management System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    
                default:
                    System.out.println("Invalid choice. Please select a valid option between 1 and 7.");
            }
        }
    }
}
