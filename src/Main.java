import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        FinanceManager manager = new FinanceManager();

        // Load categories and transactions from CSV files
        CSVHandler.loadCategories(manager, "categories.csv");
        CSVHandler.loadTransactions(manager, "transactions.csv");

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // Menu Loop
        while (running) {
            System.out.println("\n**** Finance Manager Menu ****");
            System.out.println("1. Add Category");
            System.out.println("2. Add Transaction");
            System.out.println("3. View Categories");
            System.out.println("4. View Transactions");
            System.out.println("5. Save Data");
            System.out.println("6. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    // Add Category
                    System.out.print("Enter category name: ");
                    String categoryName = scanner.nextLine();
                    manager.addCategory(categoryName);
                    System.out.println("Category added.");
                    break;

                case 2:
                    // Add Transaction
                    System.out.print("Enter transaction type (EXPENSE/INCOME): ");
                    TransactionType type = TransactionType.valueOf(scanner.nextLine().toUpperCase());
                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    System.out.print("Enter category ID: ");
                    int categoryId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    // Get the category based on the entered ID
                    Category category = manager.getCategories().get(categoryId - 1); // assuming category IDs are 1-based

                    // Ask user for date and parse it
                    System.out.print("Enter transaction date (yyyy-MM-dd): ");
                    String dateInput = scanner.nextLine();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate date = LocalDate.parse(dateInput, formatter);

                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();

                    // Add transaction with the parsed date
                    manager.addTransaction(1, type, amount, category, date, description);
                    System.out.println("Transaction added.");
                    break;

                case 3:
                    // View Categories
                    System.out.println("\nCategories:");
                    for (Category c : manager.getCategories()) {
                        System.out.println(c.getId() + ": " + c.getName());
                    }
                    break;

                case 4:
                    // View Transactions
                    System.out.println("\nTransactions:");
                    for (Transaction t : manager.getTransactions()) {
                        System.out.println(t.getId() + ": " + t.getType() + ", " + t.getAmount() + " - "
                                + t.getCategory().getName() + " - " + t.getDate());
                    }
                    break;

                case 5:
                    // Save Data to CSV
                    CSVHandler.saveCategories(manager, "categories.csv");
                    CSVHandler.saveTransactions(manager, "transactions.csv");
                    System.out.println("Data saved.");
                    break;

                case 6:
                    // Exit Program
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        // Close scanner and exit
        scanner.close();
        System.out.println("Exiting program...");
    }
}
