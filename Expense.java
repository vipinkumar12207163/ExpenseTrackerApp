package tracker;

public class Expense {
    private String date;
    private String category;
    private String description;
    private double amount;

    public Expense(String date, String category, String description, double amount) {
        this.date = date;
        this.category = category;
        this.description = description;
        this.amount = amount;
    }

    public String toString() {
        return date + "," + category + "," + description + "," + amount;
    }
}
