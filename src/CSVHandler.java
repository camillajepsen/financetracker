import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CSVHandler {

    public static void createFileIfNotExists(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println(fileName + " created.");
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }
    }
    public static void loadCategories(FinanceManager manager, String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                manager.addCategory(name);  // Add categories with generated ID (ID will be auto-incremented)
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveCategories(FinanceManager manager, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Category category : manager.getCategories()) {
                writer.printf("%d,%s%n", category.getId(), category.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveTransactions(FinanceManager manager, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Transaction transaction : manager.getTransactions()) {
                // Saving the transaction with only the date in the format "yyyy-MM-dd"
                writer.printf("%d,%s,%.2f,%s,%s,%s%n", transaction.getId(),
                        transaction.getType(), transaction.getAmount(),
                        transaction.getCategory().getName(),
                        transaction.getDate(), transaction.getDescription());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadTransactions(FinanceManager manager, String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                TransactionType type = TransactionType.valueOf(parts[1]);
                double amount = Double.parseDouble(parts[2]);
                String categoryName = parts[3];
                String dateStr = parts[4]; // The date is now in the format "yyyy-MM-dd"

                // Parse the date string into LocalDate
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(dateStr, formatter);

                // Find the category by name
                Category category = findCategoryByName(manager, categoryName);
                if (category != null) {
                    manager.addTransaction(id, type, amount, category, date, parts[5]);  // Assuming description is the last part
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Category findCategoryByName(FinanceManager manager, String categoryName) {
        for (Category category : manager.getCategories()) {
            if (category.getName().equals(categoryName)) {
                return category;
            }
        }
        return null;
    }
}
