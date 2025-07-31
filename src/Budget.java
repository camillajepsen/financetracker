public class Budget {
    private final int id;
    private int categoryId;
    private double budgetAmount;
    private double spentAmount;

    public Budget(int id, int categoryId, double budgetAmount, double spentAmount) {
        this.id = id;
        this.categoryId = categoryId;
        this.budgetAmount = budgetAmount;
        this.spentAmount = spentAmount;
    }

    public int getId() {
        return this.id;
    }

    public int getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public double getBudgetAmount() {
        return this.budgetAmount;
    }

    public void setBudgetAmount(double budgetAmount) {
        this.budgetAmount = budgetAmount;
    }

    public double getSpentAmount() {
        return this.spentAmount;
    }

    public void setSpentAmount(double spentAmount) {
        this.spentAmount = spentAmount;
    }
}
