import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FinanceManager {
    private static int categoryCounter = 0;  // Static variable for category ID incrementation
    private List<Category> categories = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();
    private List<Budget> budgets = new ArrayList<>();

    // Add Category method
    public void addCategory(String name) {
        int categoryId = ++categoryCounter;  // Auto-increment the ID for new categories
        Category newCategory = new Category(categoryId, name);
        this.categories.add(newCategory);
    }

    // Add Transaction method (now using LocalDate)
    public void addTransaction(int id, TransactionType type, double amount, Category category, LocalDate date, String description) {
        Transaction newTransaction = new Transaction(id, type, amount, category, date, description);
        this.transactions.add(newTransaction);
    }

    // Add Budget method
    public void addBudget(int categoryId, double budgetAmount) {
        int budgetId = ++categoryCounter;
        Budget newBudget = new Budget(budgetId, categoryId, budgetAmount, 0.0);
        this.budgets.add(newBudget);
    }

    // Getters for categories, transactions, and budgets
    public List<Category> getCategories() {
        return categories;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public List<Budget> getBudgets() {
        return budgets;
    }
}
