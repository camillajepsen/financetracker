import java.time.LocalDate;

public class Transaction {
    private final int id;
    private TransactionType type;
    private double amount;
    private Category category;
    private LocalDate date;  // Changed to LocalDate
    private String description;

    // Constructor updated to accept LocalDate instead of LocalDateTime
    public Transaction(int id, TransactionType type, double amount, Category category, LocalDate date, String description) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.description = description;
    }

    // Getter for id
    public int getId() {
        return this.id;
    }

    // Setter and Getter for Transaction Type
    public void setType(TransactionType type) {
        this.type = type;
    }

    public TransactionType getType() {
        return this.type;
    }

    // Setter and Getter for Amount
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return this.amount;
    }

    // Setter and Getter for Category
    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return this.category;
    }

    // Setter and Getter for Date (now using LocalDate)
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return this.date;
    }

    // Setter and Getter for Description
    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
